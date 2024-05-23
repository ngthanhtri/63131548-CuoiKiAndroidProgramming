package thanhtri63131548.ntuedu.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ManHinhGioiThieu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhgioithieu);
        new Handler().postDelayed(new Runnable(){
                @Override
                public void run(){
                    startActivity(new Intent(ManHinhGioiThieu.this,DangNhapSDT.class));
                    finish();
                }
        },3000);
    }
}