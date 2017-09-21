package com.example.asus.bendevarm.Kurumsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.asus.bendevarm.R;

public class KurumsalKayitlarActivity extends AppCompatActivity {


    ImageView imageViewlogo;
    Button buttonyenihesapkurumsal,buttonvarhesapkurumsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurumsal_kayitlar);

        imageViewlogo = (ImageView) findViewById(R.id.imageViewlogo);

        buttonyenihesapkurumsal = (Button) findViewById(R.id.buttonyenihesapkurumsal);
        buttonvarhesapkurumsal = (Button) findViewById(R.id.buttonvarhesapkurumsal);

        buttonvarhesapkurumsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),KurumsalVarHesapActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonyenihesapkurumsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent(getApplicationContext(),KurumsalYeniHesapActivity.class);
                startActivity(intent2);
                finish();
            }
        });

    }
        

    }

