package com.example.myownbusiness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Model> models; //This array list creates a list of array wich paramets define in our model.

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,null); //Line to inflate our row.

        return new MyHolder(view); //this  will return the new row.
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.mTitle.setText(models.get(i).getTitle()); //i, is the actual position.
        myHolder.mDesc.setText(models.get(i).getDesc());
        myHolder.mImageView.setImageResource(models.get(i).getImg()); //here we used image resource that we actually have in our resources called Drawable.
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
