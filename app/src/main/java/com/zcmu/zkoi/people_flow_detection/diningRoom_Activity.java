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

@ContentView(R.layout.activity_dining_room_)
public class diningRoom_Activity extends AppCompatActivity {

    @ViewInject(R.id.txt_jam)
    private TextView txt_jam;

    @ViewInject(R.id.txt_wait)
    private TextView txt_wait;

    @ViewInject(R.id.txt_diningRoom)
    private TextView txt_diningRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        txt_jam.setTypeface(Typeface.createFromAsset(getAssets(),"font/simhei.ttf"));
//        txt_wait.setTypeface(Typeface.createFromAsset(getAssets(),"font/simhei.ttf"));
//        txt_jam.setAlpha(0);
//        txt_wait.setAlpha(0);
        x.view().inject(this);
    }



    @Event(type = View.OnClickListener.class,value = {R.id.btn_drphoto,R.id.btn_drstatus})
    private void OnClick(View view){
        switch(view.getId()){
            case R.id.btn_drphoto:
                Intent intent = new Intent(diningRoom_Activity.this,DrphotoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_drstatus:
                txt_wait.setAlpha(1);
                txt_jam.setAlpha(1);
                txt_diningRoom.setAlpha(1);
            default:
                break;
        }
    }
}
