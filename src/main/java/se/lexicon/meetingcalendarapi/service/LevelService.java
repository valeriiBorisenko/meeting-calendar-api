package se.lexicon.meetingcalendarapi.service;

import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOView;

import java.util.List;
import java.util.Optional;

public interface LevelService {
    List<LevelDTOView> getAllLevels();
    Optional<LevelDTOView> findLevelById(Long id);
    boolean existLevelById(Long id);
    LevelDTOView createLevel(LevelDTOForm form);
    void deleteLevel(Long id);
}
