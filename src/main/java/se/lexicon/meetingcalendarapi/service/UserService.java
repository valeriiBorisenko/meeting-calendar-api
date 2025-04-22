package se.lexicon.meetingcalendarapi.service;

import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOView;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTOView> getAllUsers();
    Optional<UserDTOView> findUserByEmail(String email);
    boolean existsUserByEmail(String email);
    UserDTOView createUser(UserDTOForm form);
    void deleteUserById(Long id);
}
