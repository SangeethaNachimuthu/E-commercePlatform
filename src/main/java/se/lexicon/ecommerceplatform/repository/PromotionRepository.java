package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.ecommerceplatform.entity.Promotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    //SELECT * FROM promotions WHERE start_date <= ? AND (end_date IS NULL OR end_date >= ?);
    List<Promotion> findByStartDateAfterAndEndDateBefore(LocalDate startDateAfter, LocalDate endDateBefore);

    //SELECT * FROM promotions WHERE code = ?;
    Optional<Promotion> findByCodeIgnoreCase(String code);

    //SELECT * FROM promotions WHERE start_date > ?;
    List<Promotion> findByStartDateAfter(LocalDate startDateAfter);

    //SELECT * FROM promotions WHERE end_date < ?;
    List<Promotion> findByEndDateBefore(LocalDate endDateBefore);

    //SELECT * FROM promotions WHERE end_date IS NULL;
    List<Promotion> findByEndDateIsNull();

    //SELECT * FROM promotions WHERE start_date <= CURRENT_DATE AND (end_date >= CURRENT_DATE OR end_date IS NULL);
    List<Promotion> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate startDateIsLessThan, LocalDate endDateIsGreaterThan);

    // Required: active on date
    @Query("""
           select p from Promotion p
           where p.startDate <= :date
             and (p.endDate is null or p.endDate >= :date)
           """)
    List<Promotion> findActiveOn(@Param("date") LocalDate date);
}
