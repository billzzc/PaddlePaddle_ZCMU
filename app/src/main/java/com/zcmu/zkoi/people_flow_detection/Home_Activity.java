package com.zcmu.zkoi.people_flow_detection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

@ContentView(R.layout.activity_home_)
public class Home_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(type = View.OnClickListener.class , value = {R.id.btn_dining,R.id.btn_library,R.id.btn_calsshelper,R.id.btn_wall,R.id.btn_personpage})
    private void OnClick(View view){
        switch(view.getId()){
            case R.id.btn_dining:
                Intent intent1 = new Intent(Home_Activity.this,diningRoom_Activity.class);
                startActivity(intent1);
                break;
            case R.id.btn_calsshelper:
                Intent intent2 = new Intent(Home_Activity.this,Insrec_Activity.class);
                startActivity(intent2);
                break;
            case R.id.btn_library:
                Intent intent = new Intent(Home_Activity.this,Library_1_Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_wall:
                Toast.makeText(Home_Activity.this,"敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_personpage:
                Intent intent4 = new Intent(Home_Activity.this,PersonpageActivity.class);
                startActivity(intent4);
                default:
                    break;
        }
    }

}
