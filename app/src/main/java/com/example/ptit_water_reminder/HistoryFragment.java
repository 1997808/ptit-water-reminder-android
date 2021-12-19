package com.example.ptit_water_reminder;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.Notification;

import android.widget.ListView;
import android.widget.Toast;

import com.example.ptit_water_reminder.helper.CustomLogListAdapter;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.Cup;
import com.example.ptit_water_reminder.models.WaterLog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private ListView listView;
    private List<WaterLog> logList = new ArrayList<>();
    private CustomLogListAdapter logListAdapter;
    FloatingActionButton themHistory;





//
//    private final List<Notification> noteList = new ArrayList<Notification>();
//    private ArrayAdapter<Notification> listViewAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;

    private String mParam1;
    private String mParam2;
    public HistoryFragment() {
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Toast.makeText(getActivity(), "onAttach", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        listView = view.findViewById(R.id.listLogView);

        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        List<WaterLog> data = db.getAllWaterLogs();
//        logList = db.getAllWaterLogs();
        logListAdapter = new CustomLogListAdapter(getActivity(), logList);
        listView.setAdapter(logListAdapter);
        logList.addAll(data);
        logListAdapter.notifyDataSetChanged();

        registerForContextMenu(this.listView);

        themHistory = view.findViewById(R.id.themHistory);
        themHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment UpdateFragment = new AddEditHistoryFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container, UpdateFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


       return view;

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo)    {

        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Chọn");

        // groupId, itemId, order, title

        menu.add(0, MENU_ITEM_EDIT , 2, "Edit Note");
        menu.add(0, MENU_ITEM_DELETE, 4, "Delete Note");
    }



    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final WaterLog selectedWaterLog = (WaterLog) this.listView.getItemAtPosition(info.position);


        if(item.getItemId() == MENU_ITEM_VIEW){
            Log.d("this is tag","messsssss");
            Toast.makeText(getContext(),selectedWaterLog.getWaterLogId(),Toast.LENGTH_LONG).show();// chu y


        }
        else if(item.getItemId() == MENU_ITEM_CREATE){

        }

        else if(item.getItemId() == MENU_ITEM_EDIT ){
            Log.d("this is tag","this is edit");

        }
        else if(item.getItemId() == MENU_ITEM_DELETE){

        }
        else {
            return false;
        }
        return true;
    }


}