package se.lexicon.meetingcalendarapi.service;

import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOView;

import java.util.List;

public interface LevelService {
    List<LevelDTOView> getAllLevels();
    LevelDTOView findLevelByName(LevelDTOForm form);
    boolean existLevelByName(LevelDTOForm form);
    LevelDTOView createLevel(LevelDTOForm form);
    void deleteLevel(Long id);
}
