package thanhtri63131548.ntuedu.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import thanhtri63131548.ntuedu.chatapp.utils.AndroidUtil;

public class DangNhapOTP extends AppCompatActivity {

    String sdt;
    Long timeoutSecond = 60L;
    String xacthucCode;
    PhoneAuthProvider.ForceResendingToken resendingToken;

    EditText otpInput;
    Button tieptheoBtn;
    ProgressBar progressBar;
    TextView guilaiOtpTv;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhapotp);

        otpInput = findViewById(R.id.login_otp);
        tieptheoBtn = findViewById(R.id.login_next_btn);
        progressBar = findViewById(R.id.login_progress_bar);
        guilaiOtpTv = findViewById(R.id.resend_otp_textview);

        sdt = getIntent().getExtras().getString("dt");
        guiOTP(sdt, false);
    }
    void guiOTP (String sdt,boolean isResend){
        setInProgress(true);
        PhoneAuthOptions.Builder builder =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(sdt)
                        .setTimeout(timeoutSecond, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                dangNhap(phoneAuthCredential);
                                setInProgress(false);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                AndroidUtil.hienthiToast(getApplicationContext(),"Xác thực OTP thất bại");
                                setInProgress(false);
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                xacthucCode = s;
                                resendingToken = forceResendingToken;
                                AndroidUtil.hienthiToast(getApplicationContext(),"Gửi mã OTP thành công");
                                setInProgress(false);
                            }
                        });
        if(isResend){
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
        }else{
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }
    }

    void setInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            tieptheoBtn.setVisibility(View.GONE);

        } else {
            progressBar.setVisibility(View.GONE);
            tieptheoBtn.setVisibility(View.VISIBLE);
        }
    }
    void dangNhap(PhoneAuthCredential phoneAuthCredential){

    }
}