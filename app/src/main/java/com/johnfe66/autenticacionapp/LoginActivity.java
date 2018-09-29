package com.johnfe66.autenticacionapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txtLoginEmail)
    TextInputEditText txtLoginEmail;
    @BindView(R.id.textInputLayout3)
    TextInputLayout textInputLayout3;
    @BindView(R.id.txtLoginPassword)
    TextInputEditText txtLoginPassword;
    @BindView(R.id.textInputLayout2)
    TextInputLayout textInputLayout2;
    @BindView(R.id.btnLoginEntrar)
    Button btnLoginEntrar;
    @BindView(R.id.btnLoginRegistrar)
    Button btnLoginRegistrar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioActual = mAuth.getCurrentUser();
        actualizarUsuario(usuarioActual);
    }

    private void actualizarUsuario(FirebaseUser usuarioActual) {
        if (usuarioActual != null) {
           if(usuarioActual.isEmailVerified()){
               startActivity(new Intent(LoginActivity.this, MainActivity.class));
               finish();

           }else {
               Toast.makeText(LoginActivity.this, "Debe Verificar su email", Toast.LENGTH_SHORT).show();
           }





        }

    }

    @OnClick(R.id.btnLoginEntrar)
    public void onBtnLoginEntrarClicked() {

        mAuth.signInWithEmailAndPassword(txtLoginEmail.getText().toString().trim(), txtLoginPassword.getText().toString().trim())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("[Login user]", "Usuario Logueado OK");
                            FirebaseUser user = mAuth.getCurrentUser();
                            actualizarUsuario(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("[Login user]", "Usuario logueado FAIL ", task.getException());
                            Toast.makeText(LoginActivity.this, "Loguin Fallido.",
                                    Toast.LENGTH_SHORT).show();
                            actualizarUsuario(null);
                        }

                        // ...
                    }
                });
    }

    @OnClick(R.id.btnLoginRegistrar)
    public void onBtnLoginRegistrarClicked() {
        mAuth.createUserWithEmailAndPassword(txtLoginEmail.getText().toString().trim(), txtLoginPassword.getText().toString().trim())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("[Login OK]", "Creacion de usuario OK");
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification().addOnCompleteListener(LoginActivity.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()==true)
                                       Toast.makeText(LoginActivity.this, "Email Enaviado", Toast.LENGTH_SHORT).show();

                                    else
                                        Toast.makeText(LoginActivity.this, "Email Enaviado", Toast.LENGTH_SHORT).show();

                                }
                            });
                            actualizarUsuario(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("[Login FAIL]", "Creacion de usuario FAIL ", task.getException());
                            Toast.makeText(LoginActivity.this, "Creacion de usuario fallida",
                                    Toast.LENGTH_SHORT).show();
                            actualizarUsuario(null);
                        }

                        // ...
                    }
                });

    }
}
