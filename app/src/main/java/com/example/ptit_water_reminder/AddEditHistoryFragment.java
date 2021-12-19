package com.example.ptit_water_reminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.WaterLog;

public class AddEditHistoryFragment extends Fragment {

    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private EditText textWater;
    private EditText textContent;
    private Button buttonSave;
    private Button buttonCancel;

    private WaterLog waterLog;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AddEditHistoryFragment() {
    }

    public static AddEditHistoryFragment newInstance() {
        AddEditHistoryFragment fragment = new AddEditHistoryFragment();
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

        View view = inflater.inflate(R.layout.fragment_add_edit_history, container, false);

        buttonSave= view.findViewById(R.id.button_save);
        buttonCancel= view.findViewById(R.id.button_cancel);
        textWater= view.findViewById(R.id.editText_Waterlog);

        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                buttonSaveClicked();
            }

            public void buttonSaveClicked() {
                MyDatabaseHelper db = new MyDatabaseHelper(getActivity());

                String LuongNuoc = textWater.getText().toString();
                int ln = 0;
                if(!LuongNuoc.isEmpty()) ln = Integer.parseInt(LuongNuoc);
                waterLog= new WaterLog(ln);
                db.addWaterLog(waterLog);

               getFragmentManager().popBackStack();

            }
        });

        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                buttonCancelClicked();
            }

            private void buttonCancelClicked() {

            }
        });



        return view;
    }
}