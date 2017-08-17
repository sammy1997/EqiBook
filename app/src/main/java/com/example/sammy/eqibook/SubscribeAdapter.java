package com.example.sammy.eqibook;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sammy on 16/8/17.
 */

public class SubscribeAdapter extends ArrayAdapter<Subscribe> {
    Activity activity;
    public SubscribeAdapter(Context context, List<Subscribe> list, Activity activity){
        super(context,R.layout.item_subscribe,list);
        this.activity=activity;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            LayoutInflater inflater= (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_subscribe,null,false);
            holder=new ViewHolder();
            holder.paperName=(TextView)convertView.findViewById(R.id.paper_name);
            holder.subscribe=(Button)convertView.findViewById(R.id.subscribe);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
        Subscribe item=getItem(position);
        holder.paperName.setText(SHARED_CONSTANTS.paperNames[position]);
        holder.subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"You voted for " + SHARED_CONSTANTS.paperNames[position],
                                    Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    static class ViewHolder{
        TextView paperName;
        Button subscribe;
    }
}
