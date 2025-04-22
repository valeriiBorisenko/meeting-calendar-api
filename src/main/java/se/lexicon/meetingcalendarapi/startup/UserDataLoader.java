package se.lexicon.meetingcalendarapi.startup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.meetingcalendarapi.domain.entity.User;
import se.lexicon.meetingcalendarapi.repository.UserRepository;

@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserDataLoader(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        createLevelIfNotExist("test@gmail.com");
        createLevelIfNotExist("manager@gmail.com");
        createLevelIfNotExist("karlson@gmail.com");
    }

    protected void createLevelIfNotExist(String email) {
        if (!userRepository.existsByEmail(email)) {
            userRepository.save(User.builder().email(email).build());
        }
    }
}
