package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.UserProfile;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    List<UserProfile> findByNickname(String nickname);

    List<UserProfile> findByPhoneNumberContains(String phoneNumber);

    List<UserProfile> findByBioIsNotNull(String bio);

    List<UserProfile> findByNicknameStartingWith(String nickname);

    boolean existsByPhoneNumberStartsWith(String phoneNumber);
}
