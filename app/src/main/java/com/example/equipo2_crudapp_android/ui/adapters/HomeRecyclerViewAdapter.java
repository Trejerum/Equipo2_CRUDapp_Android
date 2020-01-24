package com.example.equipo2_crudapp_android.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.equipo2_crudapp_android.R;

import java.util.ArrayList;

/**
 * Adapter for the shops recyclerView
 * @author Diego Corral
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> offerNames = new ArrayList<>();
    private ArrayList<String> offerDiscountedPrices = new ArrayList<>();
    private ArrayList<byte[]> offerImages = new ArrayList<>();
    private ArrayList<String> offerShops = new ArrayList<>();
    private Context context;

    public HomeRecyclerViewAdapter(Context context, ArrayList<String> shopsNames,
                                   ArrayList<String> offerDiscountedPrices,
                                   ArrayList<byte[]> offerImages,
                                   ArrayList<String> offerShops) {
        this.offerNames = shopsNames;
        this.offerDiscountedPrices = offerDiscountedPrices;
        this.offerImages = offerImages;
        this.offerShops = offerShops;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewOfferName.setText(offerNames.get(position));
        holder.textViewDiscountedPrice.setText(offerDiscountedPrices.get(position) + "€");
        holder.textViewOfferShop.setText(offerShops.get(position));
        // holder.imageViewOffer
    }

    @Override
    public int getItemCount() {
        return offerNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewOfferName;
        ImageView imageViewOffer;
        TextView textViewDiscountedPrice;
        TextView textViewOfferShop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewOffer = itemView.findViewById(R.id.imageViewOffer);
            textViewOfferName = itemView.findViewById(R.id.textViewOfferName);
            textViewDiscountedPrice = itemView.findViewById(R.id.textViewDiscountedPrice);
            textViewOfferShop = itemView.findViewById(R.id.textViewOfferShop);
        }
    }

}
