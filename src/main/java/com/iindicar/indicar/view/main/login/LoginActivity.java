package com.iindicar.indicar.view.main.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.remote.LoadDataResponse;
import com.iindicar.indicar.data.source.user.UserDataSource;
import com.iindicar.indicar.data.source.user.UserRepository;
import com.iindicar.indicar.databinding.ActivityLoginBinding;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.utils.App;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.BaseActivity;
import com.iindicar.indicar.view.main.MainActivity;
import com.iindicar.indicar.view.main.join.JoinActivity;
import com.iindicar.indicar.view.main.tutorial.TutorialFragment;
import com.kakao.auth.AuthType;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.api.LineApiClientBuilder;
import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Locale;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    //뒤로가기 버튼을 두 번 클릭시 종료. 이를 구현하기 위한 변수를 선언한다.
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressTime = 0;

    public final ObservableBoolean isKorean = new ObservableBoolean(true);
    public final ObservableInt currentPage = new ObservableInt(0);

    //로그인 관련 변수
    private FirebaseAuth GoogleModule;
    private GoogleSignInClient GoogleClient;
    private CallbackManager FBcallBackManager;
    private SessionCallback KakaoModule;
    private static LineApiClient lineApiClient;

    //회원가입 메소드
    User user = null;
    String loginApiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        binding.setActivity(this);

        // Initialize SDK before setContentView(Layout ID)

        //앱 설치 후 첫 실행인지를 체크한다.
        //첫 실행이면 You're New!를 띄우고 그게 아니면 You're Back!을 띄운다.
        SharedPreferences pref = getSharedPreferences("firstexe", MODE_PRIVATE);
        if (pref.getString("index", "").equals("")) {
            /*Intent i = new Intent(FirstActivity.this,GuideActivity.class);
            startActivity(i);*/
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("index", "execute");
            editor.commit();
        }

        // 뷰페이저 생성
        binding.viewPagerWelcome.setAdapter(new WelcomePagerAdapter(getSupportFragmentManager()));
        binding.viewPagerWelcome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                currentPage.set(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        //4종 로그인 초기화
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();
        GoogleClient = GoogleSignIn.getClient(this, gso);
        GoogleModule = FirebaseAuth.getInstance();
        FBcallBackManager = CallbackManager.Factory.create();
        KakaoModule = new SessionCallback();
        Session.getCurrentSession().addCallback(KakaoModule);
        App.setCurrentActivity(LoginActivity.this);
        LineApiClientBuilder apiClientBuilder = new LineApiClientBuilder(getApplicationContext(),getString(R.string.line_channel_id));
        lineApiClient = apiClientBuilder.build();

        //국가별 뷰 초기화
        this.isKorean.set(LocaleHelper.getLanguage(getApplicationContext()).equals("ko"));
    }

    //구글 로그인 버튼
    public void googleLogin() {
        binding.loadinglogin.setVisibility(View.VISIBLE);
        Intent intent = GoogleClient.getSignInIntent();
        startActivityForResult(intent, 9001);
    }

    //카카오 로그인 버튼
    public void kakaoLogin() {
        Session.getCurrentSession().open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
        binding.loadinglogin.setVisibility(View.VISIBLE);
    }

    //페이스북 로그인 버튼
    public void fbLogin() {
        binding.loadinglogin.setVisibility(View.VISIBLE);
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "user_photos", "public_profile"));
        LoginManager.getInstance().registerCallback(FBcallBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {//AccessToken.getCurrentAccessToken()
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            loginApiId = object.getString("id");
                            user = new User("",object.getString("name"),"", "facebook", R.string.facebookGraphApi + loginApiId + "/picture", "");

                            CheckUserwithKey();
                        } catch (Exception e) {
                            //Toast.makeText(getApplicationContext(), getString(R.string.strErrwithCode)+e.toString(), Toast.LENGTH_LONG).show();
                            Toast.makeText(LoginActivity.this,getString(R.string.strErrwithCode) + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                binding.loadinglogin.setVisibility(View.GONE);
                //Toast.makeText(getApplicationContext(), "페이스북 로그인을 취소하셨습니다.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                binding.loadinglogin.setVisibility(View.GONE);
                //Toast.makeText(getApplicationContext(), getString(R.string.strErrwithCode) + error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(LoginActivity.this,getString(R.string.strErrwithCode) + error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //라인 로그인 버튼
    public void lineLogin() {
        binding.loadinglogin.setVisibility(View.VISIBLE);
        try {
            Intent lineIntent = LineLoginApi.getLoginIntent(getApplicationContext(), getString(R.string.line_channel_id));
            startActivityForResult(lineIntent, 7000);
        } catch (Exception e) {
            makeToast(getString(R.string.strErrwithCode) + e.toString());
            binding.loadinglogin.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    //각 로그인의 onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 9001) { //구글
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (Exception e) {
                binding.loadinglogin.setVisibility(View.GONE);
                if(e.toString().contains("ApiException")) {
                    makeToast("구글 플레이 버전이 너무 낮습니다. 구글 플레이를 업데이트해주세요.");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gms&hl=ko"));
                    startActivity(intent);
                } else {
                    makeToast(getString(R.string.strErrwithCode) + e.toString());
                }
            }
        } else if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        } else if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            super.onActivityResult(requestCode, resultCode, data);
            FBcallBackManager.onActivityResult(requestCode, resultCode, data);
            return;
        } else if (requestCode == 7000) {
            super.onActivityResult(requestCode, resultCode, data);
            LineLoginResult result = LineLoginApi.getLoginResultFromIntent(data);

            switch (result.getResponseCode()) {
                case SUCCESS:
                    loginApiId = result.getLineProfile().getUserId();
                    user = new User("",result.getLineProfile().getDisplayName(),"", "line", result.getLineProfile().getPictureUrl().toString(), "");

                    CheckUserwithKey();
                    break;
                case CANCEL:
                    binding.loadinglogin.setVisibility(View.GONE);
                    break;
                default:
                    binding.loadinglogin.setVisibility(View.GONE);
                    makeToast(getString(R.string.strErrwithCode) + result.getResponseCode().name());
                    break;
            }
        }
    }

    //구글 로그인 구현
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("Individual Car", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        GoogleModule.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser account = GoogleModule.getCurrentUser();
                            loginApiId = "0";
                            user = new User("",account.getDisplayName(),account.getEmail(), "google", account.getPhotoUrl().toString(), "");

                            try {
                                CheckUserwithEmail();
                            } catch (Exception e) {
                                binding.loadinglogin.setVisibility(View.GONE);
                                makeToast(getString(R.string.strgetUserInfoError) + e.toString());
                            }
                        } else {
                            binding.loadinglogin.setVisibility(View.GONE);
                            makeToast(getResources().getString(R.string.strErrwithCode) + task.getException().toString());
                        }
                    }
                });
    }

    //카카오 로그인 구현
    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.requestMe(new MeResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    binding.loadinglogin.setVisibility(View.GONE);
                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) { //인터넷 연결이 끊어진 경우.
                        makeToast(getResources().getString(R.string.strNoInternet));
                    } else {
                        makeToast(getResources().getString(R.string.strErrwithCode) + errorResult.toString());
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    binding.loadinglogin.setVisibility(View.GONE);
                    //redirectLoginActivitywithFail(getResources().getString(R.string.strLoginedErr));
                }

                @Override
                public void onNotSignedUp() {
                    binding.loadinglogin.setVisibility(View.GONE);
                }

                @Override
                public void onSuccess(UserProfile result) {
                    loginApiId = "0";
                    user = new User("",result.getNickname(),result.getEmail(), "kakao", result.getProfileImagePath(), "");

                    try {
                        CheckUserwithEmail();
                    } catch (Exception e) {
                        makeToast(getString(R.string.strgetUserInfoError) + e.toString());
                    }
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            binding.loadinglogin.setVisibility(View.GONE);
            if (e != null) {
                makeToast(getResources().getString(R.string.strKakaoLoginStop) + e.toString());
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                LoginActivity.this.finish();
                startActivity(intent);
            }
        }
    }

    public void CheckUserwithKey() {
        UserRepository.getInstance().EmailAuthSearch(loginApiId, new UserDataSource.LoadDataCallback<String>() {
            @Override
            public void onDataLoaded(String result, String item) {
                if (result.equals("F")) {
                    binding.loadinglogin.setVisibility(View.GONE);
                    Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                    intent.putExtra("id", loginApiId);
                    intent.putExtra("login_method", user.getUserLoginMethod());
                    intent.putExtra("name", user.getUserName());
                    intent.putExtra("email", user.getUserEmail());
                    intent.putExtra("isEmail", false);
                    intent.putExtra("profile_img_url", user.getUserImageUrl());
                    startActivity(intent);
                    finish();
                } else if (result.equals("S")) {
                    if (item.contains("status=S")) {
                        //주의: item의 형태는 아래와 같다.
                        //[{key=U30811372d97534dfa4d1c23eac48b0bd,email=incar_candykick@naver.com,
                        // frst_time=2018-11-06 16:57:05,status=S,token=MTU0MTQ5MTAyNTkyMA==}]
                        String tmpItem[] = item.split(",");
                        String email = tmpItem[1].substring(7);
                        user.setUserEmail(email);
                        CheckUserwithEmail();
                    } else if (item.contains("status=W")) {
                        binding.loadinglogin.setVisibility(View.GONE);
                        //사용자가 이메일 인증을 안 함
                        Toast.makeText(getApplicationContext(), getString(R.string.strRequestEmailWait), Toast.LENGTH_SHORT).show();
                        String tmpItem[] = item.split(",");
                        String email = tmpItem[1].substring(7);
                        Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                        intent.putExtra("id", loginApiId);
                        intent.putExtra("login_method", user.getUserLoginMethod());
                        intent.putExtra("name", user.getUserName());
                        intent.putExtra("email", email);
                        intent.putExtra("isEmail", false);
                        intent.putExtra("profile_img_url", user.getUserImageUrl());
                        startActivity(intent);
                        finish();
                        //cancelLogin();
                    } else if (item.contains("status=C")) {
                        binding.loadinglogin.setVisibility(View.GONE);
                        //다른 이메일 인증을 요청해서 현 이메일 인증이 취소됨
                        Toast.makeText(getApplicationContext(), getString(R.string.strRequestEmailCancel), Toast.LENGTH_LONG).show();
                        cancelLogin();
                        Intent intent = getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    } else {
                        binding.loadinglogin.setVisibility(View.GONE);
                        //원인불명 오류
                        Toast.makeText(getApplicationContext(), getString(R.string.strRequestEmailError), Toast.LENGTH_LONG).show();
                        cancelLogin();
                        Intent intent = getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                } else {
                    binding.loadinglogin.setVisibility(View.GONE);
                    //원인불명 오류
                    Toast.makeText(getApplicationContext(), getString(R.string.strLoginedErr), Toast.LENGTH_LONG).show();
                    cancelLogin();
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onDataNotAvailable() {
                binding.loadinglogin.setVisibility(View.GONE);
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                intent.putExtra("id", loginApiId);
                intent.putExtra("login_method", user.getUserLoginMethod());
                intent.putExtra("name", user.getUserName());
                intent.putExtra("email", user.getUserEmail());
                intent.putExtra("isEmail", false);
                intent.putExtra("profile_img_url", user.getUserImageUrl());
                startActivity(intent);
                finish();
            }
        });
    }

    public void CheckUserwithEmail() {
        //이메일 있음: searchUserByEmail 실행
        UserRepository.getInstance().searchUserByEmail(user.getUserEmail(), new UserDataSource.LoadDataCallback<User>() {
            @Override
            public void onDataLoaded(String result, User item) {
                if (item != null) {
                    if (result.equals("S")) {
                        String result_login_method = item.getUserLoginMethod();
                        if (result_login_method.equals(user.getUserLoginMethod())) {
                            //User 셋팅
                            UserRepository.getInstance().setLoginUser(item);

                            //SharedPreference 셋팅
                            SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefLogin.edit();
                            editor.putLong("profileEditDate", 0);
                            editor.putString("id", item.getUserId());
                            editor.putString("login_method", item.getUserLoginMethod());
                            editor.putString("name", item.getUserName());
                            editor.putString("profile_img_url", item.getUserImageUrl());
                            editor.putString("email", item.getUserEmail());
                            editor.putString("apiid", loginApiId);
                            editor.putBoolean("EventAlarm", true);
                            editor.putBoolean("OtherAlarm", true);
                            Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
                            editor.putString("locale",systemLocale.getLanguage());
                            editor.commit();

                            UserRepository.getInstance().updateFCMToken(item.getUserId(), FirebaseInstanceId.getInstance().getToken(), new UserDataSource.LoadDataCallback<LoadDataResponse>() {
                                @Override
                                public void onDataLoaded(String result, LoadDataResponse item) {
                                    if(item.getResult().equals("S")) {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                        startActivity(intent);
                                        LoginActivity.this.finish();
                                    } else if(item.getContent().toString().contains("FCM 토큰값이 동일합니다.")) {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                        startActivity(intent);
                                        LoginActivity.this.finish();
                                    } else {
                                        binding.loadinglogin.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), getString(R.string.strLoginedErr), Toast.LENGTH_SHORT).show();
                                        cancelLogin();
                                    }
                                }

                                @Override
                                public void onDataNotAvailable() {
                                    binding.loadinglogin.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), getString(R.string.strLoginedErr), Toast.LENGTH_SHORT).show();
                                    cancelLogin();
                                }
                            });
                        } else {//해당 이메일로 가입된 정보가 사용자가 클릭한 login_method로 가입한 정보가 아닐 경우
                            binding.loadinglogin.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), getString(R.string.strEmailexisted1) + result_login_method + getString(R.string.strEmailexisted2), Toast.LENGTH_LONG).show();
                            cancelLogin();
                            Intent intent = getIntent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            LoginActivity.this.finish();
                            startActivity(intent);
                        }
                    } else if (result.equals("F")) {
                        binding.loadinglogin.setVisibility(View.GONE);
                        Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                        intent.putExtra("id", loginApiId);
                        intent.putExtra("login_method", user.getUserLoginMethod());
                        intent.putExtra("name", user.getUserName());
                        intent.putExtra("email", user.getUserEmail());
                        intent.putExtra("isEmail", true);
                        intent.putExtra("profile_img_url", user.getUserImageUrl());
                        startActivity(intent);
                        finish();
                    }
                } else {
                    binding.loadinglogin.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), getString(R.string.strLoginedErr), Toast.LENGTH_LONG).show();
                    cancelLogin();
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    LoginActivity.this.finish();
                    startActivity(intent);
                }
            }

            @Override
            public void onDataNotAvailable() {
                binding.loadinglogin.setVisibility(View.GONE);
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                intent.putExtra("id", loginApiId);
                intent.putExtra("login_method", user.getUserLoginMethod());
                intent.putExtra("name", user.getUserName());
                intent.putExtra("email", user.getUserEmail());
                intent.putExtra("profile_img_url", user.getUserImageUrl());
                if(user.getUserLoginMethod().equals("google") || user.getUserLoginMethod().equals("kakao"))
                    intent.putExtra("isEmail", true);
                else
                    intent.putExtra("isEmail", false);
                startActivity(intent);
                finish();

                //Toast.makeText(getApplicationContext(), getString(R.string.strLoginedErr), Toast.LENGTH_LONG).show();
                //RxSocialLogin.logout(loginResultItem.getPlatform(), true);
                            /*Intent intent = getIntent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            LoginActivity.this.finish();
                            startActivity(intent);*/
            }
        });
    }



    //유저 식별 실패 시 로그아웃 메서드
    private void cancelLogin() {
        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
            SharedPreferences.Editor editor = prefLogin.edit();
            editor.putLong("profileEditDate", 0);
            editor.putString("id", "0");
            editor.putString("login_method", "0");
            editor.putString("name", "0");
            editor.putString("profile_img_url", "0");
            editor.putString("email", "fail");
            editor.putString("apiid","0");
            editor.putBoolean("EventAlarm", true);
            editor.putBoolean("OtherAlarm", true);
            Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
            editor.putString("locale",systemLocale.getLanguage());
            editor.apply();
        } else if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
            SharedPreferences.Editor editor = prefLogin.edit();
            editor.putLong("profileEditDate", 0);
            editor.putString("id", "0");
            editor.putString("login_method", "0");
            editor.putString("name", "0");
            editor.putString("profile_img_url", "0");
            editor.putString("email", "fail");
            editor.putString("apiid","0");
            editor.putBoolean("EventAlarm", true);
            editor.putBoolean("OtherAlarm", true);
            Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
            editor.putString("locale",systemLocale.getLanguage());
            editor.apply();
        } else if (!Session.getCurrentSession().isClosed()) {
            UserManagement.requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() {
                    SharedPreferences prefLogin = getApplicationContext().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putLong("profileEditDate", 0);
                    editor.putString("id", "0");
                    editor.putString("login_method", "0");
                    editor.putString("name", "0");
                    editor.putString("profile_img_url", "0");
                    editor.putString("email", "fail");
                    editor.putString("apiid","0");
                    editor.putBoolean("EventAlarm", true);
                    editor.putBoolean("OtherAlarm", true);
                    Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
                    editor.putString("locale",systemLocale.getLanguage());
                    editor.apply();
                }
            });
        } else if(lineApiClient.getCurrentAccessToken().isSuccess()) {
            new LineLogout().execute();
            SharedPreferences.Editor editor = prefLogin.edit();
            editor.putLong("profileEditDate", 0);
            editor.putString("id", "0");
            editor.putString("login_method", "0");
            editor.putString("name", "0");
            editor.putString("profile_img_url", "0");
            editor.putString("email", "fail");
            editor.putString("apiid","0");
            editor.putBoolean("EventAlarm", true);
            editor.putBoolean("OtherAlarm", true);
            Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
            editor.putString("locale",systemLocale.getLanguage());
            editor.apply();
        } else {
            SharedPreferences.Editor editor = prefLogin.edit();
            editor.putLong("profileEditDate", 0);
            editor.putString("id", "0");
            editor.putString("login_method", "0");
            editor.putString("name", "0");
            editor.putString("profile_img_url", "0");
            editor.putString("email", "fail");
            editor.putString("apiid","0");
            editor.putBoolean("EventAlarm", true);
            editor.putBoolean("OtherAlarm", true);
            Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
            editor.putString("locale",systemLocale.getLanguage());
            editor.apply();
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


    DialogInterface.OnKeyListener dialogBackKey = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_BACK) {
                cancelLogin();
                dialog.dismiss();
                return true;
            }
            return false;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(KakaoModule);
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
            overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
        } else {
            backPressTime = tempTime;
            showSnackBar(getResources().getString(R.string.strBackpressed));
        }
    }

    public void showSnackBar(String text) {
        Snackbar.make(binding.getRoot(), "" + text, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 튜토리얼 페이지 어댑터 */
    private class WelcomePagerAdapter extends FragmentStatePagerAdapter {

        private final int NUM_OF_PAGES = 3;

        public WelcomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return TutorialFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUM_OF_PAGES;
        }
    }
}
