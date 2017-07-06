package com.charlton.whatsin;

import com.charlton.whatsin.service.WhatsInService;
import com.charlton.whatsin.service.YoService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cj on 11/4/16.
 */
public class WhatsIn {
    private static WhatsIn ourInstance = new WhatsIn();
    private YoService yoService;

    public static WhatsIn getInstance() {
        return ourInstance;
    }

    public static WhatsIn getOurInstance() {
        return ourInstance;
    }

    public YoService getYoService() {
        return yoService;
    }


    private WhatsInService whatsInService = null;

    public static WhatsInService getWhatsInService() {
        return getInstance().whatsInService;
    }

    private WhatsIn() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();
        whatsInService = new Retrofit.Builder().baseUrl("http://world.openfoodfacts.org/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WhatsInService.class);

        yoService = new Retrofit.Builder().baseUrl("https://yoprice.co/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(YoService.class);
    }
}
