package com.zcmu.zkoi.people_flow_detection;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Map;

@ContentView(R.layout.activity_reigst_)
public class Reigst_Activity extends AppCompatActivity {

    @ViewInject(R.id.username_regist)
    private EditText username;
    @ViewInject(R.id.password_regist)
    private EditText password;
    @ViewInject(R.id.password_regist_1)
    private EditText password_1;
    @ViewInject(R.id.tel_regist_)
    private EditText tel;
    @ViewInject(R.id.school_regist)
    private EditText school;

    private String originAddress = "http://172.19.14.105:8080/Android/Regist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    //检验合法性
    private boolean  isInputValid(){
        if(username.getText().toString().isEmpty()||password.getText().toString().isEmpty()||password_1.getText().toString().isEmpty()||tel.getText().toString().isEmpty()||school.getText().toString().isEmpty()){
            Toast.makeText(Reigst_Activity.this,"信息不得为空",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!password.getText().toString().equals(password_1)){
            Toast.makeText(Reigst_Activity.this,"第一次密码与第二次不相同",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    Handler mhandler = new Handler(){
        public void handlMessage(Message msg){
            super.handleMessage(msg);
            String result = "";
            if("OK".equals(msg.obj.toString())){
                result = "注册成功";
                Intent intent = new Intent(Reigst_Activity.this,Home_Activity.class);
                startActivity(intent);
            }else{
                result = "该用户已存在";
            }
            Toast.makeText(Reigst_Activity.this,result,Toast.LENGTH_LONG).show();
        }
    };

    public void Regist(){
        if(!isInputValid()){
            return ;
        }
        RequestParams params = new RequestParams(originAddress);
        params.addParameter("regist_username",username);
        params.addParameter("regist_password",password);
        params.addParameter("regist_tel",tel);
        params.addParameter("regist_school",school);
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Message message = new Message();
                message.obj = result;
                mhandler.sendMessage(message);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Event(type = View.OnClickListener.class,value = R.id.register_do)
    private void OnClick(View view){
        switch (view.getId()){
            case R.id.register_do:
                Regist();
        }
    }
}
