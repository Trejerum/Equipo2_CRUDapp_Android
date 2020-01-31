package com.example.equipo2_crudapp_android.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.equipo2_crudapp_android.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import equipo2_crudapp_classes.classes.Comment;

/**
 * Adapter for the comments recyclerView.
 */
public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentsRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Comment> comments;

    private Context context;

    /**
     * Constructor for the adapter.
     * @param context The context of the view.
     * @param comments The comments.
     */
    public CommentsRecyclerViewAdapter(Context context, ArrayList<Comment> comments) {
        this.comments = comments;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item_comments, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewCommentUser.setText(comments.get(position).getUser().getLogin());
        holder.textViewCommentText.setText(comments.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    /**
     * view holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewCommentUser;
        TextView textViewCommentText;
        RelativeLayout layoutComments;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCommentText = itemView.findViewById(R.id.textViewCommentText);
            textViewCommentUser = itemView.findViewById(R.id.textViewCommentUser);
        }
    }
}
