package thanhtri63131548.ntuedu.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DangNhapOTP extends AppCompatActivity {

    String sdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhapotp);

        sdt = getIntent().getExtras().getString("dt");
        Toast.makeText(getApplication(),sdt, Toast.LENGTH_LONG).show();


    }
}