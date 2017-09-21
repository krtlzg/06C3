package com.example.asus.bendevarm.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.bendevarm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class VarHesapActivity extends AppCompatActivity {

    EditText editTexteposta,editTextşifre;
    TextView textViewunuttunmu;
    Button buttongönüllüol;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_var_hesap);

        editTexteposta= (EditText) findViewById(R.id.editTextemail);
        editTextşifre= (EditText) findViewById(R.id.editTextşifre);


        buttongönüllüol = (Button) findViewById(R.id.buttongönüllüol);

        mAuth= FirebaseAuth.getInstance();


        buttongönüllüol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eposta =  editTexteposta.getText().toString();
                String şifre = editTextşifre.getText().toString();

                mAuth.signInWithEmailAndPassword(eposta,şifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(),ListeActivity.class);
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Giriş yapılamadı",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });






    }
}
