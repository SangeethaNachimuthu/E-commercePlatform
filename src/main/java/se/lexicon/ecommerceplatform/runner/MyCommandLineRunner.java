package se.lexicon.ecommerceplatform.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.ecommerceplatform.entity.Address;
import se.lexicon.ecommerceplatform.entity.Customer;
import se.lexicon.ecommerceplatform.entity.UserProfile;
import se.lexicon.ecommerceplatform.repository.AddressRepository;
import se.lexicon.ecommerceplatform.repository.CustomerRepository;
import se.lexicon.ecommerceplatform.repository.UserProfileRepository;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;
    private UserProfileRepository userProfileRepository;

    @Autowired
    public MyCommandLineRunner(CustomerRepository customerRepository,
                               AddressRepository addressRepository,
                               UserProfileRepository userProfileRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("#################");

        Address address = Address.builder()
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
        customerRepository.save(customer);
    }
}
