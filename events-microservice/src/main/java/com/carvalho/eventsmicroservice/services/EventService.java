package com.carvalho.eventsmicroservice.services;

import com.carvalho.eventsmicroservice.domain.Event;
import com.carvalho.eventsmicroservice.domain.Subscription;
import com.carvalho.eventsmicroservice.dtos.EmailRequestDTO;
import com.carvalho.eventsmicroservice.dtos.EventRequestDTO;
import com.carvalho.eventsmicroservice.exceptions.EventFullException;
import com.carvalho.eventsmicroservice.exceptions.EventNotFoundException;
import com.carvalho.eventsmicroservice.repositories.EventRepository;
import com.carvalho.eventsmicroservice.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailServiceClient emailServiceClient;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }

    public Event createEvent(EventRequestDTO eventRequest) {
        Event newEvent = new Event(eventRequest);
        return eventRepository.save(newEvent);
    }

    private Boolean isEventFull(Event event) {
        return event.getRegisteredParticipants() >= event.getMaxParticipants();
    }

    public void registerParticipant(String eventId, String participantEmail) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

        if (isEventFull(event)) {
            throw new EventFullException();
        }

        Subscription subscription = new Subscription(event, participantEmail);
        subscriptionRepository.save(subscription);

        event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);

        EmailRequestDTO emailRequestDTO = new EmailRequestDTO(participantEmail, "E-mail evento", "Registrado no evento");
        emailServiceClient.sendEmail(emailRequestDTO);
    }

}
