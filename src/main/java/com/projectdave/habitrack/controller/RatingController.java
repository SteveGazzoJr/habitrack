package com.projectdave.habitrack.controller;

import com.projectdave.habitrack.model.rating.Rating;
import com.projectdave.habitrack.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RatingController {

    @Autowired
    private final RatingService ratingService;

    @PostMapping("/rating")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Rating> postRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(ratingService.save(rating));
    }

    @GetMapping("/rating/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Rating> getRatingById(@PathVariable String id) {
        return ResponseEntity.ok(ratingService.getById(id));
    }

    @GetMapping("/rating")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Rating> getRatingByDate(@RequestParam String date) {
        return ResponseEntity.ok(ratingService.getByDate(date));
    }
}
