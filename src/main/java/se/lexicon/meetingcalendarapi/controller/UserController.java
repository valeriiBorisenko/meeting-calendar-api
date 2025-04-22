package se.lexicon.meetingcalendarapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.User.UserDTOView;
import se.lexicon.meetingcalendarapi.service.UserService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/api/v2/users")
@RestController
@Validated

public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTOView>> doGetAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTOView> doFindUserByEmail(
            @NotBlank
            @Email(message = "invalid email format")
            @PathVariable("email")
            String email
    ) {
        Optional<UserDTOView> responseBody = userService.findUserByEmail(email);

        return responseBody.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/exist/{email}")
    public ResponseEntity<Boolean> doGetUserByEmailExist(
            @NotBlank
            @Email(message = "invalid email format")
            @PathVariable("email")
            String email
    ) {
        boolean exists = userService.existsUserByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @PostMapping()
    public ResponseEntity<UserDTOView> doCreateUser(@Valid @RequestBody UserDTOForm form) {
        UserDTOView responseBody = userService.createUser(form);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> doDeleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
