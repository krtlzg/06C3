package com.example.asus.bendevarm.Fragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asus.bendevarm.R;
import com.example.asus.bendevarm.custom.CustomAdapter;
import com.example.asus.bendevarm.model.EtkinlikModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainFragment extends BaseFragment {

    private FirebaseDatabase fDatabase;
    private DatabaseReference dReference;
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;

    private AlertDialog.Builder builderSingle;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> temp;
    private ArrayList<EtkinlikModel> etkinlikList;
    private ListView lvEtkinlik;
    private CustomAdapter adapter;

    private boolean ilkAcis = true;
    private String filtre = "";
    private ProgressDialog pDialog;

    public MainFragment() { }

    @Override
    protected int getFID() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {
        getActivity().setTitle("Anasayfa");
        setHasOptionsMenu(true);
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        fDatabase = FirebaseDatabase.getInstance();
        dReference = fDatabase.getReference("Etkinlikler");

        temp = new ArrayList<>();
        temp.add("");

        etkinlikList = new ArrayList<>();
        etkinlikList.add(new EtkinlikModel(0, "", "", "", "", "", "", temp));

        lvEtkinlik = (ListView) getActivity().findViewById(R.id.listviewetkinlik);
        adapter = new CustomAdapter(getActivity(), etkinlikList, getContext(), fAuth);
        lvEtkinlik.setAdapter(adapter);
        lvEtkinlik.setDivider(null);

        dReference.keepSynced(true);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setTitle("Yükleniyor.");
        pDialog.setMessage("Lütfen bekleyiniz.");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);
        if (ilkAcis) {
            pDialog.show();
        }

        builderSingle = new AlertDialog.Builder(getActivity());
        builderSingle.setTitle("Birini seçiniz :");

        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Etkinlik Adı (A-Z)");
        arrayAdapter.add("Kuruluş Adı (A-Z)");
        arrayAdapter.add("Durum");
    }

    @Override
    protected void handlers() {
        dReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                etkinlikList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    etkinlikList.add(ds.getValue(EtkinlikModel.class));
                }
                adapter.notifyDataSetChanged();

                if (ilkAcis)
                {
                    pDialog.dismiss();
                    ilkAcis = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (filtre != "")
        { filtrele(); }

        lvEtkinlik.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                EtkinlikModel etkinlik = etkinlikList.get(position);
                bundle.putSerializable("etkinlik",etkinlik);
                listener.onFragmentChange(FDF.ETKINLIK,bundle);
            }
        });


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.liste,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.app_bar_filtrele) {
            dialogGoster();
            return true;
        }
        if (id == R.id.app_bar_bul) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void dialogGoster() {
        builderSingle.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String secilenFiltre = arrayAdapter.getItem(which);
                filtre = secilenFiltre;
                filtrele();
            }
        });
        builderSingle.show();
    }

    private void filtrele()
    {
        switch (filtre)
        {
            case "Etkinlik Adı (A-Z)":
                Collections.sort(etkinlikList, new Comparator<EtkinlikModel>() {
                    @Override
                    public int compare(EtkinlikModel o1, EtkinlikModel o2) {
                        return o1.getEtkinlikAdi().compareTo(o2.getEtkinlikAdi());
                    }
                });
                adapter.notifyDataSetChanged();
                break;
            case "Kuruluş Adı (A-Z)":
                Collections.sort(etkinlikList, new Comparator<EtkinlikModel>() {
                    @Override
                    public int compare(EtkinlikModel o1, EtkinlikModel o2) {
                        return o1.getKurulusAdi().compareTo(o2.getKurulusAdi());
                    }
                });
                adapter.notifyDataSetChanged();
                break;
            case "Durum":
                Collections.sort(etkinlikList, new Comparator<EtkinlikModel>() {
                    @Override
                    public int compare(EtkinlikModel o1, EtkinlikModel o2) {
                        return String.valueOf(o1.getDurum()).compareTo(String.valueOf(o2.getDurum()));
                    }
                });
                adapter.notifyDataSetChanged();
                break;
        }

    }


}
