package com.projectdave.habitrack.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("User")
public class User {
    String id;
    List<EventModel> events;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }
}
