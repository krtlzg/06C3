package com.example.asus.bendevarm.Fragment;

import android.os.Bundle;

/**
 * Created by asus on 10.9.2017.
 */

public interface IFListener {
    void onFragmentChange(String tag);
    void onFragmentChange(String tag, Bundle bundle);
}
