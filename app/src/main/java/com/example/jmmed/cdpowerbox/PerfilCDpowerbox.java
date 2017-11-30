package com.example.jmmed.cdpowerbox;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilCDpowerbox extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView txtsesion;
    private NavigationView menu;
    private View header;
    private TextView usuarioCabecera;
    private Button botonSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cdpowerbox);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        menu = (NavigationView) findViewById(R.id.navview);
        header = menu.getHeaderView(0);
        usuarioCabecera = (TextView) header.findViewById(R.id.usuario_cabecera);

        txtsesion = (TextView) findViewById(R.id.txt_sesion);
        usuarioCabecera.setText(user.getEmail());
        txtsesion.setText("Bienvenido " + user.getEmail() );
        botonSalir = (Button) header.findViewById(R.id.boton_salir);

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getBaseContext(), LoginCDpowerbox.class));
            }
        });

    }
}
