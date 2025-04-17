package se.lexicon.meetingcalendarapi.domain.dto.User;

import lombok.Builder;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOView;

import java.util.List;

@Builder
public record UserDTOView(
        Long id,
        String email,
        List<MeetingDTOView> meetings
) { }
