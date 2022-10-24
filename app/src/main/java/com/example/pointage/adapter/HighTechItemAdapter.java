package com.example.pointage.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pointage.R;
import com.example.pointage.modeles.HighTechItem;

import java.util.List;


public class HighTechItemAdapter extends BaseAdapter {

    //attribut
    private Context context;
    private List<HighTechItem> highTechitemList;
    private LayoutInflater inflater;

    public HighTechItemAdapter(Context context, List<HighTechItem> highTechitemList) {

        this.context = context;
        this.highTechitemList = highTechitemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return highTechitemList.size();
    }

    @Override
    public HighTechItem getItem(int position) {

        return highTechitemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"SetTextI18n", "ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = inflater.inflate(R.layout.adapter_item,null);


        HighTechItem currentItem = getItem(position);
        final String itemName = currentItem.getNom();
        final String  itemPrenom = currentItem.getPrenom();


        TextView itemPrenomView = view.findViewById(R.id.item_prenom);
        itemPrenomView.setText(itemPrenom);
        TextView itemNameView = view.findViewById(R.id.item_nom);
        itemNameView.setText(itemName);

        return view;
    }

}




