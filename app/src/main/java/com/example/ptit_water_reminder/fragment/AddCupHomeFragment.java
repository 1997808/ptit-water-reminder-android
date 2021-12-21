package com.example.ptit_water_reminder.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ptit_water_reminder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddCupHomeFragment extends Fragment {
    private Button cupButtonSave;
    private Button cupButtonCancel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCupHomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddCupHomeFragment newInstance(String param1, String param2) {
        AddCupHomeFragment fragment = new AddCupHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_cup_home, container, false);
        cupButtonSave = view.findViewById(R.id.cup_button_save);
        cupButtonCancel = view.findViewById(R.id.cup_button_cancel);

        cupButtonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                AddLogHomeFragment addLogHomeFragment = new AddLogHomeFragment();
                transaction.replace(R.id.fragment_container, addLogHomeFragment);
                transaction.commit();
            }
        });

        cupButtonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                AddLogHomeFragment addLogHomeFragment = new AddLogHomeFragment();
                transaction.replace(R.id.fragment_container, addLogHomeFragment);
                transaction.commit();
            }
        });

        return view;
    }
}