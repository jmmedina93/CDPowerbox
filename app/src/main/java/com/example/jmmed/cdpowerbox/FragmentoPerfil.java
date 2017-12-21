package com.example.jmmed.cdpowerbox;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


public class FragmentoPerfil extends Fragment{
    private FirebaseAuth firebaseAuth;
    private TextView txtsesion;
    private EditText edt_email;
    private EditText edt_nombre;
    private EditText edt_apellido;
    private EditText edt_telefono;
    private TextView usuarioCabecera;
    private Button btn_guardar;
    private FragmentManager fm;
    private FragmentTransaction ft;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_perfil,container,false);
        btn_guardar = (Button) view.findViewById(R.id.btnGuardar);
        txtsesion = (TextView) view.findViewById(R.id.txt_sesion);
        edt_email = (EditText) view.findViewById(R.id.edtEmail);
        edt_nombre = (EditText) view.findViewById(R.id.edtNombre);
        edt_apellido = (EditText) view.findViewById(R.id.edtApellidos);
        edt_telefono = (EditText) view.findViewById(R.id.edtTelefono);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference usuariosRef = database.getReference(FirebaseReferences.USUARIOS);
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        NavigationView menu = (NavigationView) getActivity().findViewById(R.id.navview);
        View header = menu.getHeaderView(0);
        usuarioCabecera = (TextView) header.findViewById(R.id.usuario_cabecera);
        usuarioCabecera.setText(user.getEmail());
        FirebaseReferences.USUARIO=user.getEmail().toString().split("@")[0];
        Log.v("prueba", FirebaseReferences.USUARIO );
        usuariosRef.child(FirebaseReferences.USUARIO).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                final Usuario usuario = dataSnapshot.getValue(Usuario.class);
                if (usuario!=null) {
                    edt_email.setText(usuario.getEmail());
                    edt_nombre.setText(usuario.getNombre());
                    edt_apellido.setText(usuario.getApellidos());
                    edt_telefono.setText(usuario.getTelefono());
                }else{
                    usuariosRef.child(user.getEmail().split("@")[0]).child("email").setValue(user.getEmail());
                    usuariosRef.child(user.getEmail().split("@")[0]).child("nombre").setValue("");
                    usuariosRef.child(user.getEmail().split("@")[0]).child("apellidos").setValue("");
                    usuariosRef.child(user.getEmail().split("@")[0]).child("telefono").setValue("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setNombre(edt_nombre.getText().toString());
                usuario.setEmail(user.getEmail().toString());
                usuario.setApellidos(edt_apellido.getText().toString());
                usuario.setTelefono(edt_telefono.getText().toString());
                usuariosRef.child(usuario.getEmail().split("@")[0]).child("nombre").setValue(usuario.getNombre());
                usuariosRef.child(usuario.getEmail().split("@")[0]).child("apellidos").setValue(usuario.getApellidos());
                usuariosRef.child(usuario.getEmail().split("@")[0]).child("telefono").setValue(usuario.getTelefono());
            }
        });

        return view;
    }
}
