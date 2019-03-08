package com.zcmu.zkoi.people_flow_detection;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(R.layout.activity_main)
public class Login_Activity extends AppCompatActivity  {
    @ViewInject(R.id.username)
    private EditText username;
    @ViewInject(R.id.password)
    private EditText password;
    //用于接收Http请求的servlet的URL地址，请自己定义
    private String originAddress = "http://172.19.14.105:8080/Android/Login";

//    用于页面跳转的Intent
//    用于处理消息的Handler
    Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = "";
            if ("OK".equals(msg.obj.toString())){
                result = "登录成功";
            }else
                result = "用户名或者密码错误";
            Toast.makeText(Login_Activity.this,result,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login_Activity.this,Home_Activity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
    public void login() {
        //检查用户输入的账号和密码的合法性
        if (!isInputValid()){
            Toast.makeText(Login_Activity.this,"帐号和密码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        RequestParams params = new RequestParams(originAddress);
        params.addBodyParameter("userName",username.getText().toString());
        params.addBodyParameter("userPassword",password.getText().toString());
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Message message = new Message();
                message.obj = result;
                mHandler.sendMessage(message);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Message message = new Message();
                message.obj = ex.toString();
                mHandler.sendMessage(message);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private boolean isInputValid() {
        //检查用户输入的合法性，这里暂且默认用户输入合法
        if(username.getText().toString().isEmpty()||password.getText().toString().isEmpty())
            return false;
        else
            return true;
    }

    @Event(type = View.OnClickListener.class,value = {R.id.login,R.id.regist})
    private void OnClick(View v) {
        switch (v.getId()){
            case R.id.login:
//              login();
//              intent.setClass(Login_Activity.this,Home_Activity.class);
                Intent intent1 = new Intent(Login_Activity.this,Home_Activity.class);
                startActivity(intent1);
                break;
            case R.id.regist:
                Intent intent = new Intent(Login_Activity.this,Reigst_Activity.class);
                startActivity(intent);
                break;
        }

    }

}
