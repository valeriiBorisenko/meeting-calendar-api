package se.lexicon.meetingcalendarapi.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
@Builder

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String title;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    @Column(nullable = false)
    private LocalTime time;

    @NotNull
    @Column(nullable = false)
    private String level;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NotNull
    @ElementCollection
    @Column(nullable = false)
    private Set<String> participantsEmails;

    @Builder.Default
    private String description = "";

    public Meeting(@NotNull String title, @NotNull LocalDate date, @NotNull LocalTime time, @NotNull String level, @NotNull Set<String> participantsEmails) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.level = level;
        this.participantsEmails = participantsEmails;
        this.description = "";
    }
}
