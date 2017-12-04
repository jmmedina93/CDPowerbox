package com.example.jmmed.cdpowerbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jmmed.cdpowerbox.Objetos.FirebaseReferences;
import com.example.jmmed.cdpowerbox.Objetos.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilCDpowerbox extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    /*private TextView txtsesion;
    private EditText edt_email;
    private EditText edt_nombre;
    private EditText edt_apellido;
    private EditText edt_telefono;*/
    private NavigationView menu;
    private View header;
    //private TextView usuarioCabecera;
    private Button botonSalir;
    //private Button btn_guardar;
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
        //usuarioCabecera = (TextView) header.findViewById(R.id.usuario_cabecera);
        /*btn_guardar = (Button) findViewById(R.id.btnGuardar);
        txtsesion = (TextView) findViewById(R.id.txt_sesion);
        edt_email = (EditText) findViewById(R.id.edtEmail);
        edt_nombre = (EditText) findViewById(R.id.edtNombre);
        edt_apellido = (EditText) findViewById(R.id.edtApellidos);
        edt_telefono = (EditText) findViewById(R.id.edtTelefono);
        usuarioCabecera.setText(user.getEmail());*/
        botonSalir = (Button) header.findViewById(R.id.boton_salir);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        ft.replace(R.id.contenedor,new FragmentoPerfil());
        ft.commit();
        usuariosRef.child(FirebaseReferences.USUARIO).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                final Usuario usuario = dataSnapshot.getValue(Usuario.class);
                /*edt_email.setText(usuario.getEmail());
                edt_nombre.setText(usuario.getNombre());
                edt_apellido.setText(usuario.getApellidos());
                edt_telefono.setText(usuario.getTelefono());*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getBaseContext(), LoginCDpowerbox.class));
            }
        });

    }
}
