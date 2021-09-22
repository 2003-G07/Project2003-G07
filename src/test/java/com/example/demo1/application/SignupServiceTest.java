package com.example.demo1.application;

import com.example.demo1.models.Customer;
import com.example.demo1.models.Role;
import com.example.demo1.models.User;
import com.example.demo1.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SignupServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);

    private final SignupService service = new SignupService(userRepository);
    private final Role customerRole = new Role(0L, "Customer");

    @Test
    void testSaveUser() {
        Customer customer = new Customer("Kim", "Kimchi", "0700000000", "mail@mail.com");
        User returnedCustomer = new User(customer, 1L, "username", "password", List.of(customerRole));

        when(userRepository.save(any())).thenReturn(returnedCustomer);

        User user = service.signup(customer, "username", "password", List.of(customerRole));

        Assertions.assertEquals("Kim", user.getCustomer().getFirstName());
        Assertions.assertEquals("username", user.getUsername());
    }
}