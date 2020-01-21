package com.iindicar.indicar.view.main.join;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.remote.LoadDataResponse;
import com.iindicar.indicar.data.source.user.UserDataSource;
import com.iindicar.indicar.data.source.user.UserRepository;
import com.iindicar.indicar.databinding.ActivityJoinBinding;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.view.BaseActivity;
import com.iindicar.indicar.view.main.MainActivity;
import com.iindicar.indicar.view.main.login.LoginActivity;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.api.LineApiClientBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//중요: EmailAuth 이후 join을 할 경우, join을 눌렀을 때 AddUser(name, email)에 들어가는 email값은 email 변수지, etEmail의 값이 아니다!
//이메일 인증되지 않은 메일로 가입되는 경우를 방지하기 위함이다. 이거 잘 체크해 볼 것.

public class JoinActivity extends BaseActivity<ActivityJoinBinding> {

    User Cuser = null;
    String loginApiId, email;

    private static LineApiClient lineApiClient;
    boolean isEmail;

    //initiatePopupWindowEmail1에서 사용됨. Spinner-EditText간 상호 연동을 위해 어쩔 수 없이 사용.
    String emailEnd = new String("");

    //닉네임 최소길이 및 최대길이(바이트 기준)
    private final int MAX_BYTE_OF_USER_NAME = 20;
    private final int MIN_BYTE_OF_USER_NAME = 4;

    /**
     * 외부영역 클릭시 종료 안되게 막기
     * @return true : 하단 뷰에 이벤트 전달 / false : 전달 안함 */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.setActivity(this);

        binding.toolbar.btnLeft.setOnClickListener(v -> onBackPressed());

        LineApiClientBuilder apiClientBuilder = new LineApiClientBuilder(getApplicationContext(),getString(R.string.line_channel_id));
        lineApiClient = apiClientBuilder.build();

        //String userId, String userName, String userEmail, String userLoginMethod, String userImageUrl, String userAddress
        Intent intent = getIntent();
        Cuser = new User("", intent.getStringExtra("name"), intent.getStringExtra("email"), intent.getStringExtra("login_method"), intent.getStringExtra("profile_img_url"), "");
        email = Cuser.getUserEmail();
        loginApiId = intent.getStringExtra("id");
        isEmail = intent.getBooleanExtra("isEmail", false);

        binding.joinetName.setText(Cuser.getUserName());
        if(isEmail) {
            String[] tmpEmail = Cuser.getUserEmail().split("@");

            binding.joinbtnJoin.setVisibility(View.VISIBLE);
            binding.joinbtnEmailAuth.setVisibility(View.GONE);
            binding.jiNAlertEmail1.setText(tmpEmail[0]);
            binding.jiNAlertEmail1.setClickable(false);
            binding.jiNAlertEmail1.setFocusable(false);
            binding.jiNAlertEmail2.setText(tmpEmail[1]);
            binding.jiNAlertEmail2.setClickable(false);
            binding.jiNAlertEmail2.setFocusable(false);
            binding.jiNAlertSpinner.setVisibility(View.GONE);
        } else {
            binding.joinbtnJoin.setVisibility(View.GONE);
            binding.joinbtnEmailAuth.setVisibility(View.VISIBLE);
        }

