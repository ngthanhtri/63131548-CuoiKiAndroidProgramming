package thanhtri63131548.ntuedu.chatapp.utils;

import android.content.Context;
import android.widget.Toast;
public class AndroidUtil {
    public static  void hienthiToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
