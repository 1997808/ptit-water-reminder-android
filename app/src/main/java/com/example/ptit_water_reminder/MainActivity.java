package com.example.ptit_water_reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.utils.AlarmReceiver;

public class MainActivity extends AppCompatActivity {
    EditText edName, edTarget;
    Button btDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
//        AlarmScheduler.create(this);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultCupsIfNeed();
        db.getWaterLogChart();

        this.getSupportActionBar().setTitle("Login");

        btDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edName.getText().length() != 0 && edTarget.getText().length() != 0) {
                    if (edTarget.getText().toString().equals("yen") && edName.getText().toString().equals("123")) {
                        Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, Home.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                    }
                }
                Intent i = new Intent(MainActivity.this, Home.class);
                startActivity(i);
            }
        });
    }

    public void anhXa() {
        edName = (EditText) findViewById(R.id.username);
        edTarget = (EditText) findViewById(R.id.userTarget);
        btDangNhap = (Button) findViewById(R.id.btDangNhap);
    }
}