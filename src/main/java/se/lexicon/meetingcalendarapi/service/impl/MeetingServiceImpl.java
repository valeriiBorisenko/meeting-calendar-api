package se.lexicon.meetingcalendarapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.meetingcalendarapi.converter.MeetingConverter;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.Meeting;
import se.lexicon.meetingcalendarapi.repository.MeetingRepository;
import se.lexicon.meetingcalendarapi.service.MeetingService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingConverter meetingConverter;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, MeetingConverter meetingConverter) {
        this.meetingRepository = meetingRepository;
        this.meetingConverter = meetingConverter;
    }

    @Override
    public List<MeetingDTOView> getAllMeetings() {
        return meetingRepository.findAll().stream().map(meetingConverter::toView).collect(Collectors.toList());
    }

    @Override
    public MeetingDTOView addMeeting(MeetingDTOForm form) {
        if (form == null) throw new IllegalArgumentException("meeting cannot be null");

        Meeting createdMeeting = meetingConverter.toEntity(form);
        createdMeeting = meetingRepository.save(createdMeeting);

        return meetingConverter.toView(createdMeeting);
    }

    @Override
    public MeetingDTOView updateMeeting(MeetingDTOForm form) {
        if (form == null) throw new IllegalArgumentException("meeting cannot be null");
        Meeting meeting = meetingRepository.findById(form.id()).orElseThrow(() -> new IllegalArgumentException("Meeting not found"));

        meeting.setTitle(form.title());
        meeting.setDate(form.date());
        meeting.setTime(form.time());
        meeting.setLevel(form.level());

        Set<String> participantsEmails = Arrays.stream(form.participantsEmails().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());

        meeting.setParticipantsEmails(participantsEmails);
        meeting.setDescription(form.description());

        Meeting updatedMeeting = meetingRepository.save(meeting);

        return meetingConverter.toView(updatedMeeting);
    }

    @Override
    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }
}
