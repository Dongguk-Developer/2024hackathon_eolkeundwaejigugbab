package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText loginId, loginPassword;
    private Button loginButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // login.xml 레이아웃 사용

        // EditText 필드 연결
        loginId = findViewById(R.id.username);
        loginPassword = findViewById(R.id.password);

        // 버튼 연결
        loginButton = findViewById(R.id.loginButton); // 로그인 버튼
        signUpButton = findViewById(R.id.signUpButton); // 회원가입 버튼

        // 로그인 버튼 클릭 이벤트
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = loginId.getText().toString();
                String userPassword = loginPassword.getText().toString();

                // 서버 통신 및 로그인 처리
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Toast.makeText(LoginActivity.this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();

                                String userID = jsonResponse.getString("userID");
                                String userPassword = jsonResponse.getString("userPassword");

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                // 사용자 정보 전달
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPassword", userPassword);
                                startActivity(intent);
                                finish(); // 현재 Activity 종료
                            } else {
                                Toast.makeText(LoginActivity.this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                // 서버 요청 실행
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        // 회원가입 버튼 클릭 이벤트
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,Signup_Php_Mysql.class);
                startActivity(intent);
            }
        });
    }
}
