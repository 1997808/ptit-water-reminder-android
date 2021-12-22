package com.example.ptit_water_reminder.helper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ptit_water_reminder.R;
import com.example.ptit_water_reminder.models.Notification;
import com.example.ptit_water_reminder.models.WaterLog;

import java.util.List;

public class CustomNotificationListAdapter extends BaseAdapter {
    private List<Notification> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomNotificationListAdapter(Context aContext, List<Notification> listData) {
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
            convertView = layoutInflater.inflate(R.layout.list_notification_layout, null);
            holder = new ViewHolder();
            holder.item = convertView.findViewById(R.id.notificationItem);
//            holder.amount = convertView.findViewById(R.id.amountWaterLog);
            holder.date = convertView.findViewById(R.id.notificationTime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Notification notification = this.listData.get(position);
//        holder.item.setBackgroundColor(Color.parseColor("#DAFBFF"));
//        holder.amount.setText(notification.getAmount() + " ml");
        holder.date.setText(notification.getTime());
        return convertView;
    }

    static class ViewHolder {
        View item;
//        TextView amount;
        TextView date;
    }
}
