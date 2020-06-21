package com.example.shenglin.studyingonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

public class Login_02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_02);
        setTitle("注册");
        Button mzc = (Button) findViewById(R.id.button);
        mzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.editText2)).getText().toString();
                String pwd = ((EditText) findViewById(R.id.editText3)).getText().toString();
                String mail = ((EditText) findViewById(R.id.editText)).getText().toString();
                AVUser user = new AVUser();// 新建 AVUser 对象实例
                user.setUsername(name);// 设置用户名
                user.setPassword(pwd);// 设置密码
                user.setEmail(mail);// 设置邮箱
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            Toast.makeText(Login_02.this, "注册成功", Toast.LENGTH_SHORT).show();
                            // 注册成功
                            startActivity(new Intent(Login_02.this,Login_01.class));
                        } else {
                            Toast.makeText(Login_02.this, "注册失败", Toast.LENGTH_SHORT).show();
                            // 失败的原因可能有多种，常见的是用户名已经存在。
                        }
                    }

                });
            }


        });
    }
}
