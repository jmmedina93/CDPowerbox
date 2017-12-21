package com.example.jmmed.cdpowerbox;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//clase para obtener la conexion mediante el patron singleton
public class Conexion {
    private static Retrofit retrofit;
    final static String BASE_URL = "https://cdpowerbox.firebaseio.com/";
    private Conexion(){}

    public static Retrofit getRetrofit(){
        if(retrofit==null){
            Gson gson = new GsonBuilder() .setLenient() .create();
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
