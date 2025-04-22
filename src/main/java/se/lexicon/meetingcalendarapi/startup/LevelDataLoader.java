package se.lexicon.meetingcalendarapi.startup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.meetingcalendarapi.domain.entity.Level;
import se.lexicon.meetingcalendarapi.repository.LevelRepository;

@Component
public class LevelDataLoader implements CommandLineRunner {

    private final LevelRepository levelRepository;

    public LevelDataLoader(final LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public void run(String... args) {
        createLevelIfNotExist("team");
        createLevelIfNotExist("department");
        createLevelIfNotExist("individual");
        createLevelIfNotExist("managers");
        createLevelIfNotExist("everyone");
    }

    protected void createLevelIfNotExist(String name) {
        if (!levelRepository.existsByName(name)) {
            levelRepository.save(Level.builder().name(name).build());
        }
    }
}
