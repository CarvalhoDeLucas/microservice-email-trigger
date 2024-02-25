package com.carvalho.eventsmicroservice.dtos;

public record EmailRequestDTO(String to, String subject, String body) {
}
