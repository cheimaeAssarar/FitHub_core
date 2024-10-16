package fr.fitHub.dto;

public record CourseResponseDto (
        Long id,
        String nom,
        String coach,
        String schedule
){}
