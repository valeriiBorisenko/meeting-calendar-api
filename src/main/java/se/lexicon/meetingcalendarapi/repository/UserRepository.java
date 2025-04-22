package se.lexicon.meetingcalendarapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.meetingcalendarapi.domain.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail (String email);
    Optional<User> findByEmail(String userEmail);
    void deleteById(Long userId);
}
