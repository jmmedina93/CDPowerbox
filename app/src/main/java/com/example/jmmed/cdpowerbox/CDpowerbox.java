package com.example.jmmed.cdpowerbox;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CDpowerbox extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText passAdmin, userAdmin;
    private TextView txtSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdpower_box);


        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog  = new ProgressDialog(this);

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), PerfilCDpowerbox.class));
        }

        btnLogin = (Button) findViewById(R.id.btn_login);

        passAdmin = (EditText) findViewById(R.id.pass_admin);
        userAdmin = (EditText) findViewById(R.id.login_admin);

        txtSignin = (TextView) findViewById(R.id.txt_signnin);

        btnLogin.setOnClickListener(this);
        txtSignin.setOnClickListener(this);

    }
    private void registerUser(){
        String user = userAdmin.getText().toString().trim();
        String pass = passAdmin.getText().toString().trim();

        if(TextUtils.isEmpty(user)){
            //El usuario esta vacio
            Toast.makeText(this,"Introduce el Usuario o Email.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            //Contraseña vacia
            Toast.makeText(this,"Introduce la contraseña.", Toast.LENGTH_SHORT).show();
            return;
        }
        //Si la validacion esta correcta seguiremos con el Progressbar

        progressDialog.setMessage("Registrando Usuario.");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                        }else{
                            Toast.makeText(CDpowerbox.this, "No se a registrado con exito. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void onClick (View view){
        if (view == btnLogin){
            registerUser();
        }
        if (view == txtSignin){
            //Aqui pondre el codigo para iniciar sesion
            startActivity(new Intent(this, LoginCDpowerbox.class));
        }
    }
}
