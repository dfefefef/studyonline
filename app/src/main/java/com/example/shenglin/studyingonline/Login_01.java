package com.example.shenglin.studyingonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

public class Login_01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_01);

        Button mdl=(Button)findViewById(R.id.登录);
        mdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yhm=((EditText) findViewById(R.id.用户名)).getText().toString();
                String psw=((EditText) findViewById(R.id.密码)).getText().toString();
                AVUser.logInInBackground(yhm, psw, new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        if (e == null) {
                            Toast.makeText(Login_01.this,"登陆成功",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login_01.this,Main_01.class));
                        }
                        else
                            Toast.makeText(Login_01.this,"登陆失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button mzc=(Button)findViewById(R.id.注册);
        mzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_01.this,Login_02.class));
            }
        });

        Button mzh = (Button)findViewById(R.id.忘记密码);
        mzh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_01.this,Login_03.class));
            }
        });
    }
}
