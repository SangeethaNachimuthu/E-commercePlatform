package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByNicknameIgnoreCase(String nickname);

    List<UserProfile> findByPhoneNumberContains(String phoneNumber);

    //Optional Task
    List<UserProfile> findByBioIsNotNull();

    List<UserProfile> findByNicknameStartingWithIgnoreCase(String nickname);

    boolean existsByPhoneNumberStartsWith(String phoneNumber);
}
