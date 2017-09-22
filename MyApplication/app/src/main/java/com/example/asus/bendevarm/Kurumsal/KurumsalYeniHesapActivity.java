package com.example.asus.bendevarm.Kurumsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.example.asus.bendevarm.model.KurumsalModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KurumsalYeniHesapActivity extends AppCompatActivity {

    EditText editTextadKrmsl,editTextepostaKrmsl,editTextşifreKrmsl,editTexttelKrmsl;
    TextView textViewilKrmsl;
    Button buttonüyeolKrmsl;
    Spinner spinnerilKrmsl;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference dRef;
    String[] cityList;
    String ad, eposta, tel, il, url, sifre;


    //adapteri tanımlıyoruz
    private ArrayAdapter<String> IlAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurumsal_yeni_hesap);

        editTextadKrmsl = (EditText) findViewById(R.id.editTextadkurumsal);
        editTextepostaKrmsl = (EditText) findViewById(R.id.editTextemailkurumsal);
        editTextşifreKrmsl = (EditText) findViewById(R.id.editTextşifrekurumsal);
        editTexttelKrmsl = (EditText) findViewById(R.id.editTexttelkurmsal);
        editTexttelKrmsl.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        textViewilKrmsl = (TextView) findViewById(R.id.textViewilkurumsal);

        buttonüyeolKrmsl = (Button) findViewById(R.id.buttonüyeolkurumsal);

        spinnerilKrmsl = (Spinner) findViewById(R.id.spinnerilkurumsal);
        cityList = getResources().getStringArray(R.array.turkey_city_list);

        //adapteri hazırlıyoruz
        IlAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cityList);
        // listelenecek veri görünümü
        IlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //adapteri set ediyoruz
        spinnerilKrmsl.setAdapter(IlAdapter);

        /*
        spinneril.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        */
        mAuth= FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        dRef = mDatabase.getReference("Kurumsal/");

        buttonüyeolKrmsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eposta= editTextepostaKrmsl.getText().toString();
                sifre = editTextşifreKrmsl.getText().toString();
                ad = editTextadKrmsl.getText().toString().trim();
                il = spinnerilKrmsl.getSelectedItem().toString();
                tel = editTexttelKrmsl.getText().toString().trim();
                url = "https://firebasestorage.googleapis.com/v0/b/bendevarimdenem.appspot.com/o/green-user-icon.png?alt=media&token=1743d79d-99e4-409e-bb5c-2151b052cf7d";
                mAuth.createUserWithEmailAndPassword(eposta,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            KurumsalModel kurumsalKullanici = new KurumsalModel(ad,eposta,sifre,tel,il,url);
                            dRef.child(mAuth.getCurrentUser().getUid()).setValue(kurumsalKullanici);
                            editTextClear();
                            Toast.makeText(getApplicationContext(),"Kayıt Başarılı :)",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),EtkinlikActivity.class);
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
        editTextadKrmsl.getText().clear();
        editTexttelKrmsl.getText().clear();
        editTextepostaKrmsl.getText().clear();
        editTextşifreKrmsl.getText().clear();
        spinnerilKrmsl.setSelection(0);
    }


}
