package se.lexicon.meetingcalendarapi.converter;

import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.Meeting;

public interface MeetingConverter {
    Meeting toEntity(MeetingDTOForm form);
    MeetingDTOView toView(Meeting meeting);
}
