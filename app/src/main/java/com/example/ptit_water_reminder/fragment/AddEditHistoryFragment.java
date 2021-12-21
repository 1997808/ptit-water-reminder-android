package com.example.ptit_water_reminder.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ptit_water_reminder.HistoryFragment;
import com.example.ptit_water_reminder.R;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.WaterLog;

import java.util.List;

public class AddEditHistoryFragment extends Fragment {

    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private EditText textWater;
    private EditText textContent;
    private Button buttonSave;
    private Button buttonCancel;
    private boolean needRefresh;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AddEditHistoryFragment() {
    }

    public static AddEditHistoryFragment newInstance() {
        AddEditHistoryFragment fragment = new AddEditHistoryFragment();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_add_edit_history, container, false);
        //anh xa
        buttonSave = view.findViewById(R.id.button_save);
        buttonCancel = view.findViewById(R.id.button_cancel);
        textWater = view.findViewById(R.id.editText_Waterlog);

        //nhan du lieu
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        Log.d("TAG", "onCreateView: " + id);

        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        WaterLog data = db.getWaterLog(id);

        this.textWater.setText(data.getAmount() + "");

        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonSaveClicked();
            }

            public void buttonSaveClicked() {
                MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
                int LuongNuoc = Integer.parseInt(textWater.getText().toString());
                data.setAmount(LuongNuoc);
                db.updateWaterLog(data);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                HistoryFragment historyFragment = new HistoryFragment();
                transaction.replace(R.id.fragment_container, historyFragment);
                transaction.commit();
            }
        });

        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonCancelClicked();
            }

            private void buttonCancelClicked() {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                HistoryFragment historyFragment = new HistoryFragment();
                transaction.replace(R.id.fragment_container, historyFragment);
                transaction.commit();
            }
        });
        return view;
    }
}