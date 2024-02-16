package com.projectdave.habitrack.service;

import com.projectdave.habitrack.exception.InvalidParameterException;
import com.projectdave.habitrack.exception.NotFoundException;
import com.projectdave.habitrack.model.EventInstance;
import com.projectdave.habitrack.model.EventModel;
import com.projectdave.habitrack.repository.EventModelRepository;
import com.projectdave.habitrack.repository.EventInstanceRepository;
import com.projectdave.habitrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static io.micrometer.common.util.StringUtils.isBlank;


@Component
public class EventService {

    @Autowired
    EventModelRepository eventModelRepository;
    @Autowired
    EventInstanceRepository eventInstanceRepository;
    @Autowired
    UserRepository userRepository;

    public String saveEventInstance(EventInstance eventInstance){
        if (isBlank(eventInstance.getId())) eventInstance.setId(UUID.randomUUID().toString());
        try {
            eventInstanceRepository.save(eventInstance);
            return eventInstance.getId();
        } catch (Exception e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    public EventInstance getEventInstance(String eventId) {
        return eventInstanceRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event instance not found"));
    }

    public String saveEventModel(EventModel eventModel){
        if (isBlank(eventModel.getId())) eventModel.setId(UUID.randomUUID().toString());
        try {
            eventModelRepository.save(eventModel);
            return eventModel.getId();
        } catch (Exception e) {
            return null;
        }
    }

    public EventModel getEventModel(String eventInstanceId) {
        return eventModelRepository.findById(eventInstanceId).orElseThrow(() -> new NotFoundException("Event model not found"));
    }

    public List<EventModel> getEventModelsByTitle(String title) {
        return eventModelRepository.findByTitle(title);
    }
}
