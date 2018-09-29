package com.johnfe66.autenticacionapp;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }


    @OnClick(R.id.btnLoginEntrar)
    public void onBtnLoginEntrarClicked() {

        Toast.makeText(this, "Click Entrar",Toast.LENGTH_LONG).show();

    }

    @OnClick(R.id.btnLoginRegistrar)
    public void onBtnLoginRegistrarClicked() {

        Toast.makeText(this, "Click Registrar",Toast.LENGTH_LONG).show();
    }
}
