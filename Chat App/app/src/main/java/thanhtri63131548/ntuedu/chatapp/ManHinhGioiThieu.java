package thanhtri63131548.ntuedu.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import thanhtri63131548.ntuedu.chatapp.utils.FirebaseUtil;

public class ManHinhGioiThieu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhgioithieu);
        new Handler().postDelayed(new Runnable(){
                @Override
                public void run(){
                    if(FirebaseUtil.isLoggedIn()){
                        startActivity(new Intent(ManHinhGioiThieu.this, MainActivity.class));
                    }else{
                        startActivity(new Intent(ManHinhGioiThieu.this,DangNhapSDT.class));
                    }
                    finish();
                }
        },1000);
    }
}