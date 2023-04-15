package com.myapplication.myapplication;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.myapplication.myapplication.databinding.FragmentFirstBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private RequestQueue req;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        req = Volley.newRequestQueue(getActivity());
        setCategorySelection();
        setLocationStyle();
        setKeyboardStyle();
        setSubmitButton();
        setClearButton();
        setBackButton();
        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setCategorySelection() {
        Spinner category = binding.category;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.options_array, R.layout.selected_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
    }

    public void setLocationStyle() {
        TextView location = binding.inputLocation;
        binding.autoDetect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {location.setVisibility(View.GONE);}
                else {location.setVisibility(View.VISIBLE);}
            }
        });
    }

    public void setKeyboardStyle() {
        binding.getRoot().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                closeKeyboard();
                return false;
            }
        });
    }

    public void setSubmitButton() {
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = binding.inputKeyword.getText().toString();
                String distance = binding.inputDistance.getText().toString();
                String category = binding.category.getSelectedItem().toString();
                boolean autoDetect = binding.autoDetect.isChecked();
                String location = binding.inputLocation.getText().toString();
                boolean verify = TextUtils.isEmpty(keyword) || TextUtils.isEmpty(distance)
                        || (!autoDetect&&TextUtils.isEmpty(location));
                if (verify) {
                    Snackbar.make(view, "All fields should be filled.", Snackbar.LENGTH_LONG)
                            .setAction("action", null).show();
                    return;
                }
                String url = String.format(
                        "http://nodeeventappp.us-west-2.elasticbeanstalk.com/submit_form?keyword=%s&distance=%s&category=%s&location=%s",
                        keyword, distance, category, autoDetect? "auto-detect":location
                );
                binding.searchViewer.setVisibility(View.GONE);
                binding.loadingView.setVisibility(View.VISIBLE);
                OnSubmitForm(url);
            }
        });
    }
    
    public void setBackButton() {
        binding.resultContainer.findViewById(R.id.back_layout)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.resultContainer.setVisibility(View.GONE);
                        binding.searchViewer.setVisibility(View.VISIBLE);
                    }
                });
    }

    public void setClearButton() {
        binding.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.inputKeyword.setText("");
                binding.inputDistance.setText("");
                binding.category.setSelection(0);
                binding.autoDetect.setChecked(false);
                binding.inputLocation.setText("");
            }
        });
    }    
    
    public void OnSubmitForm(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        displayResults(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Query Error
                        errorHandler(error);
                    }
                });

        // Add the request to the RequestQueue.
        req.add(stringRequest);
    }

    public void displayResults(String response) {
        List<EventItem> itemArr = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                EventItem resultItem = new EventItem(obj);
                itemArr.add(resultItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ResultItemAdapter adapter = new ResultItemAdapter(resultItems);

        binding.loadingView.setVisibility(View.GONE);
        binding.resultContainer.setVisibility(View.VISIBLE);
    }
    
    public void errorHandler(VolleyError error) {
        String err = error.toString();
        return ;
    }

    public void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view!=null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}