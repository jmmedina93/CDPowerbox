package com.example.jmmed.cdpowerbox;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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

/**
 * Created by david on 4/12/17.
 */

public class FragmentoPerfil extends Fragment{
    private FirebaseAuth firebaseAuth;
    private TextView txtsesion;
    private EditText edt_email;
    private EditText edt_nombre;
    private EditText edt_apellido;
    private EditText edt_telefono;
    private TextView usuarioCabecera;
    private Button btn_guardar;


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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usuariosRef = database.getReference(FirebaseReferences.USUARIOS);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        NavigationView menu = (NavigationView) getActivity().findViewById(R.id.navview);
        View header = menu.getHeaderView(0);
        usuarioCabecera = (TextView) header.findViewById(R.id.usuario_cabecera);
        usuarioCabecera.setText(user.getEmail());
        usuariosRef.child(FirebaseReferences.USUARIO).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                final Usuario usuario = dataSnapshot.getValue(Usuario.class);
                edt_email.setText(usuario.getEmail());
                edt_nombre.setText(usuario.getNombre());
                edt_apellido.setText(usuario.getApellidos());
                edt_telefono.setText(usuario.getTelefono());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }
}
