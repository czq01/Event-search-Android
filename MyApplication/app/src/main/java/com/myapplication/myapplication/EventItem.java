package com.myapplication.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

public class EventItem {
    public String Id;
    public String VenueId;
    public String Date;
    public String Icon;
    public String Event;
    public String Genre;
    public String Venue;
    public EventItem(JSONObject obj) throws JSONException {
        Id = obj.getString("Id");
        VenueId = obj.getString("VenueId");
        Date = obj.getString("Date");
        Icon = obj.getString("Icon");
        Event = obj.getString("Venue");
        Genre = obj.getString("Genre");
        Venue = obj.getString("Venue");
    }

}
