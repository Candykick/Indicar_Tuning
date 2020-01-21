package com.iindicar.indicar.data.source.user;

import android.os.Build;
import android.support.annotation.NonNull;

import com.iindicar.indicar.data.remote.LoadDataResponse;
import com.iindicar.indicar.data.remote.RetrofitApi;
import com.iindicar.indicar.data.remote.RetrofitClient;
import com.iindicar.indicar.data.remote.UserUpdateRequest;
import com.iindicar.indicar.model.User;

import java.util.Objects;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRemoteDataSource implements UserDataSource {

    private static UserRemoteDataSource userRemoteDataSource;

    private UserRemoteDataSource(){}

    public static UserRemoteDataSource getInstance(){
        if(userRemoteDataSource == null){
            userRemoteDataSource = new UserRemoteDataSource();
        }
        return userRemoteDataSource;
    }

    @Override
    public void searchUserById(String userId, final LoadDataCallback<User> callback) {
        // 유저정보 api 요청
        RetrofitClient.getClient().create(RetrofitApi.class)
                .searchUserById(userId)
                .enqueue(new Callback<LoadDataResponse<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<LoadDataResponse<User>> call, @NonNull Response<LoadDataResponse<User>> response) {
                        if (response.isSuccessful()) {
                            if(Build.VERSION.SDK_INT >= 19)
                                callback.onDataLoaded(Objects.requireNonNull(response.body()).getResult(), Objects.requireNonNull(response.body()).getContent());
                            else
                                callback.onDataLoaded(response.body().getResult(), response.body().getContent());
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoadDataResponse<User>> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void searchUserByEmail(String userEmail, final LoadDataCallback<User> callback) {
        // 유저정보 api 요청
        RetrofitClient.getClient().create(RetrofitApi.class)
                .searchUserByEmail(userEmail)
                .enqueue(new Callback<LoadDataResponse<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<LoadDataResponse<User>> call, @NonNull Response<LoadDataResponse<User>> response) {
                        if (response.isSuccessful()) {
                            if(Build.VERSION.SDK_INT >= 19)
                                callback.onDataLoaded(Objects.requireNonNull(response.body()).getResult(), Objects.requireNonNull(response.body()).getContent());
                            else
                                callback.onDataLoaded(response.body().getResult(), response.body().getContent());
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoadDataResponse<User>> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void addUser(String login_method, String name, String email, String profile_img_url, final LoadDataCallback<User> callback) {
        //회원가입 요청
        RetrofitClient.getClient().create(RetrofitApi.class)
                .addUser(login_method, name, email, "USR", profile_img_url)
                .enqueue(new Callback<LoadDataResponse<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<LoadDataResponse<User>> call, @NonNull Response<LoadDataResponse<User>> response) {
                        if (response.isSuccessful()) {
                            if(Build.VERSION.SDK_INT >= 19)
                                callback.onDataLoaded(Objects.requireNonNull(response.body()).getResult(), Objects.requireNonNull(response.body()).getContent());
                            else
                                callback.onDataLoaded(response.body().getResult(), response.body().getContent());
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoadDataResponse<User>> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void EmailAuthRequest(String email, String key, final LoadDataCallback<LoadDataResponse> callback) {
        //이메일 인증 요청
        RetrofitClient.getClient().create(RetrofitApi.class)
                .EmailAuthRequest(key, email)
                .enqueue(new Callback<LoadDataResponse>() {
                    @Override
                    public void onResponse(Call<LoadDataResponse> call, Response<LoadDataResponse> response) {
                        if (response.isSuccessful()) {
                            if(Build.VERSION.SDK_INT >= 19)
                                callback.onDataLoaded(Objects.requireNonNull(response.body()).getResult(), null);
                            else
                                callback.onDataLoaded(response.body().getResult(), null);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoadDataResponse> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void
    EmailAuthSearch(String userId, final LoadDataCallback<String> callback) {
        RetrofitClient.getClient().create(RetrofitApi.class)
                .EmailAuthSearch(userId)
                .enqueue(new Callback<LoadDataResponse>() {
                    @Override
                    public void onResponse(Call<LoadDataResponse> call, Response<LoadDataResponse> response) {
                        if (response.isSuccessful()) {
                            if(Build.VERSION.SDK_INT >= 19)
                                callback.onDataLoaded(Objects.requireNonNull(response.body()).getResult(), Objects.requireNonNull(response.body()).getContent().toString());
                            else
                                callback.onDataLoaded(response.body().getResult(), response.body().getContent().toString());
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoadDataResponse> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void removeAuth(String key, final LoadDataCallback<LoadDataResponse> callback) {
        //DB에서 이메일 인증 삭제
        RetrofitClient.getClient().create(RetrofitApi.class)
                .removeAuth(key)
                .enqueue(new Callback<LoadDataResponse>() {
                    @Override
                    public void onResponse(Call<LoadDataResponse> call, Response<LoadDataResponse> response) {
                        if (response.isSuccessful()) {
                            if(Build.VERSION.SDK_INT >= 19)
                                callback.onDataLoaded(Objects.requireNonNull(response.body()).getResult(), null);
                            else
                                callback.onDataLoaded(response.body().getResult(), null);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoadDataResponse> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void removeUser(String id, final LoadDataCallback<LoadDataResponse> callback) {
        //DB에서 계정 삭제
        RetrofitClient.getClient().create(RetrofitApi.class)
                .removeUser(id)
                .enqueue(new Callback<LoadDataResponse>() {
                    @Override
                    public void onResponse(Call<LoadDataResponse> call, Response<LoadDataResponse> response) {
                        if (response.isSuccessful()) {
                            if(Build.VERSION.SDK_INT >= 19)
                                callback.onDataLoaded(Objects.requireNonNull(response.body()).getResult(), null);
                            else
                                callback.onDataLoaded(response.body().getResult(), null);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoadDataResponse> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void updateFCMToken(String id, String fcm_token, final LoadDataCallback<LoadDataResponse> callback) {
        //DB에 FCM Token값 업데이트
        RetrofitClient.getClient().create(RetrofitApi.class)
                .updateFCMToken(id, fcm_token)
                .enqueue(new Callback<LoadDataResponse>() {
                    @Override
                    public void onResponse(Call<LoadDataResponse> call, Response<LoadDataResponse> response) {
                        if (response.isSuccessful()) {
                            if(Build.VERSION.SDK_INT >= 19)
                                callback.onDataLoaded(Objects.requireNonNull(response.body()).getResult(), Objects.requireNonNull(response.body()));
                            else
                                callback.onDataLoaded(response.body().getResult(), response.body());
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoadDataResponse> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public Observable<String> updateData(UserUpdateRequest source) {
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .updateUserName(source)
                .map(LoadDataResponse::getContent);
    }
}
