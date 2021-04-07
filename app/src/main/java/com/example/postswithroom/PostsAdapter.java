package com.example.postswithroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
        private ArrayList<PostModel>postModels = new ArrayList<>();

    public void setPostModels(ArrayList<PostModel> postModels) {
        this.postModels = postModels;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_items,parent,false);
        return new PostsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        PostModel postModel =  postModels.get(position);
        if(postModel!=null){
            holder.title.setText(postModel.getTitle());
            holder.body.setText(postModel.getBody());
            holder.date.setText(postModel.getDate());


        }
    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder{
            TextView title,body,date;


        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            title = itemView.findViewById(R.id.gettitle);
            body = itemView.findViewById(R.id.getbody);
        }
    }


}
