package com.example.jmmed.cdpowerbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentoReserva extends Fragment{
    private Button botonFutbol, botonTenis;
    private FragmentManager fm;
    private FragmentTransaction ft;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_reserva, container, false);
        fm=getFragmentManager();
        ft=fm.beginTransaction();
        botonFutbol= (Button) view.findViewById(R.id.boton_reserva_futbol);
        botonTenis= (Button) view.findViewById(R.id.boton_reserva_tenis);

        botonFutbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.replace(R.id.contenedor,new FragmentoReservaFutbol()).commit();
            }
        });

        botonTenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.replace(R.id.contenedor,new FragmentoReservaTenis()).commit();
            }
        });
        return view;
    }

}
