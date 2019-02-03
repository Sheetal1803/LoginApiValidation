package com.softcelltest.test.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softcelltest.test.R;
import com.softcelltest.test.model.User;

import java.util.ArrayList;
import java.util.Random;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private ArrayList<User> mData;
    private LayoutInflater mInflater;
    private Context context;
    private long max = 100000;
    private long min = 10000;

    // data is passed into the constructor
    public UsersAdapter(Context context, ArrayList<User> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_users, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User model = mData.get(position);
        holder.textName.setText(model.getFirstName()+ " " +model.getLastName());
        Resources res = context.getResources();
        String text = String.format(res.getString(R.string.amount), res.getString(R.string.Rs), new Random().nextInt((int) ((max - min) + 1)) + min);
        holder.textAmount.setText(text);
        Glide.with(context).load(model.getAvatar()).centerCrop().into(holder.imageView);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textName,textAmount;
        ImageView imageView;
        ViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textAmount = itemView.findViewById(R.id.textAmount);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            if (mClickListener != null)
//                mClickListener.clickOnItem(0, getAdapterPosition(), mData.get(getAdapterPosition()));
        }
    }

}
