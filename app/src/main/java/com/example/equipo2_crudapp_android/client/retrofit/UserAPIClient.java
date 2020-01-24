package com.example.equipo2_crudapp_android.client.retrofit;

import com.example.equipo2_crudapp_android.client.interfaces.UserInterface;

import java.util.logging.XMLFormatter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class UserAPIClient {

    private static String API_BASE_URL = "http://localhost:8080/Equipo2_CRUDapp_Server/webresources";

    public static UserInterface getClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build()).build();

        UserInterface userInterface = retrofit.create(UserInterface.class);

        return userInterface;
    }

}
