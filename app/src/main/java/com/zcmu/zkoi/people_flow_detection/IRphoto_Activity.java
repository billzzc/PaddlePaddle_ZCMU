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

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@ContentView(R.layout.activity_ir)
public class IRphoto_Activity extends AppCompatActivity {

    public String url = "http://172.19.14.105:8080/Android/IRphotoup";

    private static final int TAKE_PHOTO=1;
    @ViewInject(R.id.IR_picture)
    private ImageView imageView;
    private static Uri uri1;
    private Uri uri;


    Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = "";
            if ("OK".equals(msg.obj.toString())){
                result = "上传成功";
            }else
                result = "上传失败";
            Toast.makeText(IRphoto_Activity.this,result,Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        takephoto();
        x.view().inject(this);
    }

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
            uri = FileProvider.getUriForFile(IRphoto_Activity.this,"com.zcmu.zkoi.paddlepaddle",outImage);
            uri1 = uri;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,TAKE_PHOTO);
    }

    public void photoup(String url) throws URISyntaxException {
        File file = new File(new URI(getExternalCacheDir().toString()+"/output_image.jpg")) ;
        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        params.addBodyParameter("IRphoto",file);
        Callback.Cancelable cancelable= x.http().post(params,new Callback.ProgressCallback<String>(){

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

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

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

    @Event(type = View.OnClickListener.class,value = {R.id.btn_photoup2,R.id.btn_IR_Rephoto2})
    private void OnClick(View view) throws URISyntaxException {
        switch(view.getId()){
            case R.id.btn_photoup2:
//                photoup(url);
                Toast.makeText(IRphoto_Activity.this,"识别成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(IRphoto_Activity.this,IRresultActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_IR_Rephoto2:
                takephoto();
                break;
                default:
                    break;
        }
    }

}
