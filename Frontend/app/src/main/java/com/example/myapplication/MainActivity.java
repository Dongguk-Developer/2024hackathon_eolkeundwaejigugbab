package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  // main.xml 레이아웃 사용

        // 동천동 버튼 클릭 이벤트
        ImageButton btnDongcheon = findViewById(R.id.btnDongcheon);
        btnDongcheon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Step1_1Activity.class);
                startActivity(intent);
            }
        });

        // 선도동 버튼 클릭 이벤트
        ImageButton btnSundo = findViewById(R.id.btnSundo);
        btnSundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Step1_2Activity.class);
                startActivity(intent);
            }
        });

        // 성건동 버튼 클릭 이벤트
        ImageButton btnSeonggeon = findViewById(R.id.btnSeonggeon);
        btnSeonggeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Step1_3Activity.class);
                startActivity(intent);
            }
        });

        // 월성동 버튼 클릭 이벤트
        ImageButton btnWolseong = findViewById(R.id.btnWolseong);
        btnWolseong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Step1_4Activity.class);
                startActivity(intent);
            }
        });

        // 용강동 버튼 클릭 이벤트
        ImageButton btnYonggang = findViewById(R.id.btnYonggang);
        btnYonggang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Step1_5Activity.class);
                startActivity(intent);
            }
        });

        // 황성동 버튼 클릭 이벤트
        ImageButton btnHwangseong = findViewById(R.id.btnHwangseong);
        btnHwangseong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Step1_6Activity.class);
                startActivity(intent);
            }
        });

        // Login 버튼 클릭 이벤트
        Button btnLogin = findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
