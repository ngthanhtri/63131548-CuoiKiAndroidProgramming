package thanhtri63131548.ntuedu.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Document;

import thanhtri63131548.ntuedu.chatapp.model.UserModel;
import thanhtri63131548.ntuedu.chatapp.utils.FirebaseUtil;

public class DangNhapUsername extends AppCompatActivity {

    EditText usernameInput;
    Button hoanthanhBtn;
    ProgressBar progressBar;
    UserModel userModel;

    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhapusername);

        usernameInput = findViewById(R.id.login_username);
        hoanthanhBtn = findViewById(R.id.login_hoanthanh_btn);
        progressBar = findViewById(R.id.login_progress_bar);

        phoneNumber = getIntent().getExtras().getString("dt");
        getUsername();

        hoanthanhBtn.setOnClickListener(v -> {
            setUsername();
        });
    }


    void setUsername(){
        String username = usernameInput.getText().toString();
        if(username.isEmpty() || username.length()<3){
            usernameInput.setError("Username phải có ít nhất 3 ký tự");
            return;
        }
        setInProgress(true);

        if(userModel!=null){
            userModel.setUsername(username);
        }else{
            userModel = new UserModel(phoneNumber,username, Timestamp.now());
        }

        FirebaseUtil.thongtinUserhientai().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    Intent intent = new Intent(DangNhapUsername.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }
    void getUsername() {
        setInProgress(true);
        FirebaseUtil.thongtinUserhientai().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    userModel = task.getResult().toObject(UserModel.class);
                    if(userModel!=null){
                        usernameInput.setText(userModel.getUsername());
                    }
                }
            }
        });

    }

    void setInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            hoanthanhBtn.setVisibility(View.GONE);

        } else {
            progressBar.setVisibility(View.GONE);
            hoanthanhBtn.setVisibility(View.VISIBLE);
        }
    }
}