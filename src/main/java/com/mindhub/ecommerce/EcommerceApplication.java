package com.mindhub.ecommerce;

import com.mindhub.ecommerce.models.Author;
import com.mindhub.ecommerce.repositories.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(AuthorRepository authorRepository){
		return args -> {
			Author author1 = new Author("Alan", "Moore", LocalDate.of(1953, 11, 18), "Northampton, United Kingdom");
			authorRepository.save(author1);
		};
	}

}
