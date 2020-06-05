package com.example.myownbusiness;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mTitle, mDesc;
    RecyclerView recyclerView;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        this.mImageView = itemView.findViewById(R.id.ImageRow);
        this.mTitle = itemView.findViewById(R.id.txtviewTitle);
        this.mDesc = itemView.findViewById(R.id.txtviewDecription);
    }
}
