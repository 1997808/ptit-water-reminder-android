package com.example.ptit_water_reminder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.ListView;

import com.example.ptit_water_reminder.fragment.AddEditHistoryFragment;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;

import android.widget.Toast;

import com.example.ptit_water_reminder.helper.CustomLogListAdapter;
import com.example.ptit_water_reminder.models.WaterLog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private ListView listView;
    private List<WaterLog> logList = new ArrayList<>();
    private CustomLogListAdapter logListAdapter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        listView = view.findViewById(R.id.listLogView);
        getActivity().setTitle("Log");

        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        List<WaterLog> data = db.getAllWaterLogs();
        logListAdapter = new CustomLogListAdapter(getActivity(), logList);
        listView.setAdapter(logListAdapter);
        logList.addAll(data);
        logListAdapter.notifyDataSetChanged();
        registerForContextMenu(this.listView);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Chọn");

        // groupId, itemId, order, title
        menu.add(0, MENU_ITEM_EDIT, 2, "Edit Note");
        menu.add(0, MENU_ITEM_DELETE, 4, "Delete Note");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final WaterLog selectedWaterLog = (WaterLog) this.listView.getItemAtPosition(info.position);

        if (item.getItemId() == MENU_ITEM_EDIT) {
            // ban du lieu
            Bundle bundle = new Bundle();
            bundle.putInt("id", selectedWaterLog.getWaterLogId());
//
            // chuyen fragment
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            AddEditHistoryFragment editHistoryFragment = new AddEditHistoryFragment();
            editHistoryFragment.setArguments(bundle);
            transaction.replace(R.id.fragment_container, editHistoryFragment);
            transaction.commit();

        } else if (item.getItemId() == MENU_ITEM_DELETE) {
            new AlertDialog.Builder(getActivity())
                    .setMessage(selectedWaterLog.getWaterLogId() + ".Bạn có muốn xóa không?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteHistory(selectedWaterLog);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            return false;
        }
        return true;
    }

    private void deleteHistory(WaterLog waterLog) {
        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        db.deleteWaterLog(waterLog);
        this.logList.remove(waterLog);
        // Refresh ListView.
        this.logListAdapter.notifyDataSetChanged();
    }
}