package com.zcmu.zkoi.people_flow_detection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.activity_irresult)
public class IRresultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(IRresultActivity.this,"识别成功",Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
