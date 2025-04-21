package se.lexicon.meetingcalendarapi.service;

import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOView;

import java.util.List;

public interface UserService {
    List<UserDTOView> getAllUsers();
    UserDTOView findUserByEmail(String email);
    boolean existsUserById(Long id);
    UserDTOView createUser(UserDTOForm form);
    void deleteUserById(Long id);
}
