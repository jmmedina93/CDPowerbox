package com.example.jmmed.cdpowerbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentoInicio extends Fragment{
    private TextView textBienvenida;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_inicio, container, false);
        textBienvenida = (TextView) view.findViewById(R.id.texto_bienvenida);
        return view;
    }

}
