package com.projectdave.habitrack.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("Event")
public class EventInstance {
    @Id
    String id;
    LocalDate date;
    String eventModelId;
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEventModelId() {
        return eventModelId;
    }

    public void setEventModelId(String eventModelId) {
        this.eventModelId = eventModelId;
    }
}
