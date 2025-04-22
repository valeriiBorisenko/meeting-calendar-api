package se.lexicon.meetingcalendarapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.meetingcalendarapi.domain.entity.Level;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    boolean existsByName(String levelName);
    Optional<Level> findByName(String levelName);
    void deleteById(Long levelId);
}
