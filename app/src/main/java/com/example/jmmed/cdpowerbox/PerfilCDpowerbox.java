package com.example.jmmed.cdpowerbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.jmmed.cdpowerbox.Objetos.FirebaseReferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerfilCDpowerbox extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private NavigationView menu;
    private View header;
    private Button botonSalir;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cdpowerbox);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usuariosRef = database.getReference(FirebaseReferences.USUARIOS);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        menu = (NavigationView) findViewById(R.id.navview);
        header = menu.getHeaderView(0);
        menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getTitle().toString()) {
                    case "reservar":
                        ft = fm.beginTransaction();
                        ft.addToBackStack("fragmento");
                        ft.replace(R.id.contenedor, new FragmentoReserva());
                        break;
                    case "datos_personales":
                        ft = fm.beginTransaction();
                        ft.addToBackStack("fragmento");
                        ft.replace(R.id.contenedor, new FragmentoReserva());
                        break;
                    default:
                        return true;
                }
                ft.commit();

                return true;

            }
        });
        botonSalir = (Button) header.findViewById(R.id.boton_salir);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        ft.replace(R.id.contenedor,new FragmentoPerfil());
        ft.commit();

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getBaseContext(), LoginCDpowerbox.class));
            }
        });

    }
}
