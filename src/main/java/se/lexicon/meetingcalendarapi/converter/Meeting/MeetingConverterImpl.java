package se.lexicon.meetingcalendarapi.converter.Meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.meetingcalendarapi.converter.Level.LevelConverter;
import se.lexicon.meetingcalendarapi.converter.User.UserConverter;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOView;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserShortDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.Level;
import se.lexicon.meetingcalendarapi.domain.entity.Meeting;
import se.lexicon.meetingcalendarapi.domain.entity.User;
import se.lexicon.meetingcalendarapi.exception.DataNotFoundException;
import se.lexicon.meetingcalendarapi.repository.LevelRepository;
import se.lexicon.meetingcalendarapi.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MeetingConverterImpl implements MeetingConverter {

    private final LevelRepository levelRepository;
    private final UserRepository userRepository;
    private final LevelConverter levelConverter;
    private final UserConverter userConverter;

    @Override
    public Meeting toEntity(MeetingDTOForm form) {

        Level findLevel = levelRepository.findByName(form.level())
                .orElseThrow(() -> new DataNotFoundException("Level not found"));

        Set<User> participants = form.participants() != null ?
                form.participants().stream()
                        .map(email -> userRepository.findByEmail(email)
                                .orElseThrow(() -> new DataNotFoundException("User not found : " + email)))
                        .collect(Collectors.toSet())
                : new HashSet<>();

        return Meeting.builder()
                .title(form.title())
                .date(form.date())
                .time(form.time())
                .level(findLevel)
                .participants(participants)
                .description(form.description())
                .build();
    }

    @Override
    public MeetingDTOView toView(Meeting meeting) {

        return MeetingDTOView.builder()
                .id(meeting.getId())
                .title(meeting.getTitle())
                .date(meeting.getDate())
                .time(meeting.getTime())
                .level(levelConverter.toView(meeting.getLevel()))
                .participants(meeting.getParticipants().stream().map(user ->
                                UserShortDTOView.builder()
                                        .id(user.getId())
                                        .email(user.getEmail())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .description(meeting.getDescription())
                .build();
    }
}
