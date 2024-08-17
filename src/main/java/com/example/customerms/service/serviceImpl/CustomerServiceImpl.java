package com.example.customerms.service.serviceImpl;

import com.example.customerms.dao.Entity.CustomerEntity;
import com.example.customerms.dao.repository.CustomerRepository;
import com.example.customerms.exception.CustomerNotFoundException;
import com.example.customerms.mapper.CustomerMapper;
import com.example.customerms.model.CustomerDto;
import com.example.customerms.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public void createCustomer(CustomerDto customer) {
        customerRepository.save(customerMapper.toEntity(customer));
    }

    @Override
    public CustomerDto findById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public void updateCustomer(Long id, CustomerDto customerDto) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customerEntity.setFullName(customerDto.getFullName());
        customerEntity.setAge(customerDto.getAge());
        customerEntity.setPin(customerDto.getPin());
        customerEntity.setBalance(customerDto.getBalance());
        customerRepository.save(customerEntity);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void decreaseBalance(Long customerId, BigDecimal price) {
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        customerEntity.setBalance(customerEntity.getBalance().subtract(price));
        customerRepository.save(customerEntity);
    }

}
