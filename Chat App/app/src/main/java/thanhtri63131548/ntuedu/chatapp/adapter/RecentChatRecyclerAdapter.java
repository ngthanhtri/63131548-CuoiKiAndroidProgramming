package thanhtri63131548.ntuedu.chatapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import thanhtri63131548.ntuedu.chatapp.ChatActivity;
import thanhtri63131548.ntuedu.chatapp.R;
import thanhtri63131548.ntuedu.chatapp.model.ChatroomModel;
import thanhtri63131548.ntuedu.chatapp.model.UserModel;
import thanhtri63131548.ntuedu.chatapp.utils.AndroidUtil;
import thanhtri63131548.ntuedu.chatapp.utils.FirebaseUtil;

public class RecentChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatroomModel,RecentChatRecyclerAdapter.ChatroomModelViewHolder> {


    Context context;
    public RecentChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatroomModel> options, Context context){
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatroomModelViewHolder holder, int position, @NonNull ChatroomModel model) {
        FirebaseUtil.getOtherUserFromChatroom(model.getUserIds())
                .get().addOnCompleteListener(task -> {
                   if(task.isSuccessful()){
                       boolean lastMessageSentByMe = model.getLastMessageSenderId().equals(FirebaseUtil.userIDhientai());


                       UserModel otherUserModel = task.getResult().toObject(UserModel.class);

                       FirebaseUtil.getOtherProfilePicStorageRef(otherUserModel.getUserId()).getDownloadUrl()
                               .addOnCompleteListener(t -> {
                                   if(t.isSuccessful()){
                                       Uri uri = t.getResult();
                                       AndroidUtil.setProfilePic(context,uri,holder.profilePic);
                                   }
                               });



                       holder.usernameText.setText(otherUserModel.getUsername());
                       if(lastMessageSentByMe)
                           holder.lastMessageText.setText("Bạn : "+model.getLastMessage());
                        else
                           holder.lastMessageText.setText(model.getLastMessage());

                       holder.lastMessageTime.setText(FirebaseUtil.timestampToString(model.getLastMessageTimestamp()));

                       holder.itemView.setOnClickListener(v -> {
                           Intent intent = new Intent(context, ChatActivity.class);
                           AndroidUtil.passUserModelAsIntent(intent,otherUserModel);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           context.startActivity(intent);
                       });
                   }
                });
    }

    @NonNull
    @Override
    public ChatroomModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recent_chat_recycler_row,parent,false);
        return new ChatroomModelViewHolder(view);
    }

    class ChatroomModelViewHolder extends RecyclerView.ViewHolder{
        TextView usernameText;
        TextView lastMessageText;
        TextView lastMessageTime;
        ImageView profilePic;

        public ChatroomModelViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.user_name_text);
            lastMessageText = itemView.findViewById(R.id.last_message_text);
            lastMessageTime = itemView.findViewById(R.id.last_message_time_text);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }
    }
}
