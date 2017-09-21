package com.example.asus.bendevarm.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.bendevarm.R;
import com.example.asus.bendevarm.model.KullaniciModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class YeniHesapActivity extends AppCompatActivity {

    EditText editTextad,editTextsoyad,editTexteposta,editTextşifre,editTextcep;
    TextView textViewil;
    Button buttongönüllüol;
    Spinner spinneril;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference dRef;
    String[] cityList;
    String ad, soyad, eposta, cep, il, url, şifre;


    //adapteri tanımlıyoruz
    private ArrayAdapter<String> IlAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_hesap);


        editTextad = (EditText) findViewById(R.id.editTextad);
        editTextsoyad = (EditText) findViewById(R.id.editTextsoyad);
        editTexteposta = (EditText) findViewById(R.id.editTextemail);
        editTextşifre = (EditText) findViewById(R.id.editTextşifre);
        editTextcep = (EditText) findViewById(R.id.editTextcep);
        editTextcep.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        textViewil = (TextView) findViewById(R.id.textViewil);

        buttongönüllüol = (Button) findViewById(R.id.buttongönüllüol);

        spinneril = (Spinner) findViewById(R.id.spinneril);
        cityList = getResources().getStringArray(R.array.turkey_city_list);

        //adapteri hazırlıyoruz
        IlAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cityList);
        // listelenecek veri görünümü
        IlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        //adapteri set ediyoruz
        spinneril.setAdapter(IlAdapter);

        /*
        spinneril.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        */

        mAuth= FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        dRef = mDatabase.getReference("Kullanici/");
        buttongönüllüol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eposta= editTexteposta.getText().toString();
                şifre = editTextşifre.getText().toString();
                ad = editTextad.getText().toString().trim();
                soyad = editTextsoyad.getText().toString().trim();
                il = spinneril.getSelectedItem().toString();
                cep = editTextcep.getText().toString().trim();
                url = "https://firebasestorage.googleapis.com/v0/b/bendevarimdenem.appspot.com/o/green-user-icon.png?alt=media&token=1743d79d-99e4-409e-bb5c-2151b052cf7d";

                mAuth.createUserWithEmailAndPassword(eposta,şifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            KullaniciModel kullanici = new KullaniciModel(ad,soyad,eposta,şifre,cep,il,url,"");
                            dRef.child(mAuth.getCurrentUser().getUid()).setValue(kullanici);
                            editTextClear();
                            Toast.makeText(getApplicationContext(),"Kayıt Başarılı :)",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),ListeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Log.d(getLocalClassName(),task.getException().toString());
                            Toast.makeText(getApplicationContext(),"Kayıt Başarısız :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void editTextClear()
    {
        editTextad.getText().clear();
        editTextsoyad.getText().clear();
        editTextcep.getText().clear();
        editTexteposta.getText().clear();
        editTextşifre.getText().clear();
        spinneril.setSelection(0);
    }
}
