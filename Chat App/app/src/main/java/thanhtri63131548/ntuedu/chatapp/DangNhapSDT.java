package thanhtri63131548.ntuedu.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hbb20.CountryCodePicker;

public class DangNhapSDT extends AppCompatActivity {

    CountryCodePicker maquocgia;
    EditText sdtInput;
    Button btnGuiOTP;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhapsdt);

        maquocgia = findViewById(R.id.login_countrycode);
        sdtInput = findViewById(R.id.login_mobile_number);
        btnGuiOTP = findViewById(R.id.send_otp_btn);
        progressBar = findViewById(R.id.login_progress_bar);

        progressBar.setVisibility(View.GONE);

        maquocgia.registerCarrierNumberEditText(sdtInput);
        btnGuiOTP.setOnClickListener((v)-> {
            if(!maquocgia.isValidFullNumber()){
                sdtInput.setError("Số điện thoại không đúng");
                return;
            }
            Intent intent = new Intent(DangNhapSDT.this,DangNhapOTP.class);
            intent.putExtra("dt",maquocgia.getFullNumberWithPlus());
            startActivity(intent);
        });
    }
}