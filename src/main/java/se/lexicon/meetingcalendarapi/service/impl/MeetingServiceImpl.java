package se.lexicon.meetingcalendarapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.meetingcalendarapi.converter.Meeting.MeetingConverter;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.Level;
import se.lexicon.meetingcalendarapi.domain.entity.Meeting;
import se.lexicon.meetingcalendarapi.domain.entity.User;
import se.lexicon.meetingcalendarapi.exception.DataNotFoundException;
import se.lexicon.meetingcalendarapi.repository.LevelRepository;
import se.lexicon.meetingcalendarapi.repository.MeetingRepository;
import se.lexicon.meetingcalendarapi.repository.UserRepository;
import se.lexicon.meetingcalendarapi.service.MeetingService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingConverter meetingConverter;
    private final LevelRepository levelRepository;
    private final UserRepository userRepository;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, MeetingConverter meetingConverter, LevelRepository levelRepository, UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.meetingConverter = meetingConverter;
        this.levelRepository = levelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<MeetingDTOView> getAllMeetings() {
        return meetingRepository.findAll().stream()
                .map(meetingConverter::toView).collect(Collectors.toList());
    }

    @Override
    public Optional<MeetingDTOView> findMeetingById(Long id) {
        return meetingRepository.findById(id).map(meetingConverter::toView);
    }

    @Override
    public MeetingDTOView addMeeting(MeetingDTOForm form) {
        if (form == null) throw new IllegalArgumentException("Meeting form cannot be null");

        Meeting createdMeeting = meetingConverter.toEntity(form);
        createdMeeting = meetingRepository.save(createdMeeting);

        return meetingConverter.toView(createdMeeting);
    }

    @Override
    public boolean updateMeeting(MeetingDTOForm form) {
        if (form == null) throw new IllegalArgumentException("Meeting form cannot be null");
        Meeting meeting = meetingRepository.findById(form.id()).orElseThrow(() -> new DataNotFoundException("Meeting not found"));

        meeting.setTitle(form.title());
        meeting.setDate(form.date());
        meeting.setTime(form.time());

        Level level = levelRepository.findByName(form.level().toLowerCase())
                .orElseThrow(() -> new DataNotFoundException("Level not found"));

        Set<User> participants = form.participants() != null ?
                form.participants().stream()
                        .map(email -> userRepository.findByEmail(email)
                                .orElseThrow(() -> new DataNotFoundException("User not found : " + email)))
                        .collect(Collectors.toSet())
                : new HashSet<>();

        meeting.setLevel(level);
        meeting.setParticipants(participants);
        meeting.setDescription(form.description());

        Meeting updatedMeeting = meetingRepository.save(meeting);

        return updatedMeeting != null;
    }

    @Override
    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }
}
