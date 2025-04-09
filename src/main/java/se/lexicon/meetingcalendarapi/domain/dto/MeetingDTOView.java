package se.lexicon.meetingcalendarapi.domain.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record MeetingDTOView (
        Long id,
        String title,
        LocalDate date,
        LocalTime time,
        String level,
        String participantsEmails,
        String description
) { }
