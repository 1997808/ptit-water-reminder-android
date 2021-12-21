package com.example.ptit_water_reminder.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptit_water_reminder.HistoryFragment;
import com.example.ptit_water_reminder.HomeFragment;
import com.example.ptit_water_reminder.R;
import com.example.ptit_water_reminder.helper.CustomCupListAdapter;
import com.example.ptit_water_reminder.helper.CustomLogListAdapter;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.Cup;
import com.example.ptit_water_reminder.models.WaterLog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddLogHomeFragment extends Fragment {
    private GridView gridView;
    private List<Cup> cupList = new ArrayList<>();
    private CustomCupListAdapter cupListAdapter;
    FloatingActionButton addCup;

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddLogHomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddLogHomeFragment newInstance(String param1, String param2) {
        AddLogHomeFragment fragment = new AddLogHomeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
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
        View view = inflater.inflate(R.layout.fragment_add_log_home, container, false);
        gridView = view.findViewById(R.id.gridView);
        addCup = view.findViewById(R.id.floating_cup_home);
        getActivity().setTitle("Log");

        this.addCup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                AddCupHomeFragment addCupHomeFragment = new AddCupHomeFragment();
                transaction.replace(R.id.fragment_container, addCupHomeFragment);
                transaction.commit();
            }
        });

        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        List<Cup> data = db.getAllCups();
//        logList = db.getAllWaterLogs();
        cupListAdapter = new CustomCupListAdapter(getActivity(), cupList);
        gridView.setAdapter(cupListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Cup selectedCup = (Cup) gridView.getItemAtPosition(position);
                db.addWaterLog(selectedCup.getCupAmount());
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                transaction.replace(R.id.fragment_container, homeFragment);
                transaction.commit();
            }
        });
        cupList.addAll(data);
        cupListAdapter.notifyDataSetChanged();

//        registerForContextMenu(this.gridView);
        return view;
    }
}