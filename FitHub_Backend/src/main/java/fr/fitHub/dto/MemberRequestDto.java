package fr.fitHub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record MemberRequestDto(
        @NotNull
        String firstName,
        @Email
        String email,
        @NotNull
        Integer age
) {}
