package com.example.jmmed.cdpowerbox;

import com.example.jmmed.cdpowerbox.Objetos.Reserva;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestReservaFutbol {
        //obtener un reserva
        @GET("reservas/futbol/{hora}")
        Call<Reserva> getReserva(@Path("hora") String hora);

        //modificar la reserva (crear)
        @PUT("reservas/futbol/{hora}")
        Call<Reserva> putReservaHora(@Body Reserva reserva, @Path("hora") String hora);
}
