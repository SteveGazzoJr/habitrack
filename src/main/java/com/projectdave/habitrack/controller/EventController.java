package com.projectdave.habitrack.controller;

import com.projectdave.habitrack.exception.TokenMismatchException;
import com.projectdave.habitrack.model.event.EventInstance;
import com.projectdave.habitrack.model.event.EventModel;
import com.projectdave.habitrack.service.EventService;
import com.projectdave.habitrack.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EventController {

    @Autowired
    private final EventService eventService;

    @Autowired
    private final JwtUtil jwtUtil;

    @PostMapping("/event")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<EventInstance> postEvent(@RequestBody EventInstance eventInstance, @RequestHeader (name="Authorization") String token) {
        if(verifyUserIdFromHeader(token, eventInstance.getUserId())) {
            return ResponseEntity.ok(eventService.saveEventInstance(eventInstance));
        }
        throw new TokenMismatchException("Token ID doesn't match request ID");
    }

    @GetMapping("/event/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<EventInstance> getEvent(@PathVariable String id) {
        return ResponseEntity.ok(eventService.getEventInstance(id));
    }

    @PostMapping("/event/model")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<EventModel> postEventModel(@RequestBody EventModel eventModel) {
        return ResponseEntity.ok().body(eventService.saveEventModel(eventModel));
    }

    @GetMapping("/event/model/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<EventModel> getEventModel(@PathVariable String id) {
        return ResponseEntity.ok(eventService.getEventModel(id));
    }

    @GetMapping("/event/models")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<EventModel>> getEventModelsByTitle(@RequestParam String title) {
        return ResponseEntity.ok(eventService.getEventModelsByTitle(title));
    }

    private boolean verifyUserIdFromHeader(String header, String expectedId) {
        if(!header.startsWith("Bearer ")) {
            throw new TokenMismatchException("Could not determine auth token from header");
        }
        String parsedId = jwtUtil.getId(jwtUtil.parseJwtClaims(header.substring(header.indexOf(" ") + 1)));
        log.info("userId parsed from header %s".formatted(parsedId));
        log.info("userId from request %s".formatted(expectedId));

        return parsedId.contentEquals(expectedId);
    }
}
