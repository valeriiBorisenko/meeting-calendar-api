package se.lexicon.meetingcalendarapi.domain.dto.Level;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


@Builder
public record LevelDTOForm(
        @NotBlank(message = "Level name is required")
        String name
) { }
