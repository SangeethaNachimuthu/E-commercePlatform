package se.lexicon.ecommerceplatform.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.ecommerceplatform.entity.*;
import se.lexicon.ecommerceplatform.repository.*;

import java.math.BigDecimal;
import java.util.List;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;
    private UserProfileRepository userProfileRepository;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public MyCommandLineRunner(CustomerRepository customerRepository,
                               AddressRepository addressRepository,
                               UserProfileRepository userProfileRepository,
                               CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.userProfileRepository = userProfileRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("#################");

        /*Address address = Address.builder()
                .street("Storgatan")
                .city("Malmo")
                .zipcode("34565")
                .build();
        addressRepository.save(address);

        UserProfile userProfile = UserProfile.builder()
                .nickname("rose")
                .phoneNumber("9845743098")
                .bio("user1")
                .build();
        userProfileRepository.save(userProfile);

        Customer customer = Customer.builder()
                .firstName("Rose")
                .lastName("Mari")
                .email("rose.mari@gmail.com")
                .address(address)
                .profile(userProfile)
                .build();
        customerRepository.save(customer);*/

        List<Category> categories = List.of(
                Category.builder().name("Electronics").build(),
                Category.builder().name("Books").build(),
                Category.builder().name("Bags").build(),
                Category.builder().name("Shoes").build(),
                Category.builder().name("Sports").build()
        );
        for (Category category : categories) {
            if (!categoryRepository.existsByNameIgnoreCase(category.getName())) {
                categoryRepository.save(category);
            }
        }


/*        Category electronicsCategory = categoryRepository.findByNameIgnoreCase("Electronics").getFirst();
        Category booksCategory = categoryRepository.findByNameIgnoreCase("Books").getFirst();
        Category bagsCategory = categoryRepository.findByNameIgnoreCase("Bags").getFirst();

        List<Product> products = List.of(
                Product.builder()
                        .name("Television")
                        .price(BigDecimal.valueOf(12000.00))
                        .category(electronicsCategory)
                        .build(),
                Product.builder()
                        .name("Fiction")
                        .price(BigDecimal.valueOf(110.00))
                        .category(booksCategory)
                        .build(),
                Product.builder()
                        .name("School bags")
                        .price(BigDecimal.valueOf(150.00))
                        .category(bagsCategory)
                        .build(),
                Product.builder()
                        .name("Mouse")
                        .price(BigDecimal.valueOf(500.00))
                        .category(electronicsCategory)
                        .build()
        );
        productRepository.saveAll(products);*/
    }
}
