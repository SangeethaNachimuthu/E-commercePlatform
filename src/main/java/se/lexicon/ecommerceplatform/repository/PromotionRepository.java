package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    //SELECT * FROM promotions WHERE start_date <= ? AND (end_date IS NULL OR end_date >= ?);
    List<Promotion> findByStartDateAfterAndEndDateBefore(LocalDate startDateAfter, LocalDate endDateBefore);

    //SELECT * FROM promotions WHERE code = ?;
    List<Promotion> findByCode(String code);

    //SELECT * FROM promotions WHERE start_date > ?;
    List<Promotion> findByStartDateAfter(LocalDate startDateAfter);

    //SELECT * FROM promotions WHERE end_date < ?;
    List<Promotion> findByEndDateBefore(LocalDate endDateBefore);

    //SELECT * FROM promotions WHERE end_date IS NULL;
    List<Promotion> findByEndDateIsNull(LocalDate endDate);

    //SELECT * FROM promotions WHERE start_date <= CURRENT_DATE AND (end_date >= CURRENT_DATE OR end_date IS NULL);
    List<Promotion> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate startDateIsLessThan, LocalDate endDateIsGreaterThan);
}
