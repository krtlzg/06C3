package com.example.asus.bendevarm.Kurumsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.bendevarm.R;
import com.example.asus.bendevarm.activity.ListeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class KurumsalVarHesapActivity extends AppCompatActivity {

    EditText editTextepostaKrmsl,editTextşifreKrmsl;
    Button buttongirisyapKrmsl;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurumsal_var_hesap);

        editTextepostaKrmsl= (EditText) findViewById(R.id.editTextemailkurumsal);
        editTextşifreKrmsl= (EditText) findViewById(R.id.editTextşifrekurumsal);

        buttongirisyapKrmsl = (Button) findViewById(R.id.buttongirisyapkurumsal);

        mAuth= FirebaseAuth.getInstance();



        buttongirisyapKrmsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eposta =  editTextepostaKrmsl.getText().toString();
                String şifre = editTextşifreKrmsl.getText().toString();

                mAuth.signInWithEmailAndPassword(eposta,şifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(),ListeActivity.class);
                            startActivity(intent);
                            finish();
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



