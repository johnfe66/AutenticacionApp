package com.johnfe66.autenticacionapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.johnfe66.autenticacionapp.model.Persona;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonasActivity extends AppCompatActivity {

    @BindView(R.id.txt_identificacion_persona)
    TextInputEditText txtIdentificacionPersona;
    @BindView(R.id.txt_nombre_persona)
    TextInputEditText txtNombrePersona;
    @BindView(R.id.txt_apellido_persona)
    TextInputEditText txtApellidoPersona;
    @BindView(R.id.txt_edad_persona)
    TextInputEditText txtEdadPersona;
    @BindView(R.id.txt_telefono_persona)
    TextInputEditText txtTelefonoPersona;
    FirebaseDatabase database;
    DatabaseReference coleccion;

    FirebaseAuth auth;
    @BindView(R.id.buscar)
    FloatingActionButton buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        coleccion = database.getReference();





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Persona persona = new Persona();

                persona.setApellidos(txtApellidoPersona.getText().toString().trim());
                persona.setEdad(Integer.valueOf(txtEdadPersona.getText().toString().trim()));
                persona.setIdentificacion(txtIdentificacionPersona.getText().toString().trim());
                persona.setNombre(txtNombrePersona.getText().toString().trim());
                persona.setTelefono(txtTelefonoPersona.getText().toString().trim());

                coleccion = database.getReference("personas").child(txtIdentificacionPersona.getText().toString().trim());

                coleccion.setValue(persona);


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.buscar)
    public void onViewClicked() {

        coleccion = database.getReference("personas").child(txtIdentificacionPersona.getText().toString().trim());

        coleccion.addListenerForSingleValueEvent(new  ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Persona persona = dataSnapshot.getValue(Persona.class);


                System.out.println("obejto persona "+ persona.getNombre());

                   }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
