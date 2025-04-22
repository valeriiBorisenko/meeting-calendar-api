package se.lexicon.meetingcalendarapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Meeting.MeetingDTOView;
import se.lexicon.meetingcalendarapi.service.MeetingService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/api/v2/meetings")
@RestController
@Validated

public class MeetingController {
    private final MeetingService meetingService;

    @Autowired
    public MeetingController(final MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping()
    public ResponseEntity<List<MeetingDTOView>> doGetAllMeetings() {
        return ResponseEntity.ok(meetingService.getAllMeetings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingDTOView> doGetMeetingById(@PathVariable @Valid Long id) {
        Optional<MeetingDTOView> responseBody = meetingService.findMeetingById(id);

        return responseBody.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<MeetingDTOView> doCreateMeeting(@Valid @RequestBody MeetingDTOForm form) {
        MeetingDTOView responseBody = meetingService.addMeeting(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PutMapping()
    public ResponseEntity<Void> doUpdateMeeting(@Valid @RequestBody MeetingDTOForm form) {
        meetingService.updateMeeting(form);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> doDeleteMeeting(@PathVariable("id") Long id) {
        meetingService.deleteMeeting(id);
        return ResponseEntity.noContent().build();
    }
}
