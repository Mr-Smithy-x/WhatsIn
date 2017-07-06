package com.charlton.whatsin.service;

import com.charlton.whatsin.data.models.Response;
import com.charlton.whatsin.data.models.UserModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cj on 11/5/16.
 */

public interface YoService {
    @GET("api/upc/login")
    Observable<Response<UserModel>> login(@Query("user") String user, @Query("pass") String pass);

    @GET("api/upc/register")
    Observable<Response<UserModel>> register(@Query("user") String user, @Query("pass") String pass, @Query("email") String email);

}
