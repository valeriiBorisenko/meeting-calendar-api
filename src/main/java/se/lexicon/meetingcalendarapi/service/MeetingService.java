package se.lexicon.meetingcalendarapi.service;

import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOView;

import java.util.List;
import java.util.Optional;

public interface MeetingService {
    List<MeetingDTOView> getAllMeetings();
    Optional<MeetingDTOView> findMeetingById(Long id);
    MeetingDTOView addMeeting(MeetingDTOForm form);
    boolean updateMeeting(MeetingDTOForm form );
    void deleteMeeting(Long id);
}
