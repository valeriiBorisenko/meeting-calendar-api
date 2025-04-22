package se.lexicon.meetingcalendarapi.domain.dto.Meeting;

import lombok.Builder;
import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOView;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserShortDTOView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
public record MeetingDTOView (
        Long id,
        String title,
        LocalDate date,
        LocalTime time,
        LevelDTOView level,
        List<UserShortDTOView> participants,
        String description
) { }
