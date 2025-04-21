package se.lexicon.meetingcalendarapi.domain.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserDTOForm(
        Long id,
        @NotNull(message = "Users email is required")
        @Email(message = "Invalid email format")
        String email
) { }
