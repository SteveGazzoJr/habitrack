package com.projectdave.habitrack.service;

import com.projectdave.habitrack.exception.InvalidParameterException;
import com.projectdave.habitrack.exception.NotFoundException;
import com.projectdave.habitrack.model.EventInstance;
import com.projectdave.habitrack.model.EventModel;
import com.projectdave.habitrack.repository.EventInstanceRepository;
import com.projectdave.habitrack.repository.EventRepository;
import com.projectdave.habitrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static io.micrometer.common.util.StringUtils.isBlank;


@Component
public class EventService {

    @Autowired
    EventInstanceRepository eventInstanceRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;

    public String saveEventInstance(EventInstance eventInstance){
        if (isBlank(eventInstance.getId())) eventInstance.setId(UUID.randomUUID().toString());
        try {
            eventRepository.save(eventInstance);
            return eventInstance.getId();
        } catch (Exception e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    public EventInstance getEventInstance(String eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event not found"));
    }

    public String saveEventModel(EventModel eventModel){
        if (isBlank(eventModel.getId())) eventModel.setId(UUID.randomUUID().toString());
        try {
            eventInstanceRepository.save(eventModel);
            return eventModel.getId();
        } catch (Exception e) {
            return null;
        }
    }

    public EventModel getEventModel(String eventInstanceId) {
        return eventInstanceRepository.findById(eventInstanceId).orElseThrow(() -> new NotFoundException("Event instance not found"));
    }
}
