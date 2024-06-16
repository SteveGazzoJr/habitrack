package com.projectdave.habitrack.model.rating;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Map;

@Document("DayRating")
@Data
public class Rating {
    @Id
    String id;
    LocalDate date;
    String userId;
    Map<String, int> ratings;
}
