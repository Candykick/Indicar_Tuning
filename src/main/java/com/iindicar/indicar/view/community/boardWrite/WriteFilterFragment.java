package com.iindicar.indicar.view.community.boardWrite;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.databinding.FragmentWriteFilterBinding;
import com.iindicar.indicar.databinding.HolderBoardFilterBinding;
import com.iindicar.indicar.model.BoardFilterItem;
import com.iindicar.indicar.utils.ActivityResultEvent;
import com.iindicar.indicar.utils.BusProvider;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;
import com.iindicar.indicar.view.album.AlbumActivity;
import com.squareup.otto.Subscribe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

public class WriteFilterFragment extends BaseFragment<FragmentWriteFilterBinding> implements BoardWriteContract.FilterView {

    //카메라 관련 변수
    public static final int REQUEST_CAMERA = 71;
    public static final int REQUEST_PICK_FROM_ALBUM = 101;

    private static final String EXTRA_IS_UPDATING = "EXTRA_IS_UPDATING";

    private String mCurrentPhotoPath;
    Uri photoUri;

    private OnFilterFragmentInteractionListener interactionListener;

    private WriteFilterPresenter presenter;
    private WriteImageAdapter adapter;

    private boolean isUpdating;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_write_filter;
    }

    public WriteFilterFragment(){}

    public static WriteFilterFragment newInstance(boolean isUpdating){

        WriteFilterFragment fragment = new WriteFilterFragment();

        Bundle args = new Bundle();
        args.putBoolean(EXTRA_IS_UPDATING, isUpdating);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFilterFragmentInteractionListener){
            interactionListener = (OnFilterFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFilterFragmentInteractionListener");
        }
    }

    private void getExtraIsUpdating(){
        this.isUpdating = getArguments().getBoolean(EXTRA_IS_UPDATING);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtraIsUpdating();

        presenter = new WriteFilterPresenter(this, BoardRepository.getInstance(), isUpdating);
    }


    //photoUri값을 저장.
    //메모리가 부족한 구형 핸드폰에서 카메라 실행 시 액티비티 자체를 죽여버리는 경우가 있다. 이를 대비한 것.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (photoUri != null) {
            outState.putString("cameraImageUri", photoUri.toString());
            outState.putString("mCurrentPhotoPath", mCurrentPhotoPath);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BusProvider.getInstance().register(this);

        //카메라에서 사진을 찍고 넘어왔을 때, 액티비티가 다시 생성된 경우 저장해놨던 Bundle값을 불러온다.
        if(savedInstanceState != null)
        if (savedInstanceState.containsKey("cameraImageUri") && savedInstanceState.containsKey("mCurrentPhotoPath")) {
            photoUri = Uri.parse(savedInstanceState.getString("cameraImageUri"));
            mCurrentPhotoPath = savedInstanceState.getString("mCurrentPhotoPath");
            Log.d("Indicar Tuning","onViewCreated(Re)");
            Log.d("Indicar Tuning",mCurrentPhotoPath);
        }

        // xml 뷰와 프레젠터 바인딩
        binding.setPresenter(presenter);

        adapter = new WriteImageAdapter(context);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        initView();

        presenter.onViewCreated(); // 뷰 생성 후 호출
    }

    private void initView() {

        // 게시판 유형 리스트 보여줄 리사이클러뷰 생성
        binding.recyclerBoardFilter.setLayoutManager(new GridLayoutManager(context, 3));
        binding.recyclerBoardFilter.setAdapter(new BoardFilterAdapter(context, presenter.boardType));

        // 이미지 선택 버튼 리스너 등록
        binding.btnAlbum.setOnClickListener(v -> onStartAlbumActivity());
        binding.btnCamera.setOnClickListener(v -> onStartCameraActivity());

        // 선택된 사진 보여줄 리사이클러 뷰 생성
        binding.recyclerImages.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerImages.setAdapter(adapter);
        binding.recyclerImages.setEmptyView(binding.emptyView);

        // 다음으로 버튼 클릭 리스너 등록
        binding.btnNext.setOnClickListener(v -> presenter.onNextButtonClicked());
        binding.btnCancel.setOnClickListener(v -> getActivity().finish());
    }

    @Override
    public void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        BusProvider.getInstance().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        interactionListener = null;
        super.onDetach();
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 사진 앨범 액티비티 띄우기 */
    @Override
    public void onStartAlbumActivity() {

        // 앨범 띄우기 전에 퍼미션 체크 후 액티비티 전환
        TedPermission.with(context)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Intent intent = new Intent(context, AlbumActivity.class);
                        startActivityForResult(intent, REQUEST_PICK_FROM_ALBUM);
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        makeToast(resources.getString(R.string.strPermissionDenied));
                    }
                })
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    /**
     * 카메라 액티비티 띄우기 */
    @Override
    public void onStartCameraActivity() {
        // 앨범 띄우기 전에 퍼미션 체크 후 액티비티 전환
        TedPermission.with(context)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        //API 24 미만 : 프로바이더 없이 사용 가능
                        if(Build.VERSION.SDK_INT < 24) {
                            Log.d("Indicar Tuning","onStartCameraActivity");
                            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            if (takePicture.resolveActivity(getActivity().getPackageManager()) != null) {
                                File photoFile = null;

                                try {
                                    photoFile = createImageFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                if (photoFile != null) {
                                    photoUri = Uri.fromFile(photoFile);
                                    takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                    getActivity().startActivityForResult(takePicture, REQUEST_CAMERA);
                                }
                            }
                        } else {//API 24 이상 : 프로바이더 필수*/
                            String state = Environment.getExternalStorageState();
                            if (Environment.MEDIA_MOUNTED.equals(state)) {
                                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                                    File photoFile = null;
                                    try {
                                        photoFile = createImageFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    if (photoFile != null) {
                                        photoUri = FileProvider.getUriForFile(getActivity().getApplicationContext(), "com.iindicar.indicar", photoFile);
                                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                        getActivity().startActivityForResult(takePictureIntent, REQUEST_CAMERA);
                                    }
                                }
                            } else {
                                makeToast(resources.getString(R.string.strcannotCamera));
                                return;
                            }
                        }
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        makeToast(resources.getString(R.string.strcannotCamera));
                    }
                })
                .setPermissions(Manifest.permission.CAMERA
                        , Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    /*private static Uri getOutputMediaFileUri(int type){
        // 아래 capture한 사진이 저장될 file 공간을 생성하는 method를 통해 반환되는 File의 URI를 반환
        return Uri.fromFile(getOutputMediaFile(type));
    }


    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        // 외부 저장소에 이 App을 통해 촬영된 사진만 저장할 directory 경로와 File을 연결
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){ // 해당 directory가 아직 생성되지 않았을 경우 mkdirs(). 즉 directory를 생성한다.
            if (! mediaStorageDir.mkdirs()){ // 만약 mkdirs()가 제대로 동작하지 않을 경우, 오류 Log를 출력한 뒤, 해당 method 종료
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        // File 명으로 file의 생성 시간을 활용하도록 DateFormat 기능을 활용
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;

        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + timeStamp + ".jpg");
        } else {
            return null;
        }
        Log.d("Indicar Tuning",mediaFile.getAbsolutePath());
        return mediaFile; // 생성된 File valuable을 반환
    }*/

    /**
     * 선택된 사진의 path 정보를 presenter 에게 넘겨준다 */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Indicar Tuning","Fragment onActivityResult");

        if(resultCode != RESULT_OK) return;

        /*else if(requestCode == REQUEST_CAMERA && Build.VERSION.SDK_INT < 22)
        {
            File tempImg = new File(photoUri.getPath()); // 앞서 지정한 경로에 파일의 경로에
            if(!tempImg.exists()){ // file이 존재하지 않으면
                Log.d("IndicarTuning", "The image doesn't exist : " + tempImg.toString());
                try {
                    FileOutputStream fos = new FileOutputStream(new File(photoUri.getPath())); // FileOutputStream 변수를 선언한 후
                    try {
                        fos.write(data.getExtras().getByte("data")); // Intent의 data 값을 write 하여 저장
                        fos.close(); // 그러고 나서 FileOutputStream을 종료한다.
                    } catch(IOException e){
                        Log.d("IndicarTuning", "IOException occur : " + e.getMessage());
                    }
                } catch (FileNotFoundException e){
                    Log.d("IndicarTuning" , "The image doesn't exist : "+ tempImg.toString());
                }
            }
            presenter.onActivityResult(requestCode, data);
        }*/
        else if(requestCode == REQUEST_CAMERA) {
            galleryAddPic();
        }
        else
            presenter.onActivityResult(requestCode, data);
    }

    /**
     * 액티비티에 프래그먼트 전환 요청 */
    @Override
    public void onStartContentFragment() {
        interactionListener.onStartContentFragment();
    }

    /**
     * 액티비티와 통신하기 위한 콜백 리스너 */
    public interface OnFilterFragmentInteractionListener {

        void onStartContentFragment();
    }

    /**
     * 게시판 유형 리스트 보여줄 어댑터 */
    public static class BoardFilterAdapter extends BaseRecyclerAdapter<BoardFilterItem, BoardFilterAdapter.ViewHolder> {

        public final ObservableField<String> boardType;

        public BoardFilterAdapter(Context context, ObservableField<String> boardType) {
            super(context);
            itemList = new ArrayList<>();
            if(LocaleHelper.getLanguage(context).equals("ko")) {
                itemList.add(new BoardFilterItem(context.getString(R.string.daylife), R.drawable.btn_filter_daylife));
                itemList.add(new BoardFilterItem(context.getString(R.string.market), R.drawable.btn_filter_market));
                itemList.add(new BoardFilterItem(context.getString(R.string.diy), R.drawable.btn_filter_diy));
            } else {
                itemList.add(new BoardFilterItem(context.getString(R.string.daylife), R.drawable.btn_filter_daylifeen));
                itemList.add(new BoardFilterItem(context.getString(R.string.market), R.drawable.btn_filter_marketen));
                itemList.add(new BoardFilterItem(context.getString(R.string.diy), R.drawable.btn_filter_diyen));
            }
            this.boardType = boardType;
        }

        @Override
        protected void onBindView(ViewHolder holder, int position) {

            BoardFilterItem item = itemList.get(position);

            holder.binding.setItem(item);
            holder.binding.setBoardType(boardType);

            holder.binding.itemFrame.setOnClickListener(v -> boardType.set(item.getTypeName()));
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_board_filter, parent, false);
            return new ViewHolder(view);
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            protected HolderBoardFilterBinding binding;

            public ViewHolder(View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }
        }
    }

    //사진 촬영 후 파일 생성
    private File createImageFile() throws IOException {
        //이미지 파일 이름 생성
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".jpg";
        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/" + imageFileName);

        //파일 저장: ACTION_VIEW 인텐트 이용
        mCurrentPhotoPath = storageDir.getAbsolutePath();
        Log.d("Indicar Tuning","createImageFile");
        Log.d("Indicar Tuning", mCurrentPhotoPath);

        return storageDir;
    }

    //갤러리 새로고침, ACTION_MEDIA_MOUNTED는 하나의 폴더, FILE은 하나의 파일을 새로고침할 때 사용함
    //사진 찍고 Commit하는 함수
    public void galleryAddPic() {
        Log.d("Indicar Tuning","galleryAddPic");

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

        File file = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);

        //onStartAlbumActivity();

        String[] pathArr = new String[1];
        String tmp = contentUri.toString().replace("file://","");
        pathArr[0] = tmp;
        Log.d("Indicar Tuning",tmp);

        Intent intent = getActivity().getIntent();
        intent.putExtra("RESULT_PATH_ARRAY", pathArr);
        presenter.onActivityResult(REQUEST_PICK_FROM_ALBUM, intent);
    }

    //카메라에서 찍어서 가져온 사진을 리사이즈하는 함수
    //구형 핸드폰에서 OOM이 자꾸 발생해서 넣은 소스코드.
    /*private Bitmap resize(Context context, Uri uri, int resize){
        Bitmap resizeBitmap=null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options); // 1번

            int width = options.outWidth;
            int height = options.outHeight;
            int samplesize = 1;

            while (true) {//2번
                if (width / 2 < resize || height / 2 < resize)
                    break;
                width /= 2;
                height /= 2;
                samplesize *= 2;
            }

            options.inSampleSize = samplesize;
            Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options); //3번
            resizeBitmap=bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resizeBitmap;
    }*/

    @SuppressWarnings("unused")
    @Subscribe
    public void onActivityResult(@NonNull ActivityResultEvent event) {
        onActivityResult(event.getRequestCode(), event.getResultCode(), event.getData());
    }


}
