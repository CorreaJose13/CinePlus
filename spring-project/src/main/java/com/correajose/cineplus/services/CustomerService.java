package com.correajose.cineplus.services;

import com.correajose.cineplus.dtos.customer.CustomerCreateDTO;
import com.correajose.cineplus.dtos.customer.CustomerResponseDTO;
import com.correajose.cineplus.dtos.customer.CustomerUpdateDTO;
import com.correajose.cineplus.exceptions.customer.EmailAlreadyExistsException;
import com.correajose.cineplus.models.customer.Customer;
import com.correajose.cineplus.models.customer.Role;
import com.correajose.cineplus.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.correajose.cineplus.utils.ErrorMessages.notFound;

@Service
public class CustomerService implements ICrudService<CustomerCreateDTO,CustomerUpdateDTO,CustomerResponseDTO> {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<CustomerResponseDTO> list() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(u -> modelMapper.map(u, CustomerResponseDTO.class)).toList();
    }

    public Customer find(int id) {
        return customerRepository.findById(id).orElseThrow(() -> notFound("customer","id",id));
    }

    public CustomerResponseDTO findById(int id) {
        Customer customer = find(id);
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }

    public CustomerResponseDTO findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> notFound("customer","email",email));
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }

    public CustomerResponseDTO create(CustomerCreateDTO body) {
        boolean customerExists = customerRepository.findByEmail(body.getEmail()).isPresent();
        if (customerExists) {
            throw new EmailAlreadyExistsException(body.getEmail());
        }
        Customer customer = modelMapper.map(body, Customer.class);
        customer.setPassword(passwordEncoder.encode(body.getPassword()));
        customer.setEnabled(true);
        customer.setRole(Role.user);
        return modelMapper.map(customerRepository.save(customer), CustomerResponseDTO.class);
    }

    @Transactional
    public CustomerResponseDTO update(int id, CustomerUpdateDTO body) {
        Customer customer = find(id);
        customer.setName(body.getName());
        customer.setLastName(body.getLastName());
        customer.setPhone(body.getPhone());
        customer.setEnabled(body.getEnabled());
        return modelMapper.map(customerRepository.save(customer), CustomerResponseDTO.class);
    }

    public CustomerResponseDTO delete(int id) {
        Customer customer = find(id);
        customerRepository.delete(customer);
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }

    /* Implementar logica de login*/

}
