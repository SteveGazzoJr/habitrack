package com.projectdave.habitrack.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document("EventModel")
public class EventModel {
    @Id
    String id;
    String title;
    String colorValue;
    List<String> tags;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColorValue() {
        return colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Map<String, Object> getStatsKvps() {
        return statsKvps;
    }

    public void setStatsKvps(Map<String, Object> statsKvps) {
        this.statsKvps = statsKvps;
    }

    Map<String, Object> statsKvps;
}