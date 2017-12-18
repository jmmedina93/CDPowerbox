package com.example.jmmed.cdpowerbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jmmed.cdpowerbox.Objetos.Reserva;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoReserva extends Fragment{
    Retrofit retrofit;
    RestReserva service;
    TextView textoReserva;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_reserva, container, false);
        textoReserva= (TextView) view.findViewById(R.id.texto_reserva);


        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestReserva.class);



        obtenerReserva("seis_siete");
        return view;
    }
    private void obtenerReserva(String hora) {
        Call<Reserva> call = service.getReserva(hora+".json");
        call.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                Reserva reserva = response.body();

                Log.v("prueba", reserva.getEmail());
                textoReserva.setText(reserva.getEmail());
            }

            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {
                Log.e("prueba", t.getMessage());
            }
        });
    }
}
