package com.iindicar.indicar.data.source.user;

import android.content.Context;
import android.content.SharedPreferences;

import com.iindicar.indicar.data.remote.LoadDataResponse;
import com.iindicar.indicar.data.remote.UserUpdateRequest;
import com.iindicar.indicar.model.User;

import io.reactivex.Observable;

public class UserRepository implements UserDataSource {

    private static UserRepository userRepository;

    private UserRemoteDataSource remoteDataSource;

    private User loginUser;

    private UserRepository(){
        remoteDataSource = UserRemoteDataSource.getInstance();
    }

    public static UserRepository getInstance(){
        if(userRepository == null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public void searchUserById(String userId, LoadDataCallback<User> callback) {
        remoteDataSource.searchUserById(userId, callback);
    }

    @Override
    public void searchUserByEmail(String userEmail, LoadDataCallback<User> callback) {
        remoteDataSource.searchUserByEmail(userEmail, callback);
    }

    @Override
    public void addUser(String login_method, String name, String email, String profile_img_url, LoadDataCallback<User> callback) {
        remoteDataSource.addUser(login_method, name, email, profile_img_url, callback);
    }

    @Override
    public void EmailAuthRequest(String email, String key, LoadDataCallback<LoadDataResponse> callback) {
        remoteDataSource.EmailAuthRequest(email, key, callback);
    }

    @Override
    public void EmailAuthSearch(String userId, LoadDataCallback<String> response) {
        remoteDataSource.EmailAuthSearch(userId, response);
    }

    @Override
    public void removeAuth(String key, LoadDataCallback<LoadDataResponse> callback) {
        remoteDataSource.removeAuth(key, callback);
    }

    @Override
    public void removeUser(String id, LoadDataCallback<LoadDataResponse> callback) {
        remoteDataSource.removeUser(id, callback);
    }

    @Override
    public void updateFCMToken(String id, String fcm_token, LoadDataCallback<LoadDataResponse> callback) {
        remoteDataSource.updateFCMToken(id, fcm_token, callback);
    }

    @Override
    public Observable<String> updateData(UserUpdateRequest source) {
        return remoteDataSource.updateData(source);
    }
}
