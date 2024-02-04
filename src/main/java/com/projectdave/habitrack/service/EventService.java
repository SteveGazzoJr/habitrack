package com.projectdave.habitrack.service;

import com.projectdave.habitrack.model.Event;
import com.projectdave.habitrack.model.EventInstance;
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

    public String saveEvent(Event event){
        if (isBlank(event.getId())) event.setId(UUID.randomUUID().toString());
        try {
            eventRepository.save(event);
            return event.getId();
        } catch (Exception e) {
            return null;
        }
    }

    public Event getEvent(String eventId) {
        //TODO - custom exception here to allow us to return better statuses
        return eventRepository.findById(eventId).orElseThrow();
    }

    public String saveEventInstance(EventInstance eventInstance){
        if (isBlank(eventInstance.getId())) eventInstance.setId(UUID.randomUUID().toString());
        try {
            eventInstanceRepository.save(eventInstance);
            return eventInstance.getId();
        } catch (Exception e) {
            return null;
        }
    }

    public EventInstance getEventInstance(String eventInstanceId) {
        //TODO - custom exception here to allow us to return better statuses
        return eventInstanceRepository.findById(eventInstanceId).orElseThrow();
    }
}
