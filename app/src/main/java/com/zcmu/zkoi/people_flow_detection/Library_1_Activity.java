package com.zcmu.zkoi.people_flow_detection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_library_1_)
public class Library_1_Activity extends AppCompatActivity {

    @ViewInject(R.id.txt_lbjam)
    private TextView txt_lbjam;

    @ViewInject(R.id.txt_empty)
    private TextView txt_empty;

    @ViewInject(R.id.txt_library)
    private TextView txt_library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(type = View.OnClickListener.class , value = {R.id.btn_lbstatus,R.id.btn_library_ul1})
    private void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_lbstatus:
                txt_library.setAlpha(1);
                txt_lbjam.setAlpha(1);
                txt_empty.setAlpha(1);
                break;
            case R.id.btn_library_ul1:
                Intent intent = new Intent(Library_1_Activity.this,LrphotoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
