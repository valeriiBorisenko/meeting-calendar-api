package se.lexicon.meetingcalendarapi.domain.dto.Level;

import lombok.Builder;

@Builder
public record LevelDTOView(
        Long id,
        String name
) { }
