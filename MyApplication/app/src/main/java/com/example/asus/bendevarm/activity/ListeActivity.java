package com.example.asus.bendevarm.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.bendevarm.Fragment.FDF;
import com.example.asus.bendevarm.Fragment.IFListener;
import com.example.asus.bendevarm.R;
import com.example.asus.bendevarm.model.KullaniciModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Stack;

public class ListeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , IFListener{

    private FirebaseAuth fAuth;
    private FirebaseDatabase fDatabase;
    private DatabaseReference dReference;
    private KullaniciModel kullanici;
    private FloatingActionButton fab;
    private ImageView imageViewNavPP;
    private TextView textViewNavName;
    private View headerView;

    private DrawerLayout drawer;

    private static Stack<String> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();
        dReference = fDatabase.getReference("Kullanici/" + fAuth.getCurrentUser().getUid());

        fragmentList = new Stack<>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Çıkış yapmak ister misiniz?", Snackbar.LENGTH_LONG)
                        .setAction("Evet", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                fAuth.signOut();
                                Intent intent = new Intent(getApplicationContext(), AcilisActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerView = navigationView.getHeaderView(0);
        imageViewNavPP = (ImageView) headerView.findViewById(R.id.imageViewNavPP);
        textViewNavName = (TextView) headerView.findViewById(R.id.textViewNavName);

        onFragmentChange(FDF.MAIN);
        setTitle("Anasayfa");

        dReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                kullanici = dataSnapshot.getValue(KullaniciModel.class);
                Glide.with(ListeActivity.this).load(kullanici.getFotourl()).into(imageViewNavPP);
                textViewNavName.setText(kullanici.getAd() + " " + kullanici.getSoyad());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (fragmentList.size() <= 1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Çıkış");
                builder.setMessage("Çıkmak istediğinize emin misiniz?");

                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                        System.exit(0);
                    }
                });

                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
            else {
                fragmentList.pop();
                onFragmentChange(fragmentList.peek());
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_hesabım) {
            onFragmentChange(FDF.HESABIM);
        } else if (id == R.id.nav_etkinlikler) {
            onFragmentChange(FDF.KETKİNLİK);

        } else if (id == R.id.nav_mesajkutum) {
            onFragmentChange(FDF.MESAJ);

        } else if (id == R.id.nav_hakkımızda) {
            onFragmentChange(FDF.HAKKKIMIZDA);

        } else if (id == R.id.nav_sponsorlar) {
            onFragmentChange(FDF.SPONSORLAR);

        } else if (id == R.id.nav_paylaş) {
            onFragmentChange(FDF.PAYLAS);

        } else if (id == R.id.nav_anasayfa) {
            onFragmentChange(FDF.MAIN);
        }

        item.setChecked(true);
        setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentChange(String tag) {
        onFragmentChange(tag,null);
    }

    @Override
    public void onFragmentChange(String fragment, Bundle bundle) {
        if (fragment.equals(FDF.HESABIM)) {
            fab.show();
        }
        else {
            fab.hide();
        }

        if (!fragmentList.contains(fragment)) {
            fragmentList.push(fragment);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FDF fdf= FDF.getInstance();
        Fragment tFragment = fdf.getFragment(fragment, bundle);
        fragmentTransaction.replace(R.id.container, tFragment);
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.commit();
    }
}
