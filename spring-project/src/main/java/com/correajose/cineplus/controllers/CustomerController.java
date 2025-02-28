package com.correajose.cineplus.controllers;

import com.correajose.cineplus.dtos.customer.CustomerCreateDTO;
import com.correajose.cineplus.dtos.customer.CustomerUpdateDTO;
import com.correajose.cineplus.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.correajose.cineplus.utils.ResponseUtils.buildOkResponse;

@RestController
@RequestMapping("/customer")
public class CustomerController implements ICrudController<CustomerCreateDTO,CustomerUpdateDTO>{

    @Autowired
    private CustomerService customerService;

    @GetMapping("/find/all")
    public ResponseEntity<?> list() {
        return buildOkResponse(HttpStatus.OK, customerService.list(), "List of costumers");
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<?> find(int id) {
        return buildOkResponse(HttpStatus.OK, customerService.findById(id), "Customer with id: " + id + " found");
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        return buildOkResponse(HttpStatus.OK, customerService.findByEmail(email), "Customer with email: " + email + " found");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid CustomerCreateDTO body) {
        return buildOkResponse(HttpStatus.CREATED, customerService.create(body), "Customer created successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(int id, @Valid CustomerUpdateDTO body) {
        return buildOkResponse(HttpStatus.OK, customerService.update(id,body), "Customer updated successfully");
    }

    @PutMapping("/toggleStatus/{id}")
    public ResponseEntity<?> toggleStatus(int id) {
        return buildOkResponse(HttpStatus.OK, customerService.toggleStatus(id), "Status changed successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(int id) {
        return buildOkResponse(HttpStatus.NO_CONTENT, customerService.delete(id), "Customer deleted successfully");
    }
    
}
