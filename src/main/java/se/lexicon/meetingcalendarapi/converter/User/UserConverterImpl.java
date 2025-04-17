package se.lexicon.meetingcalendarapi.converter.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import se.lexicon.meetingcalendarapi.converter.Meeting.MeetingConverter;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.User;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserConverterImpl implements UserConverter {

    private final MeetingConverter meetingConverter;

    @Override
    public User toEntity(UserDTOForm form) {
        return User.builder()
                .email(form.email().toLowerCase())
                .build();
    }

    @Override
    public UserDTOView toView(User user) {
        return UserDTOView.builder()
                .email(user.getEmail())
                .email(user.getEmail())
                .meetings(user.getMeetings().stream()
                        .map(meetingConverter::toView).collect(Collectors.toList()))
                .build();
    }
}
