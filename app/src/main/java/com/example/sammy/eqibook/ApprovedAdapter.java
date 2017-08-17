package com.example.sammy.eqibook;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ApprovedAdapter  extends ArrayAdapter<Approved>{
    Activity activity;
    public ApprovedAdapter(Context context, List<Approved> list, Activity activity){
        super(context,R.layout.item_requested,list);
        this.activity=activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_approved, null);
            holder = new ViewHolder();
            holder.item = (TextView)convertView.findViewById(R.id.textView);
            holder.status = (TextView)convertView.findViewById(R.id.textView2);
            holder.date = (TextView)convertView.findViewById(R.id.textView5);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
        Approved item=getItem(position);
        holder.item.setText(item.getItemName());
        holder.status.setText(item.getStatus());
        holder.date.setText(item.getDate());
        return convertView;
    }

    static class ViewHolder{
        TextView item;
        TextView status;
        TextView date;
    }
}
