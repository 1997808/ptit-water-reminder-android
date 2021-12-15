package com.example.ptit_water_reminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.Notification;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    private ListView listView;
//    private static final int MENU_ITEM_VIEW = 111;
//    private static final int MENU_ITEM_EDIT = 222;
//    private static final int MENU_ITEM_CREATE = 333;
//    private static final int MENU_ITEM_DELETE = 444;
//
//    private static final int MY_REQUEST_CODE = 1000;

    private final List<Notification> noteList = new ArrayList<Notification>();
    private ArrayAdapter<Notification> listViewAdapter;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public HistoryFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters

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

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_history, container, false);

         String[] item={"so 1", "so2","so3"};

        ListView listView=(ListView) view.findViewById(R.id.listView);

//        MyDatabaseHelper db = new MyDatabaseHelper(this);


        ArrayAdapter<String> listViewAdapter= new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
                item
        );
        listView.setAdapter(listViewAdapter);
        return view;


    }
}