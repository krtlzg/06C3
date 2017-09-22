package com.example.asus.bendevarm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.asus.bendevarm.Kurumsal.EtkinlikActivity;
import com.example.asus.bendevarm.Kurumsal.KurumsalKayitlarActivity;
import com.example.asus.bendevarm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AcilisActivity extends AppCompatActivity {

    ImageButton buttonbireysel,buttonkurumsal;
    TextView textViewbireysel,textViewkurumsal;

    FirebaseAuth fAuth;
    FirebaseUser fUser;
    FirebaseDatabase fData;
    DatabaseReference dRef,dRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acilis);

        fAuth = FirebaseAuth.getInstance();
        fData = FirebaseDatabase.getInstance();
        dRef = fData.getReference("Kullanici");
        dRef2 = fData.getReference("Kurumsal");
        fUser = fAuth.getCurrentUser();

        if (fUser != null)
        {
            dRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds: dataSnapshot.getChildren())
                    {
                        if (ds.getKey().equals(fUser.getUid()))
                        {
                            Intent intent = new Intent(getApplicationContext(),ListeActivity.class);
                            startActivity(intent);
                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            dRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds: dataSnapshot.getChildren())
                    {
                        if (ds.getKey().equals(fUser.getUid()))
                        {
                            Intent intent = new Intent(getApplicationContext(),EtkinlikActivity.class);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

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
