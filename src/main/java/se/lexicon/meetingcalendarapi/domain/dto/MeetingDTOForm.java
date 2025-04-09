package se.lexicon.meetingcalendarapi.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record MeetingDTOForm (
        Long id,

        @NotBlank(message = "title is required")
        String title,

        @NotBlank(message = "date is required")
        LocalDate date,

        @NotBlank(message = "time is required")
        LocalTime time,

        @NotBlank(message = "level is required")
        String level,

        @NotBlank(message = "participants emails is required")
        String participantsEmails,

        String description
) { }
