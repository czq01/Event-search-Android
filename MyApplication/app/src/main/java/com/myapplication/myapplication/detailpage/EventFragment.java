package com.myapplication.myapplication.detailpage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.myapplication.myapplication.R;
import com.myapplication.myapplication.databinding.EventFragmentBinding;

public class EventFragment extends Fragment {
    private EventFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = EventFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        Log.d("EventFragmentCreated", "Constructing details");


        Log.d("SetVisibility", "Setted");
//        ViewPager2 viewpage = getView().findViewById(R.id.detail_viewPager);
//        ConstraintLayout bar = getView().findViewById(R.id.progress_detail);
//        bar.setVisibility(View.GONE);
//        viewpage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
