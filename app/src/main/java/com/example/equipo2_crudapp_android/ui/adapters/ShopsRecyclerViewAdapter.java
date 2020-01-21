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

/**
 * Adapter for the shops recyclerView
 * @author Diego Corral
 */
public class ShopsRecyclerViewAdapter extends RecyclerView.Adapter<ShopsRecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> shopsNames = new ArrayList<>();
    private Context context;

    public ShopsRecyclerViewAdapter(Context context, ArrayList<String> shopsNames) {
        this.shopsNames = shopsNames;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item_shop, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewShopName.setText(shopsNames.get(position));
    }

    @Override
    public int getItemCount() {
        return shopsNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewShopName;
        ImageView imageViewShop;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewShop = itemView.findViewById(R.id.imageViewShop);
            textViewShopName = itemView.findViewById(R.id.textViewShopName);
        }
    }
}
