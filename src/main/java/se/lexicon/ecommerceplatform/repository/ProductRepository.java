package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //SELECT p.* FROM products p
    //           JOIN categories c ON p.category_id = c.id
    //           WHERE c.name = '?';
    List<Product> findByCategory_NameIgnoreCase(String categoryName);

    //SELECT * FROM products WHERE price BETWEEN ? AND ?;
    List<Product> findByPriceBetween(BigDecimal priceAfter, BigDecimal priceBefore);

    //Optional Tasks
    //SELECT * FROM products WHERE name = '%?%';
    List<Product> findByNameContainingIgnoreCase(String name);

    //SELECT * FROM products WHERE price < ?;
    List<Product> findByPriceLessThan(BigDecimal priceIsLessThan);

    //SELECT * FROM products ORDER BY price ASC;
    List<Product> findAllByOrderByPriceAsc();

    //SELECT COUNT(*) FROM products WHERE category_id = ?;
    long countByCategory_Id(Long categoryId);

    //SELECT * FROM products WHERE category_id = 2;
    List<Product> findByCategory_Id(Long categoryId);

    boolean existsByCategory_Id(Long categoryId);
}
