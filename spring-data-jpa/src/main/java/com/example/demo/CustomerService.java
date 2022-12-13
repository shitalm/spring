package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository repo;

    public void verify() {
        Integer id = 2;
        Customer customer = repo.findById(id).get();
        System.out.println("first-name: " + customer.getFirstName());
        updateName(id, "NewFirstName-");
        System.out.println("after update first-name: " + customer.getFirstName());

        try {
            System.out.println("Going to sleep");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        customer = repo.findById(id).get();
        System.out.println("after sleep first-name: " + customer.getFirstName());
    }
    public void updateName(Integer id, String newPrefix) {
        Customer customer = repo.findById(id).get();
        customer.setFirstName(newPrefix + id);
        repo.save(customer);
    }
}
