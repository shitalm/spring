package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private Environment environment;

	@Value( "${app.version}" )
	private String appVersion;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void test() {
		getActiveProfiles();
		System.out.println("DemoApplication::test app.version=" + appVersion);
		System.out.println("DemoApplication::test count=" + customerService.getCount());
	}

	public void getActiveProfiles() {
		System.out.println("Active profile count=" + environment.getActiveProfiles().length);
		for (String profileName : environment.getActiveProfiles()) {
			System.out.println("Currently active profile - " + profileName);
		}
	}

}
