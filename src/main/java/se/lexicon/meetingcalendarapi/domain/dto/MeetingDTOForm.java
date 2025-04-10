package se.lexicon.meetingcalendarapi.domain.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record MeetingDTOForm (
        Long id,

        @NotBlank(message = "title is required")
        String title,

        @NotNull(message = "date is required")
        @FutureOrPresent(message = "date must be today or in the future")
        LocalDate date,

        @NotNull(message = "time is required")
        LocalTime time,

        @NotBlank(message = "level is required")
        String level,

        @NotBlank(message = "participants emails is required")
        String participantsEmails,

        String description
) { }
