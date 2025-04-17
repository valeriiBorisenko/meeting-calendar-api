package se.lexicon.meetingcalendarapi.converter.User;

import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.User;

public interface UserConverter {
    User toEntity(UserDTOForm form);
    UserDTOView toView(User user);
}
