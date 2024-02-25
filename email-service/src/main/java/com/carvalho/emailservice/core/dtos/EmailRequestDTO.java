package com.carvalho.emailservice.core.dtos;

public record EmailRequestDTO(String to, String subject, String body) {
}
