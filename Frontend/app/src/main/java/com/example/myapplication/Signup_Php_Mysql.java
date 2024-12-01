package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Signup_Php_Mysql extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static String IP_ADDRESS = "43.202.183.129";
    private static String TAG = "phptest";

    private EditText signup_id, signup_pwd, signup_name, signup_date, signup_phone;
    private Button signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        signup_id = findViewById(R.id.signup_id);
        signup_pwd = findViewById(R.id.signup_pwd);
        signup_name = findViewById(R.id.signup_name);
        signup_date = findViewById(R.id.signup_date);
        signup_phone = findViewById(R.id.signup_phone);
        signup_button = findViewById(R.id.signup_button);

        signup_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = signup_id.getText().toString().trim();
                String pwd = signup_pwd.getText().toString().trim();
                String name = signup_name.getText().toString().trim();
                String date = signup_date.getText().toString().trim();
                String phone = signup_phone.getText().toString().trim();

                if (email.equals("") || pwd.equals("") || name.equals("") || date.equals("") || phone.equals("")) {
                    Toast.makeText(Signup_Php_Mysql.this, "정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (pwd.length() <= 5) {
                    Toast.makeText(Signup_Php_Mysql.this, "비밀번호를 6자리 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!email.contains("@") || !email.contains(".com")) {
                    Toast.makeText(Signup_Php_Mysql.this, "아이디에 @ 및 .com을 포함시키세요.", Toast.LENGTH_SHORT).show();
                } else if (!phone.matches("\\d{10,11}")) { // 수정된 전화번호 검증
                    Toast.makeText(Signup_Php_Mysql.this, "올바른 전화번호 형식으로 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    InsertData task = new InsertData();
                    task.execute("http://" + IP_ADDRESS + "/android_log_insert_php.php", email, email, pwd, phone, name, date);
                }
            }
        });
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Signup_Php_Mysql.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            // 결과를 로그에 출력
            Log.d(TAG, "POST response - " + result);

            // 성공 여부 확인 및 화면 전환
            if (result.contains("success")) { // 서버에서 성공 응답을 받았을 때
                Toast.makeText(Signup_Php_Mysql.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                // 로그인 화면으로 이동
                Intent intent = new Intent(Signup_Php_Mysql.this, LoginActivity.class);
                startActivity(intent);

                // 회원가입 화면 종료
                finish();
            } else { // 실패한 경우
                Toast.makeText(Signup_Php_Mysql.this, "회원가입에 실패했습니다. 다시 시도하세요.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];
            String useremail = params[1];
            String userid = params[2];
            String userpw = params[3];
            String userphone = params[4];
            String username = params[5];
            String userdate = params[6];

            String postParameters = "useremail=" + useremail + "&userid=" + userid
                    + "&password=" + userpw + "&userphone=" + userphone
                    + "&username=" + username + "&userdate=" + userdate
                    + "&submit=1";

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));

                Log.d("phpData", postParameters);

                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                InputStream inputStream;

                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString();

            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error", e);
                return new String("Error " + e.getMessage());
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
