package com.video.aashi.kmdk.Interface;

import com.video.aashi.kmdk.Urls.APIUrl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    public static Retrofit retrofit;

    public void NetworkClient(){

    }

    public static Retrofit getRetrofit(){

        if(retrofit==null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                        .baseUrl(APIUrl.Api)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                        .client(okHttpClient)
                        .build();
        }

        return retrofit;
    }

}
