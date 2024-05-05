package com.projectdave.habitrack.controller;

import com.projectdave.habitrack.model.EventInstance;
import com.projectdave.habitrack.model.EventModel;
import com.projectdave.habitrack.model.User;
import com.projectdave.habitrack.service.EventService;
import com.projectdave.habitrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<User> postEvent(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/user/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<User> getEvent(@PathVariable String id) {
        return ResponseEntity.ok(userService.get(id));
    }

}
