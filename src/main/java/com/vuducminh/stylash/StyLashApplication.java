package com.vuducminh.stylash;

import com.vuducminh.stylash.auth.AuthenticationService;
import com.vuducminh.stylash.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.vuducminh.stylash.user.Role.ADMIN;
import static com.vuducminh.stylash.user.Role.MANAGER;

@SpringBootApplication
public class StyLashApplication {

	public static void main(String[] args) {
		SpringApplication.run(StyLashApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.address("uit0")
					.phone_number("012345678")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.address("uit1")
					.phone_number("123456789")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}
}
