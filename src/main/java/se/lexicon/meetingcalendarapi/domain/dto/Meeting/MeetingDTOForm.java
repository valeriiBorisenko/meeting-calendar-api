package se.lexicon.meetingcalendarapi.domain.dto.Meeting;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
public record MeetingDTOForm (
        Long id,

        @NotBlank(message = "Meeting title is required")
        String title,

        @NotNull(message = "Meeting date is required")
        @FutureOrPresent(message = "date must be today or in the future")
        LocalDate date,

        @NotNull(message = "Meeting time is required")
        LocalTime time,

        @NotBlank(message = "Meeting level is required")
        String level,

        List<@Email(message = "Invalid email format") String> participants,

        String description
) { }
