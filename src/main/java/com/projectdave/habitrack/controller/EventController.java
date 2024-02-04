package com.projectdave.habitrack.controller;

import com.projectdave.habitrack.model.Event;
import com.projectdave.habitrack.model.EventInstance;
import com.projectdave.habitrack.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/event")
    public ResponseEntity<String> postEvent(@RequestBody Event event) {
        try {
            String id = eventService.saveEvent(event);
            return ResponseEntity.ok(id);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/event/{id}")
    @ResponseBody
    public ResponseEntity<Event> getEvent(@PathVariable String id) {
        try {
            return ResponseEntity.ok(eventService.getEvent(id));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/event/instance")
    public ResponseEntity<String> postEventInstance(@RequestBody EventInstance eventInstance) {
        try {
            String id = eventService.saveEventInstance(eventInstance);
            return ResponseEntity.ok(id);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/event/instance/{id}")
    @ResponseBody
    public ResponseEntity<EventInstance> getEventInstance(@PathVariable String id) {
        try {
            return ResponseEntity.ok(eventService.getEventInstance(id));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
