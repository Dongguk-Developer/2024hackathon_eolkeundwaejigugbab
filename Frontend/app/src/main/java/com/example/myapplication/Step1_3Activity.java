package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Step1_3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step1_3); // step1_3.xml 레이아웃 사용

        // seonggeon_item1 버튼 클릭 시 step1_3_1.xml로 이동
        ImageButton seonggeonItem1 = findViewById(R.id.seonggeon_item1);
        seonggeonItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Step1_3Activity.this, Step1_3_1Activity.class);
                startActivity(intent);
            }
        });
    }
}
