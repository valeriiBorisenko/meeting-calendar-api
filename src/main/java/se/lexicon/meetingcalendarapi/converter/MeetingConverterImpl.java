package se.lexicon.meetingcalendarapi.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import se.lexicon.meetingcalendarapi.domain.dto.MeetingDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.MeetingDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.Meeting;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MeetingConverterImpl implements MeetingConverter {

    @Override
    public Meeting toEntity(MeetingDTOForm form) {
        Set<String> participantsEmails = Arrays.stream(form.participantsEmails().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());

        return Meeting.builder()
                .title(form.title())
                .date(form.date())
                .time(form.time())
                .level(form.level())
                .participantsEmails(participantsEmails)
                .description(form.description())
                .build();
    }

    @Override
    public MeetingDTOView toView(Meeting meeting) {

        return MeetingDTOView.builder()
                .id(meeting.getId())
                .date(meeting.getDate())
                .time(meeting.getTime())
                .level(meeting.getLevel())
                .participantsEmails(String.join(",", meeting.getParticipantsEmails()))
                .description(meeting.getDescription())
                .build();
    }
}
