package com.example.ptit_water_reminder.helper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import com.example.ptit_water_reminder.R;
import com.example.ptit_water_reminder.models.Cup;
import com.example.ptit_water_reminder.models.WaterLog;

import java.util.List;

public class CustomCupListAdapter extends BaseAdapter {
    private List<Cup> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomCupListAdapter(Context aContext, List<Cup> listData) {
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
            convertView = layoutInflater.inflate(R.layout.list_cup_layout, null);
            holder = new ViewHolder();
            holder.item = convertView.findViewById(R.id.cupItem);
            holder.amount = convertView.findViewById(R.id.cupAmount);
            holder.image = convertView.findViewById(R.id.cupImageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Cup log = this.listData.get(position);
        holder.amount.setText("" + log.getCupAmount());
        int imageId = this.getMipmapResIdByName("cup_default");
//        if (log.getAmount() >= 0) {
//            int imageId = this.getMipmapResIdByName(log.getAmount());
//        }
        if (log.getCupAmount() > 500) {
            holder.item.setBackgroundColor(453254);
        } else if (log.getCupAmount() > 300) {
            holder.item.setBackgroundColor(234234);
        } else {
            holder.item.setBackgroundColor(111111);
        }
        holder.image.setImageResource(imageId);
        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        View item;
        ImageView image;
        TextView amount;
    }
}
