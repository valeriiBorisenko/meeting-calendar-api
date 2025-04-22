package se.lexicon.meetingcalendarapi.converter.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOView;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public User toEntity(UserDTOForm form) {
        return User.builder()
                .email(form.email().toLowerCase())
                .build();
    }

    @Override
    public UserDTOView toView(User user) {

        Set<MeetingDTOView> meetings = user.getMeetings() != null ?
                user.getMeetings().stream()
                        .map(meeting -> MeetingDTOView.builder()
                                .id(meeting.getId())
                                .title(meeting.getTitle())
                                .date(meeting.getDate())
                                .build())
                        .collect(Collectors.toSet())
                : Collections.emptySet();

        return UserDTOView.builder()
                .id(user.getId())
                .email(user.getEmail())
                .meetings(new ArrayList<>(meetings))
                .build();
    }
}
