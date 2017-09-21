package com.example.asus.bendevarm.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.asus.bendevarm.activity.PaylasFragment;

/**
 * Created by asus on 10.9.2017.
 */

public class FDF {


    private static FDF instance = null;
    public static final String MAIN = "fragment_main";
    public static final String HESABIM = "fragment_hesabim";
    public static final String ETKINLIK = "fragment_etkinlik";
    public static final String HAKKKIMIZDA = "fragment_hakkimizda";
    public static  final String SPONSORLAR = "fragment_sponsor";
    public static final String PAYLAS ="fragment_paylas";
    public static final String MESAJ ="fragment_mesaj";
    public static final String KETKİNLİK ="fragment_ketkinlik";


    private FDF() { }

    public synchronized static FDF getInstance() {
        if (instance == null) {
            instance = new FDF();
        }
        return instance;
    }
    public Fragment getFragment(String fragment){
        return this.getFragment(fragment,null);
    }
    public Fragment getFragment(String fragment, Bundle bundle) {
        if (fragment == null) {
            return null;
        }

        if (fragment.equals(MAIN)) {
            return new MainFragment();
        }
        else if (fragment.equals(HESABIM))
        {
            return  new HesabimFragment();
        }
        else if (fragment.equals(ETKINLIK))
        {
            return  new EtkinlikFragment().newInstance(bundle);
        }
        else if(fragment.equals(HAKKKIMIZDA)){
            return new HakkimizdaFragment();
        }
        else if(fragment.equals(SPONSORLAR)){
            return new SponsorFragment();
        }
        else if(fragment.equals(PAYLAS)){
            return new PaylasFragment();
        }
        else if(fragment.equals(MESAJ)){
            return new MesajFragment();
        }
        else if(fragment.equals(KETKİNLİK)){
            return new KEtkinlikFragment();
        }

        else {
            return null;
        }
    }
}
