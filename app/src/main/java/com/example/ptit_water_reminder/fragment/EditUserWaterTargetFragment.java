package com.example.ptit_water_reminder.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ptit_water_reminder.Home;
import com.example.ptit_water_reminder.HomeFragment;
import com.example.ptit_water_reminder.MainActivity;
import com.example.ptit_water_reminder.R;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.User;

public class EditUserWaterTargetFragment extends Fragment {
    private EditText waterTarget;
    private Button userButtonSave;
    private Button userButtonCancel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditUserWaterTargetFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EditUserWaterTargetFragment newInstance(String param1, String param2) {
        EditUserWaterTargetFragment fragment = new EditUserWaterTargetFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_user_water_target, container, false);
        userButtonSave = view.findViewById(R.id.user_button_save);
        userButtonCancel = view.findViewById(R.id.user_button_cancel);
        waterTarget = view.findViewById(R.id.editText_waterTarget);
        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        User user = db.getUser();
        waterTarget.setText("" + user.getWaterTarget());

        userButtonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int amount = Integer.parseInt(waterTarget.getText().toString());
                if (amount > 1000 && amount < 9999) {
                    user.setWaterTarget(amount);
                    db.updateUser(user);
                    //chuyen man
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    HomeFragment homeFragment = new HomeFragment();
                    transaction.replace(R.id.fragment_container, homeFragment);
                    transaction.commit();
                } else {
                    Toast.makeText(getActivity(), "Lượng nước mục tiêu từ 1000 - 9999", Toast.LENGTH_SHORT).show();
                }
            }
        });

        userButtonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                transaction.replace(R.id.fragment_container, homeFragment);
                transaction.commit();
            }
        });
        return view;
    }
}