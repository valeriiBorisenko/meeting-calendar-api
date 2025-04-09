package se.lexicon.meetingcalendarapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.meetingcalendarapi.domain.entity.Meeting;

import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    boolean existsById(Long meetingId);
    Optional<Meeting> findById(Long meetingId);
    void deleteById(Long meetingId);
}
