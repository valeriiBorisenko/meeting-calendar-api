package se.lexicon.meetingcalendarapi.converter.Level;

import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.Level;

public interface LevelConverter {
    Level toEntity(LevelDTOForm form);
    LevelDTOView toView(Level level);
}
