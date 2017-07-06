package com.charlton.whatsin.service;

import com.charlton.whatsin.data.models.UPC;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by cj on 11/4/16.
 */

public interface WhatsInService {

    @GET("api/v0/product/{upc}.json")
    Observable<UPC> getUPC(@Path("upc") String upc);

}
