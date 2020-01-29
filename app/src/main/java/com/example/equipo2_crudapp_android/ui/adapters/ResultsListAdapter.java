package com.example.equipo2_crudapp_android.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.equipo2_crudapp_android.R;

import java.util.List;

import equipo2_crudapp_classes.classes.Software;

public class ResultsListAdapter extends ArrayAdapter<Software> {
    public ResultsListAdapter(Context context, List<Software> softwares) {
        super(context, 0, softwares);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Software software = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_item_shop, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.textViewShopName);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageViewShop);

        tvName.setText(software.getName());
        image.setImageResource(R.drawable.ic_home);
        return convertView;
    }
}