        //이메일 주소 선택 Spinner
        final String[] emailArray = getResources().getStringArray(R.array.emailArray);
        List<String> emailList = new ArrayList<>();
        for(int i=0;i<emailArray.length;i++) {
            emailList.add(emailArray[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, emailList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.jiNAlertSpinner.setAdapter(adapter);

        binding.jiNAlertSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 4) {
                    binding.jiNAlertEmail2.setText(emailEnd);
                    emailEnd = "";
                } else {
                    binding.jiNAlertEmail2.setText(emailArray[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //페이스북의 경우, 이메일이 있을 수도 있다. 이 경우에는 해당 이메일을 가져온다.
        if(isEmail) {
            String[] emailArr = email.split("@");
            binding.jiNAlertEmail1.setText(emailArr[0]);
            emailEnd = emailArr[1];
            for(int i=0;i<emailArray.length;i++) {
                if(emailEnd.equals(emailArray[i])) {
                    binding.jiNAlertSpinner.setSelection(i);
                    emailEnd = "";
                    break;
                }
            }
            if(!emailEnd.equals("")) {
                binding.jiNAlertSpinner.setSelection(4);
            }
        } else {
            binding.jiNAlertSpinner.setSelection(0);
        }
    }

    public void join() {
        binding.loadingjoin.setVisibility(View.VISIBLE);
        final String name = binding.joinetName.getText().toString();
        final String name_noSpace = name.replace(" ","");

        if(name_noSpace.getBytes().length > MAX_BYTE_OF_USER_NAME) {
            Toast.makeText(getApplicationContext(),resources.getString(R.string.strTooLongNickname),Toast.LENGTH_LONG).show();
            binding.loadingjoin.setVisibility(View.GONE);
            binding.joinetName.setText("");
        } else if(name_noSpace.getBytes().length < MIN_BYTE_OF_USER_NAME) {
            binding.loadingjoin.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),resources.getString(R.string.strTooShortNickname),Toast.LENGTH_LONG).show();
            binding.joinetName.setText("");
        } else {
            //주의: item의 형태는 아래와 같다.
            //[{key=U30811372d97534dfa4d1c23eac48b0bd,email=incar_candykick@naver.com,
            // frst_time=2018-11-06 16:57:05,status=S,token=MTU0MTQ5MTAyNTkyMA==}]
            if (isEmail) {
                addUser(name, email);
            } else {
                UserRepository.getInstance().EmailAuthSearch(loginApiId, new UserDataSource.LoadDataCallback<String>() {
                    @Override
                    public void onDataLoaded(String result, String item) {
                        if (result.equals("F")) {
                            //이메일 인증 과정에서 오류 발생
                            binding.loadingjoin.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), getString(R.string.strRequestEmailError), Toast.LENGTH_SHORT).show();
                        } else if (result.equals("S")) {
                            if (item.contains("status=S")) {
                                addUser(name, email);
                            } else if (item.contains("status=W")) {
                                //사용자가 이메일 인증을 안 함
                                binding.loadingjoin.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), getString(R.string.strRequestEmailWait), Toast.LENGTH_SHORT).show();
                            } else if (item.contains("status=C")) {
                                //다른 이메일 인증을 요청해서 현 이메일 인증이 취소됨
                                binding.loadingjoin.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), getString(R.string.strRequestEmailCancel), Toast.LENGTH_LONG).show();
                                cancelLogin();
                                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                            } else {
                                //원인불명 오류
                                binding.loadingjoin.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), getString(R.string.strRequestEmailError), Toast.LENGTH_LONG).show();
                                cancelLogin();
                                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                            }
                        } else {
                            //원인불명 오류
                            binding.loadingjoin.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), getString(R.string.strLoginedErr), Toast.LENGTH_LONG).show();
                            cancelLogin();
                            Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onDataNotAvailable() {
                        //원인불명 오류
                        binding.loadingjoin.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), getString(R.string.strLoginedErr), Toast.LENGTH_LONG).show();
                        cancelLogin();
                        Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    //회원가입 함수
    private void addUser(final String name, String email) {
        //사용자가 이메일 인증을 함
        UserRepository.getInstance().addUser(Cuser.getUserLoginMethod(), name, email, Cuser.getUserImageUrl(), new UserDataSource.LoadDataCallback<User>() {
            @Override
            public void onDataLoaded(String result, User currentUser) {
                if(currentUser != null) {
                    if(result.equals("S")) { //가입에 성공한 경우
                        //1. UserRepository에 로그인 정보를 쓴다.
                        UserRepository.getInstance().setLoginUser(currentUser);
                        //{"id":10687,"email":"woo171tm@naver.com","name":"ㄴㄴ","login_method":"kakao","frst_time":"2018-11-17 21:25:49",
                        // "last_login_time":null,"addres":null,"like_bbs":null,
                        // "profile_img_url":"https://k.kakaocdn.net/dn/ZbdfB/btqpJGXfaIf/qi1BtDwOchDH4RtkMx2KaK/profile_640x640s.jpg",
                        // "use_at":"Y","user_se":"USR","fcm_token":null}
                                                /*User loginUser = new User(element.getAsJsonObject().get("id").getAsString(), element.getAsJsonObject().get("name").getAsString(),
                                                element.getAsJsonObject().get("email").getAsString(), element.getAsJsonObject().get("login_method").getAsString(),
                                                element.getAsJsonObject().get("profile_img_url").getAsString(), element.getAsJsonObject().get("addres").getAsString());
                                                UserRepository.getInstance().setLoginUser(loginUser);*/

                        //2. 서버에 푸시알람을 보낸다.(Firebase 기반이 아닌, 안드로이드 자체 푸시알람임)
                        NotificationManager notificationManager = (NotificationManager)getApplicationContext().getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                        Notification.Builder builder = new Notification.Builder(getApplicationContext());

                        //Intent intent = new Intent(LoginAct, MainActivity.class);
                        //PendingIntent pendingIntent = PendingIntent.getActivity(LoginAct, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                        builder.setSmallIcon(R.drawable.app_icon)
                                .setTicker("Indicar Tuning")
                                .setNumber(1)
                                .setContentTitle("Indicar Tuning")
                                .setContentText(getString(R.string.strAddUserAlarm1) + name + getString(R.string.strAddUserAlarm2))
                                .setAutoCancel(true);
                        notificationManager.notify(1, builder.build());

                        //SharedPreference 셋팅
                        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefLogin.edit();
                        editor.putLong("profileEditDate", 0);
                        editor.putString("id", currentUser.getUserId());
                        editor.putString("login_method", currentUser.getUserLoginMethod());
                        editor.putString("name", currentUser.getUserName());
                        editor.putString("profile_img_url", currentUser.getUserImageUrl());
                        editor.putString("email", currentUser.getUserEmail());
                        editor.putString("apiid", loginApiId);
                        editor.putBoolean("EventAlarm", true);
                        editor.putBoolean("OtherAlarm", true);
                        Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
                        editor.putString("locale",systemLocale.getLanguage());
                        editor.commit();

                        //3. FCM 토큰값을 서버에 업로드한다.
                        UserRepository.getInstance().updateFCMToken(currentUser.getUserId(), FirebaseInstanceId.getInstance().getToken(), new UserDataSource.LoadDataCallback<LoadDataResponse>() {
                            @Override
                            public void onDataLoaded(String result, LoadDataResponse item) {
                                if(item.getResult().equals("S")) {
                                    Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);
                                    JoinActivity.this.finish();
                                } else if(item.getContent().toString().contains("FCM 토큰값이 동일합니다.")) {
                                    Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);
                                    JoinActivity.this.finish();
                                } else {
                                    binding.loadingjoin.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), getString(R.string.strLoginedErr), Toast.LENGTH_SHORT).show();
                                    cancelLogin();
                                }
                            }

                            @Override
                            public void onDataNotAvailable() {
                                binding.loadingjoin.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), getString(R.string.strLoginedErr), Toast.LENGTH_SHORT).show();
                                cancelLogin();
                            }
                        });
                    } else if(result.equals("F")) {
                        //해당 이메일로 가입한 계정이 존재하는 경우
                        binding.loadingjoin.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),getString(R.string.strAddUserFail),Toast.LENGTH_LONG).show();
                        cancelLogin();;
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        //처리 도중 오류가 발생한 경우
                        binding.loadingjoin.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), getString(R.string.strAddUserErr), Toast.LENGTH_LONG).show();
                        cancelLogin();;
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    //처리 도중 오류가 발생한 경우
                    binding.loadingjoin.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), getString(R.string.strAddUserErr), Toast.LENGTH_LONG).show();
                    cancelLogin();;
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onDataNotAvailable() {
                binding.loadingjoin.setVisibility(View.GONE);
                //처리 도중 오류가 발생한 경우
                Toast.makeText(getApplicationContext(), getString(R.string.strAddUserErr), Toast.LENGTH_LONG).show();
                cancelLogin();;
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    //이메일 인증 함수 + 이메일 인증 버튼 클릭 시 작동하는 함
    public void emailCheckAndAuth() {
        binding.loadingjoin.setVisibility(View.VISIBLE);
        email = binding.jiNAlertEmail1.getText().toString() + "@" + binding.jiNAlertEmail2.getText().toString();

        UserRepository.getInstance().searchUserByEmail(email, new UserDataSource.LoadDataCallback<User>() {
            @Override
            public void onDataLoaded(String result, User item) {
                if(item != null) {
                    if(result.equals("S")) {
                        binding.loadingjoin.setVisibility(View.GONE);
                        String result_login_method = item.getUserLoginMethod();
                        Toast.makeText(getApplicationContext(), getString(R.string.strEmailexisted1) + result_login_method + getString(R.string.strEmailexisted3), Toast.LENGTH_LONG).show();
                        cancelLogin();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    } else if(result.equals("F")) {
                        UserRepository.getInstance().EmailAuthRequest(email, loginApiId, new UserDataSource.LoadDataCallback<LoadDataResponse>() {
                            //중요: 실제 정보는 String result만 가져옴. item = null.
                            @Override
                            public void onDataLoaded(String result, LoadDataResponse item) {
                                if(result.equals("S")) {
                                    binding.loadingjoin.setVisibility(View.GONE);
                                    //이메일 인증 전송
                                    Toast.makeText(getApplicationContext(),getString(R.string.strRequestEmailSent),Toast.LENGTH_LONG).show();
                                    binding.joinbtnEmailAuth.setVisibility(View.GONE);
                                    binding.joinbtnJoin.setVisibility(View.VISIBLE);
                                    binding.joinbtnResendEmail.setVisibility(View.VISIBLE);
                                    //if(parameter.equals("F")) {
                                    //btnEmailAuth.setVisibility(View.GONE);
                                    //btnJoin.setVisibility(View.VISIBLE);
                                    //etEmail.setClickable(false);
                                    //etEmail.setFocusable(false);
                                    //}
                                } else {
                                    //이메일 인증 오류
                                    binding.loadingjoin.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), getString(R.string.strRequestEmailError), Toast.LENGTH_LONG).show();
                                    cancelLogin();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onDataNotAvailable() {
                                binding.loadingjoin.setVisibility(View.GONE);
                                //이메일 인증 오류
                                Toast.makeText(getApplicationContext(), getString(R.string.strRequestEmailError), Toast.LENGTH_LONG).show();
                                cancelLogin();;
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                } else {
                    binding.loadingjoin.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), getString(R.string.strLoginedErr), Toast.LENGTH_LONG).show();
                    cancelLogin();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
            }

            //item이 User 객체로 변환이 안 된다 = 결과값이 없다 = 이메일 인증 수행
            @Override
            public void onDataNotAvailable() {
                UserRepository.getInstance().EmailAuthRequest(email, loginApiId, new UserDataSource.LoadDataCallback<LoadDataResponse>() {
                    //중요: 실제 정보는 String result만 가져옴. item = null.
                    @Override
                    public void onDataLoaded(String result, LoadDataResponse item) {
                        if(result.equals("S")) {
                            binding.loadingjoin.setVisibility(View.GONE);
                            //이메일 인증 전송
                            Toast.makeText(getApplicationContext(),getString(R.string.strRequestEmailSent),Toast.LENGTH_LONG).show();
                            binding.joinbtnEmailAuth.setVisibility(View.GONE);
                            binding.joinbtnJoin.setVisibility(View.VISIBLE);
                            binding.joinbtnResendEmail.setVisibility(View.VISIBLE);
                            //if(parameter.equals("F")) {
                            //btnEmailAuth.setVisibility(View.GONE);
                            //btnJoin.setVisibility(View.VISIBLE);
                            //etEmail.setClickable(false);
                            //etEmail.setFocusable(false);
                            //}
                        } else {
                            binding.loadingjoin.setVisibility(View.GONE);
                            //이메일 인증 오류
                            Toast.makeText(getApplicationContext(), getString(R.string.strRequestEmailError), Toast.LENGTH_LONG).show();
                            cancelLogin();;
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onDataNotAvailable() {
                        binding.loadingjoin.setVisibility(View.GONE);
                        //이메일 인증 오류
                        Toast.makeText(getApplicationContext(), getString(R.string.strRequestEmailError), Toast.LENGTH_LONG).show();
                        cancelLogin();;
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    //뒤로 가기를 누르면 로그아웃 수행
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancelLogin();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    //유저 식별 실패 시 로그아웃 메서드
    private void cancelLogin() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
        } else if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
        } else if (!Session.getCurrentSession().isClosed()) {
            UserManagement.requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() {

                }
            });
        } else if(lineApiClient.getCurrentAccessToken().isSuccess()) {
            new LineLogout().execute();
        } else {

        }
    }

    //라인 로그아웃 메서드
    private class LineLogout extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            lineApiClient.logout();
            return null;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_join;
    }
}
