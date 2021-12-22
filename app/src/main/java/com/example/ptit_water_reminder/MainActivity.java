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
import com.example.ptit_water_reminder.models.User;
import com.example.ptit_water_reminder.utils.AlarmReceiver;

public class MainActivity extends AppCompatActivity {
    EditText edName, edTarget;
    Button btDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        Intent i = new Intent(MainActivity.this, Home.class);
//        AlarmScheduler.create(this);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultCupsIfNeed();
        // da co user tu dong vao
        if (db.checkUserExist()) {
            startActivity(i);
        };

        this.getSupportActionBar().setTitle("Login");

        btDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edName.getText().length() != 0) {
                    if (edTarget.getText().length() > 3 && edTarget.getText().length() < 5) {
                        User user = new User(edName.getText().toString(), Integer.parseInt(edTarget.getText().toString()));
                        db.addUser(user);
                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this, "Lượng nước mục tiêu từ 1000 - 9999", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Hãy nhập tên", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void anhXa() {
        edName = (EditText) findViewById(R.id.username);
        edTarget = (EditText) findViewById(R.id.userTarget);
        btDangNhap = (Button) findViewById(R.id.btDangNhap);
    }
}