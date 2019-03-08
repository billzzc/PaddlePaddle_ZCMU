package com.zcmu.zkoi.people_flow_detection;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_insrec)
public class Insrec_Activity extends AppCompatActivity {

    @ViewInject(R.id.textView_classhelper)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);
    }

    @Event(type = View.OnClickListener.class,value = R.id.btn_IRphoto)
    private void OnClickListener(View view){
        switch(view.getId()){
            case R.id.btn_IRphoto:
                Intent intent = new Intent(Insrec_Activity.this,IRphoto_Activity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }

}
