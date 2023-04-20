package com.myapplication.myapplication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class EventItem {
    public String Id;
    public String VenueId;
    public String Date;
    public String Time;
    public String Icon;
    public String Event;
    public String Genre;
    public String Venue;
    public EventItem(JSONObject obj) throws JSONException {
        Id = obj.getString("Id");
        VenueId = obj.getString("VenueId");
        Date = obj.getString("Date");
        Time = obj.getString("Time");
        Icon = obj.getString("Icon");
        Event = obj.getString("Event");
        Genre = obj.getString("Genre");
        Venue = obj.getString("Venue");
    }

}
