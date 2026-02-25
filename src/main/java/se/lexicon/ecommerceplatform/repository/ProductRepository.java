package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryName(String name);

    List<Product> findByPriceBetween(BigDecimal priceAfter, BigDecimal priceBefore);

    List<Product> findByNameContaining(String name);

    List<Product> findByPriceBefore(BigDecimal priceBefore);

    List<Product> findAllByOrderByPriceAsc();

    long countByCategory_Id(Long categoryId);

    List<Product> findByCategoryId(Long categoryId);
}
