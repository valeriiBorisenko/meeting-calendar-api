package se.lexicon.meetingcalendarapi.domain.dto.User;

import lombok.Builder;

@Builder
public record UserShortDTOView(
        Long id,
        String email
) { }