package com.zcmu.zkoi.people_flow_detection;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.zcmu.zkoi.people_flow_detection.Utils.LocationService;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

@ContentView(R.layout.activity_lrphoto)
public class LrphotoActivity extends AppCompatActivity {

    private static double latitude;
    private static double longitude;
    private LocationService locationService;
    public static final int TAKE_PHOTO=1;
    public String url = "http://172.19.43.219:8080/Android/drphotoup";
    private static String uri1;
    private Uri uri;
    @ViewInject(R.id.lr_picture)
    private ImageView imageView;


    Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = "";
//            if ("OK".equals(msg.obj.toString())){
//                result = "上传成功";
//            }else
//                result = msg.toString();
            Toast.makeText(LrphotoActivity.this,msg.toString(),Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
//        locationService.start();
//        locationService = new LocationService(this);
//        locationService.registerListener(mListener);
//        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        takephoto();
    }


//    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            latitude = location.getLatitude();
//            longitude = location.getLongitude();
//        }
//    };

    //拍照
    public void takephoto(){

        File outImage = new File(getExternalCacheDir(),"output_image.jpg");
        try{
            if(outImage.exists()){
                outImage.delete();
            }
            outImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT>=24){
            uri = FileProvider.getUriForFile(LrphotoActivity.this,"com.zcmu.zkoi.paddlepaddle",outImage);
            uri1 = uri.toString();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,TAKE_PHOTO);
    }

    public void photoUp(String url) throws URISyntaxException {
        File file = new File(getExternalCacheDir().toString()+"/output_image.jpg");
        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        params.addBodyParameter("file",file);
//        params.addBodyParameter("s","1");
//       params.addBodyParameter("latitude",String.valueOf(latitude));
//        params.addBodyParameter("longitude",String.valueOf(longitude));
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


    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode == RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Event(type = View.OnClickListener.class,value = {R.id.btn_lr_upload,R.id.btn_lr_retake})
    private void OnClick(View view) throws URISyntaxException{
        switch(view.getId()){
            case R.id.btn_lr_upload:
//                photoUp(url);
                Toast.makeText(LrphotoActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LrphotoActivity.this,Library_1_Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_lr_retake:
                takephoto();
                break;
            default:
                break;
        }
    }

}

