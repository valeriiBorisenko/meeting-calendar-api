package se.lexicon.meetingcalendarapi.service;

import se.lexicon.meetingcalendarapi.domain.dto.MeetingDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.MeetingDTOView;

import java.util.List;

public interface MeetingService {
    List<MeetingDTOView> getAllMeetings();
    MeetingDTOView addMeeting(MeetingDTOForm form);
    MeetingDTOView updateMeeting(MeetingDTOForm form );
    void deleteMeeting(Long id);
}
