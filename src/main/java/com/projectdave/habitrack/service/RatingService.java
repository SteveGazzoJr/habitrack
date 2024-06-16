package com.projectdave.habitrack.service;

import com.projectdave.habitrack.exception.InvalidParameterException;
import com.projectdave.habitrack.exception.NotFoundException;
import com.projectdave.habitrack.model.rating.Rating;
import com.projectdave.habitrack.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RatingService {

    @Autowired
    private final RatingRepository ratingRepository;

    public Rating save(Rating rating) {
        validateUser(rating);
        return ratingRepository.save(rating);
    }

    private void validateUser(Rating rating) {
        if (isNullOrEmpty(rating.getId())) {
            rating.setId(UUID.randomUUID().toString());
        }

        if(isNullOrEmpty(rating.getUserId())) {
            throw new InvalidParameterException("Rating must have a userId");
        }

        if(rating.getDate() == null) {
            throw new InvalidParameterException("Rating must have a date");
        }
    }

    public Rating getById(String id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rating not found for id %s".formatted(id)));
    }

    public Rating getByDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return ratingRepository.findByDate(parsedDate)
                    .orElseThrow(() -> new NotFoundException("Rating not found for date %s".formatted(date)));
        } catch (Exception ex) {
            throw new InvalidParameterException("Unable to parse date from string %s".formatted(date));
        }

    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.isBlank();
    }
}
