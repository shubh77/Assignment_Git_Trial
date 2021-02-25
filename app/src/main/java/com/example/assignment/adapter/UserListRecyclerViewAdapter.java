package com.example.assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.model.UserModel;
import com.example.assignment.repository.UserRepo;
import com.example.assignment.utils.Constant;
import com.example.assignment.view.AddEditUserActivity;

import java.util.ArrayList;
import java.util.List;


public class UserListRecyclerViewAdapter extends RecyclerView.Adapter<UserListRecyclerViewAdapter.ViewHolder> {

    private final List<UserModel> mValues;
    private Context context;

    public UserListRecyclerViewAdapter(List<UserModel> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getPhone() +"\n" +mValues.get(position).getDob());

        holder.theam_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final UserModel user = mValues.get(position);
//                userRepo.delete(user);
                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, AddEditUserActivity.class);
                intent.putExtra(Constant.EXTRA_ID, user.getId());
                intent.putExtra(Constant.EXTRA_NAME, user.getName());
                intent.putExtra(Constant.EXTRA_DOB, user.getDob());
                intent.putExtra(Constant.EXTRA_PHONE, user.getPhone());
//                context.startActivityForResult(intent, EDIT_USER_REQUEST_CODE);
//                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView theam_image;
        public final TextView mIdView;
        public final TextView mContentView;
        public UserModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            theam_image = view.findViewById(R.id.theam_image);
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

     /*   @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }*/
    }
}