package com.example.lab.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.RecyclerCallback;
import com.example.lab.model.RentalItem;

public class RentalItemAdapter extends ListAdapter<RentalItem, RentalItemAdapter.RentalItemViewHolder> {

    private final RecyclerCallback<RentalItem> callback;

    public RentalItemAdapter(RecyclerCallback<RentalItem> callback){
        super(new DiffUtil.ItemCallback<RentalItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull RentalItem oldItem, @NonNull RentalItem newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull RentalItem oldItem, @NonNull RentalItem newItem) {
                return oldItem.getName().equals(newItem.getName()) && oldItem.getId() == newItem.getId();
            }
        });
        this.callback = callback;
    }

    @NonNull
    @Override
    public RentalItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rental_item, parent, false);
        return new RentalItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class RentalItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private RentalItem rentalItem;

        public RentalItemViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.rental_item);
            itemView.setOnClickListener(v -> {callback.returnValue(rentalItem); });
        }

        public void bind(RentalItem rentalItem){
            this.rentalItem = rentalItem;
            this.name.setText(rentalItem.getName());
        }
    }
}
