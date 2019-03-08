package com.zcmu.zkoi.people_flow_detection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.activity_drstatus)
public class Drstatus_Activity extends AppCompatActivity {

    private String url = "http://172.19.43.219:8080/Android/dr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        RequestParams requestParams = new RequestParams(url);
    }
}
