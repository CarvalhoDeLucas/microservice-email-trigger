package com.carvalho.eventsmicroservice.dtos;

public record EventRequestDTO(int maxParticioants, int registeredParticipants, String date, String title, String description) {
}
