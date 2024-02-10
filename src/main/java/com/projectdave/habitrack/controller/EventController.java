package com.projectdave.habitrack.controller;

import com.projectdave.habitrack.model.EventInstance;
import com.projectdave.habitrack.model.EventModel;
import com.projectdave.habitrack.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/event")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> postEvent(@RequestBody EventInstance eventInstance) {
        return ResponseEntity.ok(eventService.saveEventInstance(eventInstance));
    }

    @GetMapping("/event/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<EventInstance> getEvent(@PathVariable String id) {
        return ResponseEntity.ok(eventService.getEventInstance(id));
    }

    @PostMapping("/event/model")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> postEventModel(@RequestBody EventModel eventModel) {
        return ResponseEntity.ok(eventService.saveEventModel(eventModel));
    }

    @GetMapping("/event/model/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<EventModel> getEventModel(@PathVariable String id) {
        return ResponseEntity.ok(eventService.getEventModel(id));
    }
}
