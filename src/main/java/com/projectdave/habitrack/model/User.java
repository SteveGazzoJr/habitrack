package com.projectdave.habitrack.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("User")
public class User {
    String id;
    List<EventInstance> events;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<EventInstance> getEvents() {
        return events;
    }

    public void setEvents(List<EventInstance> events) {
        this.events = events;
    }
}
