package com.almoatasem.demo;

import com.almoatasem.demo.models.entitiy.Role;
import com.almoatasem.demo.repository.RoleRepository;
import com.almoatasem.demo.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(AuthenticationService authenticationService, RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

			Role createAdminRole = roleRepository.save(new Role("ADMIN"));
			Role createUserRole = roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(createAdminRole);
			roles.add(createUserRole);

			authenticationService.registerUser("admin", "admin@gmail.com", "admin");
		};
	}
}