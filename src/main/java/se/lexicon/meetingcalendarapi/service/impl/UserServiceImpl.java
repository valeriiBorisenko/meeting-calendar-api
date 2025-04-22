package se.lexicon.meetingcalendarapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.meetingcalendarapi.converter.User.UserConverter;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.User;
import se.lexicon.meetingcalendarapi.exception.DataDuplicateException;
import se.lexicon.meetingcalendarapi.repository.UserRepository;
import se.lexicon.meetingcalendarapi.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDTOView> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userConverter::toView).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTOView> findUserByEmail(String email) {
        return userRepository.findByEmail(email.toLowerCase())
                .map(userConverter::toView);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDTOView createUser(UserDTOForm form) {
        if (form == null) throw new IllegalArgumentException("User form can't be null");
        if (userRepository.existsByEmail(form.email())) throw new DataDuplicateException("User already exists");

        User newUser = userRepository.save(userConverter.toEntity(form));

        return userConverter.toView(newUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
