package com.example.ptit_water_reminder.helper;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ptit_water_reminder.R;
import com.example.ptit_water_reminder.models.WaterLog;

import java.util.List;

public class CustomLogListAdapter extends BaseAdapter {
    private List<WaterLog> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomLogListAdapter(Context aContext, List<WaterLog> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_log_layout, null);
            holder = new ViewHolder();
            holder.item = convertView.findViewById(R.id.waterLogItem);
            holder.amount = convertView.findViewById(R.id.amountWaterLog);
            holder.date = convertView.findViewById(R.id.dateWaterLog);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WaterLog log = this.listData.get(position);
        if (log.getAmount() >= 500) {
            holder.item.setBackgroundColor(Color.parseColor("#DAFBFF"));
        } else if (log.getAmount() >= 300) {
            holder.item.setBackgroundColor(Color.parseColor("#EAFFDA"));
        } else {
            holder.item.setBackgroundColor(Color.parseColor("#FEFFDA"));
        }
        holder.amount.setText(log.getAmount() + " ml");
        holder.date.setText(log.getCreateAt());
        return convertView;
    }

    static class ViewHolder {
        View item;
        TextView amount;
        TextView date;
    }
}
