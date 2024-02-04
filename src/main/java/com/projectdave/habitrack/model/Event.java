package com.projectdave.habitrack.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("Event")
public class Event {
    @Id
    String id;
    LocalDate date;
    String eventInstanceId;

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

    public String getEventInstanceId() {
        return eventInstanceId;
    }

    public void setEventInstanceId(String eventInstanceId) {
        this.eventInstanceId = eventInstanceId;
    }
}
