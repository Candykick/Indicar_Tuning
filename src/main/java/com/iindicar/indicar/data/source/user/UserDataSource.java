package com.iindicar.indicar.data.source.user;

import com.iindicar.indicar.data.remote.LoadDataResponse;
import com.iindicar.indicar.data.remote.UserUpdateRequest;
import com.iindicar.indicar.data.source.UpdateData;
import com.iindicar.indicar.model.User;

public interface UserDataSource extends UpdateData<UserUpdateRequest> {

    void searchUserById(String userId, LoadDataCallback<User> callback);

    void searchUserByEmail(String userEmail, LoadDataCallback<User> callback);

    void addUser(String login_method, String name, String email, String profile_img_url, LoadDataCallback<User> response);

    //void checkEmailisExist(String email, LoadDataCallback<LoadDataResponse> response);

    void EmailAuthRequest(String email, String key, LoadDataCallback<LoadDataResponse> response);

    void EmailAuthSearch(String userId, LoadDataCallback<String> response);

    void removeAuth(String key, LoadDataCallback<LoadDataResponse> callback);

    void removeUser(String id, LoadDataCallback<LoadDataResponse> callback);

    void updateFCMToken(String id, String fcm_token, LoadDataCallback<LoadDataResponse> callback);

    interface LoadDataCallback<T> {

        void onDataLoaded(String result, T item);

        void onDataNotAvailable();
    }
}
