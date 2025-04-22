package se.lexicon.meetingcalendarapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOView;
import se.lexicon.meetingcalendarapi.service.LevelService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/api/v1/levels")
@RestController
@Validated

public class LevelController {
    private final LevelService levelService;

    @Autowired
    public LevelController(final LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping()
    public ResponseEntity<List<LevelDTOView>> doGetAllLevels() {
        return ResponseEntity.ok(levelService.getAllLevels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LevelDTOView> doFindLevelById(
            @NotBlank(message = "Level id is required")
            @PathVariable ("id")
            Long id
    ) {
        Optional<LevelDTOView> responseBody = levelService.findLevelById(id);

        return responseBody.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> doGetLevelByIdExist(
            @NotBlank(message = "Level name is required")
            @PathVariable ("id")
            Long id
    ) {
        boolean exists = levelService.existLevelById(id);
        return ResponseEntity.ok(exists);
    }

    @PostMapping
    public ResponseEntity<LevelDTOView> doCreateLevel(@Valid @RequestBody LevelDTOForm form) {
        LevelDTOView responseBody = levelService.createLevel(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> doDeleteLevelById(@PathVariable("id") Long id) {
        levelService.deleteLevel(id);
        return ResponseEntity.noContent().build();
    }
}
