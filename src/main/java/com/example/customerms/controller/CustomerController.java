package com.example.customerms.controller;

import com.example.customerms.model.CustomerDto;
import com.example.customerms.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createStudent(@Valid @RequestBody CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findById(id));
    }

    // @GetMapping("/findAll")
    // public ResponseEntity<List<CustomerDto>>findAll(){
    //     return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomers());
    // }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable Long id,
                                              @Valid @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<Void> decreaseBalance(@PathVariable Long customerId,
                                                @RequestParam BigDecimal price) {
        customerService.decreaseBalance(customerId, price);
        return ResponseEntity.ok().build();
    }

}

