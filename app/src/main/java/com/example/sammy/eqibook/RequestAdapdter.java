package com.example.sammy.eqibook;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sammy on 16/8/17.
 */

public class RequestAdapdter extends ArrayAdapter<Requested> {
    Activity activity;
    public RequestAdapdter(Context context, List<Requested> list, Activity activity){
        super(context,R.layout.item_requested,list);
        this.activity=activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_requested, null);
            holder = new ViewHolder();
            holder.itemName = (TextView)convertView.findViewById(R.id.itemname);
            holder.amount = (TextView)convertView.findViewById(R.id.amount);
            holder.status = (TextView)convertView.findViewById(R.id.textView3);
            holder.Date = (TextView)convertView.findViewById(R.id.textView4);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
        Requested itemReq = getItem(position);
        //Log.d("After","Item Req" + holder.toString());
        holder.itemName.setText(itemReq.getItemName());
        holder.amount.setText(itemReq.getAmount());
        holder.status.setText(itemReq.getStatus());
        holder.Date.setText(itemReq.getDate());
        return convertView;
    }

    static class ViewHolder{
        TextView itemName;
        TextView amount;
        TextView status;
        TextView Date;
    }
}
