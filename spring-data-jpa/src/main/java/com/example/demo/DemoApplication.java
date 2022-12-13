package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public void init() {
		for(int i=1; i<=10000; i++) {
			Customer customer = new Customer();
			customer.setFirstName("FirstName-" + i);
			customer.setLastName("LastName-" + i);
			customer.setAge(i);
			customerRepository.save(customer);
		}
	}

	@EventListener(ApplicationReadyEvent.class)
	public void test() {
		customerService.verify();
	}

}
