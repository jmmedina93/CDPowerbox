package com.example.jmmed.cdpowerbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by jmmed on 12/11/2017.
 */



public class LoginCDpowerbox extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignIn;
    private EditText edtUser, edtPass;
    private TextView txtsignin;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener fiAuthStateListener;
    private static final String TAG = "POWERBOX";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logincdpowe_box);

        firebaseAuth = FirebaseAuth.getInstance();

        edtUser = (EditText) findViewById(R.id.login_user);
        edtPass = (EditText) findViewById(R.id.pass_user);
        btnSignIn = (Button) findViewById(R.id.btn_loginU);
        txtsignin = (TextView) findViewById(R.id.txt_signinup);

        fiAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if( firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(LoginCDpowerbox.this, PerfilCDpowerbox.class));
                }
            }
        };
        btnSignIn.setOnClickListener(this);
        txtsignin.setOnClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(fiAuthStateListener);
    }


    private void userLogin(){
        String email = edtUser.getText().toString();
        String password = edtPass.getText().toString();


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LoginCDpowerbox.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View view){
        if(view == btnSignIn){
            userLogin();
        }

        if(view == txtsignin){
            finish();
            startActivity(new Intent(this, CDpowerbox.class));
        }

    }
}
