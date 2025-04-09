package se.lexicon.meetingcalendarapi.converter;

import se.lexicon.meetingcalendarapi.domain.dto.MeetingDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.MeetingDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.Meeting;

public interface MeetingConverter {
    Meeting toEntity(MeetingDTOForm form);
    MeetingDTOView toView(Meeting meeting);
}
