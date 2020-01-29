package com.example.equipo2_crudapp_android.client.retrofit;

import com.example.equipo2_crudapp_android.client.interfaces.ShopInterface;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ShopAPIClient {

    private static OkHttpClient.Builder httpClient;

    private static String API_BASE_URL = "http://localhost:8080/Equipo2_CRUDapp_Server/webresources/";

    public static ShopInterface getClient() {

        httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build()).build();

        ShopInterface shopInterface = retrofit.create(ShopInterface.class);

        return shopInterface;
    }

}
