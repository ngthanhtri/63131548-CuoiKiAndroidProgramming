package thanhtri63131548.ntuedu.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import thanhtri63131548.ntuedu.chatapp.model.UserModel;
import thanhtri63131548.ntuedu.chatapp.utils.AndroidUtil;
import thanhtri63131548.ntuedu.chatapp.utils.FirebaseUtil;

public class ManHinhGioiThieu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhgioithieu);

        if(getIntent().getExtras()!=null){

            String userId = getIntent().getExtras().getString("userId");
            FirebaseUtil.allUserCollectionReference().document(userId).get()
                    .addOnCompleteListener(task -> {
                       if(task.isSuccessful()){
                           UserModel model = task.getResult().toObject(UserModel.class);

                           Intent mainIntent = new Intent(this, MainActivity.class);
                           mainIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                           startActivity(mainIntent);

                           Intent intent = new Intent(this, ChatActivity.class);
                           AndroidUtil.passUserModelAsIntent(intent,model);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(intent);
                           finish();
                       }
                    });
        }else{
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
}