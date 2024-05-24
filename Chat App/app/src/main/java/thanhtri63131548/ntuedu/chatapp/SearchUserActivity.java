package thanhtri63131548.ntuedu.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchUserActivity extends AppCompatActivity {

    EditText searchInput;
    ImageButton searchButton;
    ImageButton backButton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchuser);

        searchButton = findViewById(R.id.search_user_btn);
        searchInput = findViewById(R.id.seach_username_input);
        backButton = findViewById(R.id.back_btn);
        recyclerView = findViewById(R.id.search_user_recycler_view);

        searchInput.requestFocus();

        backButton.setOnClickListener(v -> {
            onBackPressed();
        });

        searchButton.setOnClickListener(v -> {
            String searchTukhoa = searchInput.getText().toString();
            if(searchTukhoa.isEmpty() || searchTukhoa.length()<3){
                searchInput.setError("Username không hợp lệ");
                return;
            }
            setupSearchRecyclerView(searchTukhoa);
        });
    }
    void setupSearchRecyclerView(String searchTuKhoa){

    }

}