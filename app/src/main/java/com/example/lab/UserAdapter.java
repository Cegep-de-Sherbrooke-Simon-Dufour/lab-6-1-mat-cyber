package com.example.lab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends ListAdapter<User, UserAdapter.UserViewHolder> {

    private final RecyclerCallback<User> callback;
    public UserAdapter(RecyclerCallback<User> callback) {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(User oldUser, User newUser) {
                return oldUser == newUser;
            }

            @Override
            public boolean areContentsTheSame(User oldUser, User newUser) {
                return oldUser.getName().equals(newUser.getName()) && oldUser.getEmail().equals(newUser.getEmail());
            }
        });
        this.callback = callback;
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, email;
        private User user;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            email = itemView.findViewById(R.id.user_email);
            itemView.setOnClickListener(view -> { callback.returnValue(user); });
        }

        public void bind(User user){
            this.user = user;
            this.name.setText(user.getName());
            this.email.setText(user.getEmail());
        }
    }
}
