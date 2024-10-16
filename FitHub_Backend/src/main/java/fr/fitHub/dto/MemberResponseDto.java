package fr.fitHub.dto;

public record MemberResponseDto(
        Long id,
    String firstName,
    String email,
    Integer age
) { }
