package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    List<Promotion> findByStartDateAfterAndEndDateBefore(LocalDate startDateAfter, LocalDate endDateBefore);

    List<Promotion> findByCode(String code);

    List<Promotion> findByStartDateAfter(LocalDate startDateAfter);

    List<Promotion> findByEndDateBefore(LocalDate endDateBefore);

    List<Promotion> findByEndDateIsNull(LocalDate endDate);

    List<Promotion> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate startDateIsLessThan, LocalDate endDateIsGreaterThan);
}
