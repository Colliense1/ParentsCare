package com.example.colliensepodder.parentscare;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralFragment extends Fragment {

    MaterialBetterSpinner betterSpinner,bspinner,bespinner;

    String [] SPINNERLIST = {"0","1","2","3","4","5","6","7","8","9"};
    String [] spinnerLIST = {".00",".25",".50",".75"};
    String [] SPINNERlist = {"Tablet","Capsule","mg","ml","Drops","Spray","Teaspoon"};


    public GeneralFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general, container, false);

        betterSpinner = (MaterialBetterSpinner)view.findViewById(R.id.material_spinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line,SPINNERLIST);
        betterSpinner.setAdapter(arrayAdapter);

        bspinner = (MaterialBetterSpinner)view. findViewById(R.id.material_spinner1);

        ArrayAdapter<String> Arrayadapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line,spinnerLIST);
        bspinner.setAdapter(Arrayadapter);

        bespinner = (MaterialBetterSpinner)view.findViewById(R.id.material_spinner2);
        ArrayAdapter<String> aRrayadapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line,SPINNERlist);
        bespinner.setAdapter(aRrayadapter);

        return view;
    }


}
