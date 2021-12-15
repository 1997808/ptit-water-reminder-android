package com.example.ptit_water_reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText edTaiKhoan, edPassword;
    Button btdangNhap,btdangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();


        btdangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edTaiKhoan.getText().length() !=0 &&edPassword.getText().length()!=0){
                    if (edTaiKhoan.getText().toString().equals("yen")&& edPassword.getText().toString().equals("123"))
                    {
                        Toast.makeText(MainActivity.this,"Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(MainActivity.this,Home.class);
                        startActivity(i);
                    }
                   else{
                        Toast.makeText(MainActivity.this,"Dang nhap that bai", Toast.LENGTH_SHORT).show();
                   }
                }

            }
        });

    }

    public  void anhXa(){
        edTaiKhoan=(EditText) findViewById(R.id.tenDangNhap);
        edPassword= (EditText) findViewById(R.id.password);
        btdangNhap=(Button) findViewById(R.id.btDangNhap);
        btdangKy=(Button) findViewById(R.id.btDangKy);


    }
}