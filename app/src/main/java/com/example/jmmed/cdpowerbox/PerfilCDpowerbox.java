package com.example.jmmed.cdpowerbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    private TextView txtsesion;
    private TextView txt_email;
    private TextView txt_nombre;
    private TextView txt_apellido;
    private TextView txt_telefono;
    private NavigationView menu;
    private View header;
    private TextView usuarioCabecera;
    private Button botonSalir;

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
        usuarioCabecera = (TextView) header.findViewById(R.id.usuario_cabecera);

        txtsesion = (TextView) findViewById(R.id.txt_sesion);
        txt_email = (TextView) findViewById(R.id.txtEmail);
        txt_nombre = (TextView) findViewById(R.id.txtNombre);
        txt_apellido = (TextView) findViewById(R.id.txtApellidos);
        txt_telefono = (TextView) findViewById(R.id.txtTelefono);
        usuarioCabecera.setText(user.getEmail());
        botonSalir = (Button) header.findViewById(R.id.boton_salir);

        usuariosRef.child(FirebaseReferences.USUARIO).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                txt_email.setText(usuario.getEmail());
                txt_nombre.setText(usuario.getNombre());
                txt_apellido.setText(usuario.getApellidos());
                txt_telefono.setText(usuario.getTelefono());
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
