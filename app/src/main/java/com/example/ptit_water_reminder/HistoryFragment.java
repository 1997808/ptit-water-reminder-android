package com.example.ptit_water_reminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ptit_water_reminder.helper.CustomLogListAdapter;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.Cup;
import com.example.ptit_water_reminder.models.WaterLog;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private ListView listView;
    private List<WaterLog> logList = new ArrayList<>();
    private CustomLogListAdapter logListAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        listView = view.findViewById(R.id.listLogView);

        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        List<WaterLog> data = db.getAllWaterLogs();
        logListAdapter = new CustomLogListAdapter(getActivity(), logList);
        listView.setAdapter(logListAdapter);
        Log.i("TAG", String.valueOf(data));
        logList.addAll(data);
        logListAdapter.notifyDataSetChanged();

        return view;
    }
}