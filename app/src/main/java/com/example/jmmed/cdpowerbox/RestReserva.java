package com.example.jmmed.cdpowerbox;

import com.example.jmmed.cdpowerbox.Objetos.Reserva;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RestReserva {
        //obtener un cliente
        @GET("reservas/{hora}")
        Call<Reserva> getReserva(@Path("hora") String hora);
/*
        @GET("alquiler")
        Call<Alquiler[]> getAlquileres();

        @POST("/alquiler")
        Call<Respuesta> postAlquiler(@Body Alquiler alquiler);

        @PUT("alquiler/{dni}/{tituloPelicula}/{fechaAlquiler}/{fechaEntrega}")
        Call<Respuesta> putAlquilerDni(@Body Alquiler alquiler, @Path("dni") String dni,@Path("tituloPelicula") String tituloPelicula,@Path("fechaAlquiler") String fechaAlquiler,@Path("fechaEntrega") String fechaEntrega);

        @DELETE("alquiler/{tituloPelicula}/{dni}/{fechaAlquiler}/{fechaEntrega}")
        Call<Respuesta> deleteAlquilerDni(@Path("tituloPelicula") String tituloPelicula, @Path("dni") String dni,@Path("fechaAlquiler") String fechaAlquiler,@Path("fechaEntrega") String fechaEntrega);
*/
}
