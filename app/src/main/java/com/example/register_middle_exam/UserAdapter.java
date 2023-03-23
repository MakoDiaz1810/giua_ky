package com.example.register_middle_exam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> mListUser;

    public void setData(List<User> list){
        this.mListUser = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = mListUser.get(position);
        if(user == null ){
            return;
        }
        holder.tvEmail.setText(user.getEmail());
        holder.tvUsername.setText(user.getUsername());
        holder.tvPassword.setText(user.getPassword());
        holder.tvConfirmPass.setText(user.getConfimpass());
    }

    @Override
    public int getItemCount() {
        if(mListUser != null) {
            return mListUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView tvEmail;
        private TextView tvUsername;
        private TextView tvPassword;
        private TextView tvConfirmPass;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

           tvEmail = itemView.findViewById(R.id.tv_email);
           tvUsername = itemView.findViewById(R.id.tv_username);
           tvPassword= itemView.findViewById(R.id.tv_password);
           tvConfirmPass = itemView.findViewById(R.id.edt_confirm_password);
        }
    }
}
