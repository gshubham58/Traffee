package com.yashbohara.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class police_customAdapter extends BaseAdapter{
    TextView finetype,amount;
    int fine;
    LayoutInflater layoutInflater;
    ArrayList<Integer> v;
    ArrayList<String> s;
    public police_customAdapter(Context context, ArrayList<String> item, ArrayList<Integer> value) {
        s=item;
        v=value;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return v.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=layoutInflater.inflate(R.layout.police_row,parent,false);
        finetype=(TextView)view.findViewById(R.id.police_finetype);
        amount=(TextView)view.findViewById(R.id.police_amounttype);
        String singlelist=s.get(position);
        int fine= v.get(position);
        finetype.setText(singlelist);
        amount.setText(fine+"");

        return view;
    }

}
