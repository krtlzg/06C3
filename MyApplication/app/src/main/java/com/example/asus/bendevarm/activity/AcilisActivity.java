package com.example.asus.bendevarm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.asus.bendevarm.Kurumsal.KurumsalKayitlarActivity;
import com.example.asus.bendevarm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AcilisActivity extends AppCompatActivity {

    ImageButton buttonbireysel,buttonkurumsal;
    TextView textViewbireysel,textViewkurumsal;

    FirebaseAuth fAuth;
    FirebaseUser fUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acilis);

        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
        if (fUser != null)
        {
            Intent intent = new Intent(getApplicationContext(),ListeActivity.class);
            startActivity(intent);
        }

        buttonbireysel = (ImageButton) findViewById(R.id.imageButtonbireysel);
        buttonkurumsal = (ImageButton) findViewById(R.id.imageButtonkurumsal);

        textViewbireysel= (TextView) findViewById(R.id.textViewbireysel);
        textViewkurumsal= (TextView) findViewById(R.id.textViewkurumsal);

        buttonbireysel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),KayitlarActivity.class);
                startActivity(intent);
            }
        });

        buttonkurumsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), KurumsalKayitlarActivity.class);
                startActivity(intent);
            }
        });

    }
}
