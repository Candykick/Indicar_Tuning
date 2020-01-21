package com.iindicar.indicar.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.iindicar.indicar.data.remote.LoadDataResponse;
import com.iindicar.indicar.data.source.user.UserDataSource;
import com.iindicar.indicar.data.source.user.UserRepository;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by candykick on 2018. 5. 7..
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed token : "+token);

        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String id = prefLogin.getString("id", "");

        if(!id.equals("")) {
            sendRegistrationToServer(id, token);
        }
    }

    private void sendRegistrationToServer(String id, String token) {
        String fcmInputData[] = new String[]{id, token};

        UserRepository.getInstance().updateFCMToken(id, token, new UserDataSource.LoadDataCallback<LoadDataResponse>() {
            @Override
            public void onDataLoaded(String result, LoadDataResponse item) {
                if(item.getResult().equals("S") || item.getContent().toString().contains("FCM 토큰값이 동일합니다.")) {

                } else { //업데이트 실패

                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
