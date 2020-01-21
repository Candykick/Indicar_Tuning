package com.iindicar.indicar.view.main.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.remote.LoadDataResponse;
import com.iindicar.indicar.data.source.user.UserDataSource;
import com.iindicar.indicar.data.source.user.UserRepository;
import com.iindicar.indicar.data.source.version.VersionDataSource;
import com.iindicar.indicar.data.source.version.VersionRepository;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.model.Version;
import com.iindicar.indicar.utils.App;
import com.iindicar.indicar.view.main.LanguageActivity;
import com.iindicar.indicar.view.main.MainActivity;
import com.iindicar.indicar.view.main.login.LoginActivity;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.api.LineApiClientBuilder;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    //로그인 관련 변수
    private static LineApiClient lineApiClient;
    String login_method, email, loginApiKey;
    boolean lineverify;

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // 상태바 글씨 검정
        }

        // 화면 크기 구해서 어플리케이션 전역변수로 저장해놓기
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        ((App) getApplicationContext()).setWindowWidth(size.x);
        ((App) getApplicationContext()).setWindowHeight(size.y);

        //라인 토큰 얻기 위한 것
        LineApiClientBuilder apiClientBuilder = new LineApiClientBuilder(getApplicationContext(),getString(R.string.line_channel_id));
        lineApiClient = apiClientBuilder.build();

        //앱 설치 후 첫 실행인지를 체크한다. 첫 실행이면 알람 셋팅과 국가 셋팅을 미리 해 둔다.
        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        SharedPreferences pref = getSharedPreferences("firstexe", MODE_PRIVATE);
        if (pref.getString("index", "").equals("")) {
            /*Intent i = new Intent(FirstActivity.this,GuideActivity.class);
            startActivity(i);*/
            //Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
            SharedPreferences.Editor editor2 = prefLogin.edit();
            editor2.putBoolean("EventAlarm", true);
            editor2.putBoolean("OtherAlarm", true);
            //editor2.putString("locale",systemLocale.getLanguage()); //ko이거나 아니거나.
            editor2.commit();

            Intent intent = new Intent(SplashActivity.this, LanguageActivity.class);
            startActivity(intent);
            finish();
        }

        try {
            login_method = prefLogin.getString("login_method","0");
            email = prefLogin.getString("email","fail");
            loginApiKey = prefLogin.getString("apiid","0");

            if(login_method.equals("kakao")) {
                if(!Session.getCurrentSession().isClosed()) {
                    versionCheck();
                } else {
                    redirectLoginActivitywithFail(getString(R.string.strLoginedErr));
                }
            } else if(login_method.equals("google")) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                    versionCheck();
                } else {
                    redirectLoginActivitywithFail(getString(R.string.strLoginedErr));
                }
            } else if(login_method.equals("facebook")) {
                if(AccessToken.getCurrentAccessToken() != null) {
                    if(loginApiKey.equals("0")) {
                        redirectLoginActivitywithFail(getString(R.string.strLoginedErr));
                    } else {
                        versionCheck();
                    }
                }
            } else if(login_method.equals("line")) {
                if(lineApiClient.getCurrentAccessToken().isSuccess()) {
                    if(loginApiKey.equals("0")) {
                        redirectLoginActivitywithFail(getString(R.string.strLoginedErr));
                    } else {
                        new LineTokenVerify().execute();
                    }
                }
            } else {
                versionCheck();
            }
        } catch(Exception e) {
            Log.e("Indicar Tuning Error",e.toString());
            Toast.makeText(getApplicationContext(), getString(R.string.strServerCheck), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //버전 체크하는 함수
    private void versionCheck() {
        VersionRepository.getInstance().getCurrentVersion(new VersionDataSource.LoadDataCallback<Version>() {
            @Override
            public void onDataLoaded(Version item) {
                try {
                    PackageInfo packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
                    int thisVersion = packageInfo.versionCode;
                    int latestVersion = item.getApp_version();
                    Log.d("Indicar Tuning",Integer.toString(latestVersion));

                    /*SharedPreferences prefLogin = getApplication().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    String thisDBVersion = prefLogin.getString("dbVersion", "1");
                    String latestDBVersion = item.getCar_spec_version();*/

                    if(thisVersion != latestVersion) {
                        String marketuri = "market://details?id=" + packageInfo.packageName;
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(marketuri));
                        finish();
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), getString(R.string.strVersionErr), Toast.LENGTH_SHORT).show();
                    } else {
                        loginCheck();
                        /*if(!thisDBVersion.equals(latestDBVersion)) {
                            new CarDBLoad().execute();
                        } else {
                            loginCheck();
                        }*/
                    }
                } catch (Exception e) {
                    Log.e("Indicar Tuning",e.toString());
                    Toast.makeText(getApplicationContext(), getString(R.string.strServerCheck), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onDataNotAvailable(String error) {
                if(error.equals("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.strServerCheck), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.strNoInternet), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    //로그인 체크하는 함수
    private void loginCheck() {
        if(!email.equals("fail")) {
            UserRepository.getInstance().searchUserByEmail(email, new UserDataSource.LoadDataCallback<User>() {
                @Override
                public void onDataLoaded(String result, User item) {
                    if (item != null) {
                        if (result.equals("S")) {
                            String result_login_method = item.getUserLoginMethod();
                            if (result_login_method.equals(login_method)) {
                                //User 셋팅
                                UserRepository.getInstance().setLoginUser(item);

                                //FCM 토큰 업데이트
                                UserRepository.getInstance().updateFCMToken(item.getUserId(), FirebaseInstanceId.getInstance().getToken(), new UserDataSource.LoadDataCallback<LoadDataResponse>() {
                                    @Override
                                    public void onDataLoaded(String result, LoadDataResponse item) {
                                        if(item.getResult().equals("S")) {
                                            startMainActivity();
                                        } else if(item.getContent().toString().contains("FCM 토큰값이 동일합니다.")) {
                                            startMainActivity();
                                        } else {
                                            redirectLoginActivitywithFail(getString(R.string.strLoginedErr));
                                        }
                                    }

                                    @Override
                                    public void onDataNotAvailable() {
                                        redirectLoginActivitywithFail(getString(R.string.strLoginedErr));
                                    }
                                });
                            } else {//해당 이메일로 가입된 정보가 사용자가 클릭한 login_method로 가입한 정보가 아닐 경우
                                redirectLoginActivitywithFail(getString(R.string.strEmailexisted1) + result_login_method + getString(R.string.strEmailexisted2));
                                Intent intent = getIntent();
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                SplashActivity.this.finish();
                                startActivity(intent);
                            }
                        } else if (result.equals("F")) {
                            redirectLoginActivitywithFail(getString(R.string.strLoginedErr));
                            Intent intent = getIntent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            SplashActivity.this.finish();
                            startActivity(intent);
                        }
                    } else {
                        redirectLoginActivitywithFail(getString(R.string.strLoginedErr));
                        Intent intent = getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        SplashActivity.this.finish();
                        startActivity(intent);
                    }
                }

                @Override
                public void onDataNotAvailable() {
                    startLoginActivity();

                    //Toast.makeText(getApplicationContext(), getString(R.string.strLoginedErr), Toast.LENGTH_LONG).show();
                    //RxSocialLogin.logout(loginResultItem.getPlatform(), true);
                            /*Intent intent = getIntent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            LoginActivity.this.finish();
                            startActivity(intent);*/
                }
            });
        } else {
            startLoginActivity();
        }
    }

    private void startLoginActivity(){
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            SplashActivity.this.finish();
        }, 500);
    }

    private void startMainActivity() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }, 500);
    }

    //로그인 실패 시 작동하는 함수
    private void redirectLoginActivitywithFail(final String strErr) {
        Toast.makeText(getApplicationContext(), strErr,Toast.LENGTH_SHORT).show();
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
            editor.putString("locale", systemLocale.getLanguage());
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
            editor.putString("locale", systemLocale.getLanguage());
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
                    editor.putString("locale", systemLocale.getLanguage());
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
            editor.putString("locale", systemLocale.getLanguage());
            editor.apply();
        }

        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
        SplashActivity.this.finish();
    }

    //라인 로그아웃 메서드
    private class LineLogout extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            lineApiClient.logout();
            return null;
        }
    }

    //라인 토큰 valid 메서드
    private class LineTokenVerify extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            LineApiResponse response = lineApiClient.verifyToken();
            lineverify = response.isSuccess();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(lineverify == true) {
                versionCheck();
            } else {
                redirectLoginActivitywithFail(getString(R.string.strLoginedErr));
            }
        }
    }
}
