package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    //SELECT * FROM categories WHERE name = '?';
    List<Category> findByNameIgnoreCase(String name);

    //SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM categories WHERE name = '?';
    boolean existsByName(String name);

    //SELECT * FROM categories WHERE name LIKE '%?%';
    List<Category> findByNameContaining(String name);

}
