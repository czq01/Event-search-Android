package com.myapplication.myapplication.detailpage;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.myapplication.myapplication.FirstFragment;

public class DetailPageAdapter extends FragmentStateAdapter {
    private final Bundle bundle;
    public DetailPageAdapter(FragmentActivity activity, Bundle bundle) {
        super(activity);
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment createFragment(int pos) {
        switch(pos) {
            case 0:
                EventFragment f1 = new EventFragment();
                f1.setArguments(bundle);
                Log.d("Create", "Fragment1");
                return f1;
            case 1:
                ArtistsFragment f2 = new ArtistsFragment();
                f2.setArguments(bundle);
                return f2;
            case 2:
                VenueFragment f3 = new VenueFragment();
                f3.setArguments(bundle);
                return f3;
            default:
                EventFragment fd = new EventFragment();
                fd.setArguments(bundle);
                Log.d("Create", "Default");
                return fd;
        }

    }

    @NonNull
    @Override
    public int getItemCount() {
        return 3;
    }
}
