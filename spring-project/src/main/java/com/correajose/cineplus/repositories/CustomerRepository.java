package com.correajose.cineplus.repositories;

import com.correajose.cineplus.models.customer.Customer;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmail(@Email String email);
}
