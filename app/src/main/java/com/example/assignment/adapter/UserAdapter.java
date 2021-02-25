package com.example.assignment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.model.UserModel;

public class UserAdapter extends ListAdapter<UserModel,RecyclerView.ViewHolder> {

    private OnItemClickListener mListener;

    public UserAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<UserModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserModel oldItem, @NonNull UserModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserModel oldItem, @NonNull UserModel newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getDob().equals(newItem.getDob()) &&
                    oldItem.getPhone().equals(newItem.getPhone());
        }
    };

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId;
        private TextView tvName;
        private TextView tvDob;
        private TextView tvPhone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDob = itemView.findViewById(R.id.tv_dob);
            tvPhone = itemView.findViewById(R.id.tv_phone);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext())
                .inflate(R.layout.user_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;

        final UserModel user = getItem(position);

        holder.tvId.setText(String.valueOf(user.getId()));
        holder.tvName.setText(user.getName());
        holder.tvDob.setText(user.getDob());
        holder.tvPhone.setText(user.getPhone());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListener !=null){
                    mListener.onItemLongClick(v, position);
                }
                return false;
            }
        });
    }

    public UserModel getUserAt(int position){
        return getItem(position);
    }


    public interface OnItemClickListener{
        void onItemClick(UserModel user);
        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(@Nullable OnItemClickListener l){
        this.mListener = l;
    }
}

