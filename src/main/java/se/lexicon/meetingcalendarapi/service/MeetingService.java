package se.lexicon.meetingcalendarapi.service;

import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOView;

import java.util.List;

public interface MeetingService {
    List<MeetingDTOView> getAllMeetings();
    MeetingDTOView addMeeting(MeetingDTOForm form);
    MeetingDTOView updateMeeting(MeetingDTOForm form );
    void deleteMeeting(Long id);
}
