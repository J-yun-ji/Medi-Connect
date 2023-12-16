package com.example.medical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
public class Membership extends AppCompatActivity {
    // 로그 찍을 때 사용하는 TAG 변수
    final private String TAG = getClass().getSimpleName();
    // 사용할 컴포넌트 선언
    ImageButton btn_register;
    ImageButton mainbtn;
    ImageButton finish;

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스 (서버연동)
    private EditText mEtId, mEtPwd, mEtName, mEtTel, mEtNick; //회원가입 입력필드
    private ImageButton mBtnRegister; // 회원가입 버튼

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Medical");

        mEtId = findViewById(R.id.et_id);
        mEtName = findViewById(R.id.et_name);
        mEtNick = findViewById(R.id.et_nick);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtTel = findViewById(R.id.et_tel);
        mBtnRegister = findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEtName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (mEtTel.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (mEtId.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (mEtPwd.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (mEtNick.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    //회원가입 처리 시작
                    String strId = mEtId.getText().toString();
                    String strPwd = mEtPwd.getText().toString();
                    String strTel = mEtTel.getText().toString();
                    String strName = mEtName.getText().toString();
                    String strNick = mEtNick.getText().toString();
                    //Firebase Auth 진행
                    mFirebaseAuth.createUserWithEmailAndPassword(strId, strPwd).addOnCompleteListener(Membership.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                                UserAccount account = new UserAccount();
                                account.setEmailId(firebaseUser.getEmail());
                                account.setIdToken(firebaseUser.getUid());
                                account.setPassword(strPwd);
                                account.setName(strName);
                                account.setNickname(strNick);
                                account.setTel(strTel);

                                //setValue는 database에 삽입하는 행위
                                mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                                Toast.makeText(Membership.this, "회원가입에 성공하셨습니다!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Membership.this, "회원가입에 실패하셨습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                            }

                        }


                    });
                }
            }
        });
    }
}