package com.projectdave.habitrack.model.event;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document("Event")
@Data
public class EventInstance {
    @Id
    String id;
    LocalDate date;
    String eventModelId;
    String userId;
    List<String> tags;
}
