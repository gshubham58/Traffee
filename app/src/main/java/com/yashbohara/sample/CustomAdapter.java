package com.yashbohara.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter{
TextView finetype,amount;
    public CustomAdapter(Context context, ArrayList<String> item) {
        super(context, R.layout.row,item);
        notifyDataSetChanged();
        //Log.e("2",""+item);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(R.layout.row,parent,false);
        finetype=(TextView)view.findViewById(R.id.finety);
        amount=(TextView)view.findViewById(R.id.amountty);
        String singlelist=getItem(position).toString();
        finetype.setText(singlelist);
        amount.setText("100 Rs");

        return view;
    }

}
