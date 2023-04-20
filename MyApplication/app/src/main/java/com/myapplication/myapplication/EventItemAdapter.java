package com.myapplication.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class EventItemAdapter extends ArrayAdapter<EventItem> {
    public EventItemAdapter(Context context, List<EventItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.result_tab, parent, false);
        }

        EventItem item = getItem(position);
        // find element and set value
        TextView name = convertView.findViewById(R.id.event_name);
        TextView category = convertView.findViewById(R.id.event_cactegory);
        TextView date = convertView.findViewById(R.id.event_date);
        TextView time = convertView.findViewById(R.id.event_time);
        TextView venue = convertView.findViewById(R.id.event_venue);
        ImageView image = convertView.findViewById(R.id.event_image);

        name.setText(item.Event);
        category.setText(item.Genre);
        venue.setText(item.Venue);
        time.setText(item.Time);
        date.setText(String.format("%s/%s/%s", item.Date.split("-")[1],
                item.Date.split("-")[2], item.Date.split("-")[0]));

        Glide.with(getContext()).load(item.Icon).into(image);
        //return
        Log.d("EventItemAdapter", "Position: " + position + ", Event: " + item.Event);
        return convertView;

    }
}
