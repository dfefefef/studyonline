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
import com.avos.avoscloud.RequestPasswordResetCallback;

public class Login_03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_03);
        setTitle("找回密码");
        Button wj = (Button)findViewById(R.id.wj_btn_1);
        wj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail =((EditText)findViewById(R.id.wj_et_1)).getText().toString();
                AVUser.requestPasswordResetInBackground(mail, new RequestPasswordResetCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            Toast.makeText(Login_03.this,"已发送重置密码邮件！",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login_03.this,Login_01.class));
                        } else {
                            e.printStackTrace();
                            Toast.makeText(Login_03.this,"修改失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
