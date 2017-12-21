package com.example.jmmed.cdpowerbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jmmed.cdpowerbox.Objetos.FirebaseReferences;
import com.example.jmmed.cdpowerbox.Objetos.Reserva;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoReservaFutbol extends Fragment{
    Retrofit retrofit;
    RestReservaFutbol service;
    FirebaseDatabase database;
    DatabaseReference usuariosRef;
    FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    TextView textoFecha;
    TextView textoUsuarioDiezAOnce,textoUsuarioOnceADoce,textoUsuarioDoceAUna,textoUsuarioUnaAdos,textoUsuarioCuatroACinco,textoUsuarioCincoASeis,textoUsuarioSeisASiete,textoUsuarioSieteAOcho,textoUsuarioOchoANueve;
    Button botonDiezAOnce, botonOnceADoce, botonDoceAUna,botonUnaADos,botonCuatroACinco, botonCincoASeis,botonSeisASiete,botonSieteAOcho,botonOchoANueve;
    Button botonCancelarDiezAOnce, botonCancelarOnceADoce, botonCancelarDoceAUna,botonCancelarUnaADos,botonCancelarCuatroACinco, botonCancelarCincoASeis,botonCancelarSeisASiete,botonCancelarSieteAOcho,botonCancelarOchoANueve;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_reserva_futbol, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        usuariosRef = database.getReference(FirebaseReferences.USUARIOS);
        user = firebaseAuth.getCurrentUser();
        textoUsuarioDiezAOnce= (TextView) view.findViewById(R.id.texto_futbol_10_11);
        textoUsuarioOnceADoce= (TextView) view.findViewById(R.id.texto_futbol_11_12);
        textoUsuarioDoceAUna= (TextView) view.findViewById(R.id.texto_futbol_12_13);
        textoUsuarioUnaAdos= (TextView) view.findViewById(R.id.texto_futbol_13_14);
        textoUsuarioCuatroACinco= (TextView) view.findViewById(R.id.texto_futbol_16_17);
        textoUsuarioCincoASeis= (TextView) view.findViewById(R.id.texto_futbol_17_18);
        textoUsuarioSeisASiete= (TextView) view.findViewById(R.id.texto_futbol_18_19);
        textoUsuarioSieteAOcho= (TextView) view.findViewById(R.id.texto_futbol_19_20);
        textoUsuarioOchoANueve= (TextView) view.findViewById(R.id.texto_futbol_20_21);
        textoFecha= (TextView) view.findViewById(R.id.fecha_futbol);
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
        Date fecha=new Date();
        textoFecha.append(dateFormat.format(fecha));
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestReservaFutbol.class);


        //botones reservar
        botonDiezAOnce= (Button) view.findViewById(R.id.boton_futbol_10_11);
        botonDiezAOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"diez_once");
            }
        });
        botonOnceADoce= (Button) view.findViewById(R.id.boton_futbol_11_12);
        botonOnceADoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"once_doce");
            }
        });
        botonDoceAUna= (Button) view.findViewById(R.id.boton_futbol_12_13);
        botonDoceAUna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"doce_una");
            }
        });
        botonUnaADos= (Button) view.findViewById(R.id.boton_futbol_13_14);
        botonUnaADos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"una_dos");
            }
        });
        botonCuatroACinco= (Button) view.findViewById(R.id.boton_futbol_16_17);
        botonCuatroACinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"cuatro_cinco");
            }
        });
        botonCincoASeis= (Button) view.findViewById(R.id.boton_futbol_17_18);
        botonCincoASeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"cinco_seis");
            }
        });
        botonSeisASiete= (Button) view.findViewById(R.id.boton_futbol_18_19);
        botonSeisASiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"seis_siete");
            }
        });
        botonSieteAOcho= (Button) view.findViewById(R.id.boton_futbol_19_20);
        botonSieteAOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"siete_ocho");
            }
        });
        botonOchoANueve= (Button) view.findViewById(R.id.boton_futbol_20_21);
        botonOchoANueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"ocho_nueve");
            }
        });



        //Botones cancelar
        botonCancelarDiezAOnce= (Button) view.findViewById(R.id.boton_futbol_cancelar_10_11);
        botonCancelarDiezAOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(""),"diez_once");
            }
        });
        botonCancelarOnceADoce= (Button) view.findViewById(R.id.boton_futbol_cancelar_11_12);
        botonCancelarOnceADoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(""),"once_doce");
            }
        });
        botonCancelarDoceAUna= (Button) view.findViewById(R.id.boton_futbol_cancelar_12_13);
        botonCancelarDoceAUna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(""),"doce_una");
            }
        });
        botonCancelarUnaADos= (Button) view.findViewById(R.id.boton_futbol_cancelar_13_14);
        botonCancelarUnaADos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(""),"una_dos");
            }
        });
        botonCancelarCuatroACinco= (Button) view.findViewById(R.id.boton_futbol_cancelar_16_17);
        botonCancelarCuatroACinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"cuatro_cinco");
            }
        });
        botonCancelarCincoASeis= (Button) view.findViewById(R.id.boton_futbol_cancelar_17_18);
        botonCancelarCincoASeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"cinco_seis");
            }
        });
        botonCancelarSeisASiete= (Button) view.findViewById(R.id.boton_futbol_cancelar_18_19);
        botonCancelarSeisASiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"seis_siete");
            }
        });
        botonCancelarSieteAOcho= (Button) view.findViewById(R.id.boton_futbol_cancelar_19_20);
        botonCancelarSieteAOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"siete_ocho");
            }
        });
        botonCancelarOchoANueve= (Button) view.findViewById(R.id.boton_futbol_cancelar_20_21);
        botonCancelarOchoANueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearReserva(new Reserva(user.getEmail()),"ocho_nueve");
            }
        });


        obtenerReserva("diez_once");
        obtenerReserva("once_doce");
        obtenerReserva("doce_una");
        obtenerReserva("una_dos");
        obtenerReserva("cuatro_cinco");
        obtenerReserva("cinco_seis");
        obtenerReserva("seis_siete");
        obtenerReserva("siete_ocho");
        obtenerReserva("ocho_nueve");
        return view;
    }
    private void obtenerReserva(final String hora) {
        Call<Reserva> call = service.getReserva(hora+".json");
        call.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                Reserva reserva = response.body();

                Log.v("prueba", reserva.getEmail());
                switch (hora){
                    case "diez_once":
                        textoUsuarioDiezAOnce.setText(reserva.getEmail());
                        if(reserva.getEmail().length()!=0)
                            botonDiezAOnce.setEnabled(false);
                        if(reserva.getEmail().length()==0 || !reserva.getEmail().equals(user.getEmail()))
                            botonCancelarDiezAOnce.setEnabled(false);
                        break;
                    case "once_doce":
                        textoUsuarioOnceADoce.setText(reserva.getEmail());
                        if(reserva.getEmail().length()!=0)
                            botonOnceADoce.setEnabled(false);
                        if(reserva.getEmail().length()==0 || !reserva.getEmail().equals(user.getEmail()))
                            botonCancelarOnceADoce.setEnabled(false);
                        break;
                    case "doce_una":
                        textoUsuarioDoceAUna.setText(reserva.getEmail());
                        if(reserva.getEmail().length()!=0)
                            botonDoceAUna.setEnabled(false);
                        if(reserva.getEmail().length()==0 || !reserva.getEmail().equals(user.getEmail()))
                            botonCancelarDoceAUna.setEnabled(false);
                        break;
                    case "una_dos":
                        textoUsuarioUnaAdos.setText(reserva.getEmail());
                        if(reserva.getEmail().length()!=0)
                            botonUnaADos.setEnabled(false);
                        if(reserva.getEmail().length()==0 || !reserva.getEmail().equals(user.getEmail()))
                            botonCancelarUnaADos.setEnabled(false);
                        break;
                    case "cuatro_cinco":
                        textoUsuarioCuatroACinco.setText(reserva.getEmail());
                        if(reserva.getEmail().length()!=0)
                            botonCuatroACinco.setEnabled(false);
                        if(reserva.getEmail().length()==0 || !reserva.getEmail().equals(user.getEmail()))
                            botonCancelarCuatroACinco.setEnabled(false);
                        break;
                    case "cinco_seis":
                        textoUsuarioCincoASeis.setText(reserva.getEmail());
                        if(reserva.getEmail().length()!=0)
                            botonCincoASeis.setEnabled(false);
                        if(reserva.getEmail().length()==0 || !reserva.getEmail().equals(user.getEmail()))
                            botonCancelarCincoASeis.setEnabled(false);
                        break;
                    case "seis_siete":
                        textoUsuarioSeisASiete.setText(reserva.getEmail());
                        if(reserva.getEmail().length()!=0)
                            botonSeisASiete.setEnabled(false);
                        if(reserva.getEmail().length()==0 || !reserva.getEmail().equals(user.getEmail()))
                            botonCancelarSeisASiete.setEnabled(false);
                        break;
                    case "siete_ocho":
                        textoUsuarioSieteAOcho.setText(reserva.getEmail());
                        if(reserva.getEmail().length()!=0)
                            botonSieteAOcho.setEnabled(false);
                        if(reserva.getEmail().length()==0 || !reserva.getEmail().equals(user.getEmail()))
                            botonCancelarSieteAOcho.setEnabled(false);
                        break;
                    case "ocho_nueve":
                        textoUsuarioOchoANueve.setText(reserva.getEmail());
                        if(reserva.getEmail().length()!=0)
                            botonOchoANueve.setEnabled(false);
                        if(reserva.getEmail().length()==0 || !reserva.getEmail().equals(user.getEmail()))
                            botonCancelarOchoANueve.setEnabled(false);
                        break;
                }

            }

            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {
                Log.e("prueba", t.getMessage());
            }
        });
    }

    private void crearReserva(Reserva reserva , final String hora) {
        Call<Reserva> call = service.putReservaHora(reserva,hora+".json");
        call.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                Reserva reserva = response.body();

                Log.v("prueba", reserva.getEmail());
                switch (hora){
                    case "diez_once":
                        textoUsuarioDiezAOnce.setText(reserva.getEmail());
                        break;
                    case "once_doce":
                        textoUsuarioOnceADoce.setText(reserva.getEmail());
                        break;
                    case "doce_una":
                        textoUsuarioDoceAUna.setText(reserva.getEmail());
                        break;
                    case "una_dos":
                        textoUsuarioUnaAdos.setText(reserva.getEmail());
                        break;
                    case "cuatro_cinco":
                        textoUsuarioCuatroACinco.setText(reserva.getEmail());
                        break;
                    case "cinco_seis":
                        textoUsuarioCincoASeis.setText(reserva.getEmail());
                        break;
                    case "seis_siete":
                        textoUsuarioSeisASiete.setText(reserva.getEmail());
                        break;
                    case "siete_ocho":
                        textoUsuarioSieteAOcho.setText(reserva.getEmail());
                        break;
                    case "ocho_nueve":
                        textoUsuarioOchoANueve.setText(reserva.getEmail());
                        break;
                }

            }

            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {
                Log.e("prueba", t.getMessage());
            }
        });
    }
}
