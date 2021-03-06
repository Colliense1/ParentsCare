package com.example.colliensepodder.parentscare;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.colliensepodder.parentscare.Medicine;
import com.example.colliensepodder.parentscare.R;
import com.example.colliensepodder.parentscare.MedicineDashboardRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by colliensepodder on 7/30/2018.
 */


public class MedicineDashboardFragment extends Fragment {

    RecyclerView medicinePreviewRv;

    public MedicineDashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        medicinePreviewRv = view.findViewById(R.id.MedicinePreviewRV);
        MedicineManagementDatabase obj = new MedicineManagementDatabase(getContext());
        ArrayList<Medicine> medicines = obj.retriveAllMedicineInfo();
        LinearLayoutManager LayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        medicinePreviewRv.setLayoutManager(LayoutManagaer);
        MedicineDashboardRecyclerViewAdapter adapter = new MedicineDashboardRecyclerViewAdapter(getContext(), medicines);
        medicinePreviewRv.setAdapter(adapter);
        return view;
    }

}
