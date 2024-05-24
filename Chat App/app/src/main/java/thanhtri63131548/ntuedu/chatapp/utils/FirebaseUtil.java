package thanhtri63131548.ntuedu.chatapp.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtil {

    public static String userIDhientai(){
        return FirebaseAuth.getInstance().getUid();
    }

    public static boolean isLoggedIn(){
        if(userIDhientai()!=null){
            return true;
        }
        return false;
    }
    public static DocumentReference thongtinUserhientai(){
        return FirebaseFirestore.getInstance().collection("users").document(userIDhientai());
    }

    public static CollectionReference allUserCollectionReference(){
        return FirebaseFirestore.getInstance().collection("users");
    }

}
