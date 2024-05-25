package thanhtri63131548.ntuedu.chatapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import thanhtri63131548.ntuedu.chatapp.model.UserModel;
import thanhtri63131548.ntuedu.chatapp.utils.AndroidUtil;
import thanhtri63131548.ntuedu.chatapp.utils.FirebaseUtil;

public class ProfileFragment extends Fragment {

    ImageView profilePic;
    EditText usernameInput;
    EditText phoneInput;
    Button updateProfileBtn;
    ProgressBar progressBar;
    TextView logoutBtn;

    UserModel currentUserModel;


    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        profilePic = view.findViewById(R.id.profile_image_view);
        usernameInput = view.findViewById(R.id.profile_username);
        phoneInput = view.findViewById(R.id.profile_phone);
        updateProfileBtn = view.findViewById(R.id.profle_update_btn);
        progressBar = view.findViewById(R.id.profile_progress_bar);
        logoutBtn = view.findViewById(R.id.logout_btn);

        getUSerData();

        updateProfileBtn.setOnClickListener((v -> {
            updateBtnClick();
        }));

        logoutBtn.setOnClickListener((v -> {
            FirebaseUtil.logout();
            Intent intent = new Intent(getContext(), ManHinhGioiThieu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }));

        return view;
    }

    void updateBtnClick(){
        String newUsername = usernameInput.getText().toString();
        if(newUsername.isEmpty() || newUsername.length()<3){
            usernameInput.setError("Username phải có ít nhất 3 ký tự");
            return;
        }
        currentUserModel.setUsername(newUsername);
        setInProgress(true);
        updateToFirestore();
    }

    void updateToFirestore(){
        FirebaseUtil.thongtinUserhientai().set(currentUserModel)
                .addOnCompleteListener(task -> {
                    setInProgress(false);
                   if(task.isSuccessful()){
                       AndroidUtil.hienthiToast(getContext(),"Cập nhật thành công");
                   }else{
                       AndroidUtil.hienthiToast(getContext(),"Cập nhật thất bại");
                   }
                });
    }

    void getUSerData(){
        setInProgress(true);
        FirebaseUtil.thongtinUserhientai().get().addOnCompleteListener(task -> {
            setInProgress(false);
            currentUserModel = task.getResult().toObject(UserModel.class);
            usernameInput.setText(currentUserModel.getUsername());
            phoneInput.setText(currentUserModel.getPhone());
        });
    }

    void setInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            updateProfileBtn.setVisibility(View.GONE);

        } else {
            progressBar.setVisibility(View.GONE);
            updateProfileBtn.setVisibility(View.VISIBLE);
        }
    }
}