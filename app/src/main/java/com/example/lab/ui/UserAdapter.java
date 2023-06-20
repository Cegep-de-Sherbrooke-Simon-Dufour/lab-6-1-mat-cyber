package com.example.lab.ui;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.RecyclerCallback;
import com.example.lab.model.User;

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
                return oldUser.getName().equals(newUser.getName())
                        && oldUser.getEmail().equals(newUser.getEmail())
                        && oldUser.getUri().equals(newUser.getUri());
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
        private final ImageView image;
        private User user;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            email = itemView.findViewById(R.id.user_email);
            image = itemView.findViewById(R.id.user_image);
            itemView.setOnClickListener(view -> { callback.returnValue(user); });
        }

        public void bind(User user){
            this.user = user;
            this.name.setText(user.getName());
            this.email.setText(user.getEmail());
            if(user.getUri() != null) image.setImageURI(Uri.parse(user.getUri()));
        }
    }
}
