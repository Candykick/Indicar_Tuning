package com.iindicar.indicar.data.source.version;

import android.os.Build;

import com.iindicar.indicar.data.remote.RetrofitApi;
import com.iindicar.indicar.data.remote.RetrofitClient;
import com.iindicar.indicar.model.Version;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VersionRepository implements VersionDataSource {

    private static VersionRepository VersionRepository;

    public static VersionRepository getInstance() {

        if (VersionRepository == null) {
            VersionRepository = new VersionRepository();
        }
        return VersionRepository;
    }

    private VersionRepository() { }

    @Override
    public void getCurrentVersion(final LoadDataCallback<Version> callback) {
        RetrofitClient.getClient().create(RetrofitApi.class)
                .getCurrentVersion()
                .enqueue(new Callback<Version>() {
                    @Override
                    public void onResponse(Call<Version> call, Response<Version> response) {
                        if(response.isSuccessful()) {
                            if(Build.VERSION.SDK_INT >= 19) {
                                callback.onDataLoaded(Objects.requireNonNull(response.body()));
                            } else {
                                callback.onDataLoaded(response.body());
                            }
                        } else {
                            callback.onDataNotAvailable("");
                        }
                    }

                    @Override
                    public void onFailure(Call<Version> call, Throwable t) {
                        callback.onDataNotAvailable(t.getMessage().toString());
                    }
                });
    }

}

