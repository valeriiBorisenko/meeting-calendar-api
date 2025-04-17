package se.lexicon.meetingcalendarapi.converter.Level;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.Level;

@RequiredArgsConstructor
@Component
public class LevelConverterImpl implements LevelConverter {
    @Override
    public Level toEntity(LevelDTOForm form) {
        return Level.builder()
                .name(form.name().toLowerCase())
                .build();
    }

    @Override
    public LevelDTOView toView(Level level) {
        return LevelDTOView.builder()
                .id(level.getId())
                .name(level.getName())
                .build();
    }
}
