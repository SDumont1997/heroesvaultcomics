package com.mindhub.ecommerce;

import com.mindhub.ecommerce.models.*;
import com.mindhub.ecommerce.models.Character;
import com.mindhub.ecommerce.repositories.*;
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
	public CommandLineRunner initData(PublisherRepository publisherRepository, AuthorRepository authorRepository, CharacterRepository characterRepository, ComicRepository comicRepository, ComicCharacterRepository comicCharacterRepository){
		return args -> {
			Publisher publisher1 = new Publisher("Marvel Comics", LocalDate.of(1939, 1, 10));
			publisherRepository.save(publisher1);
			Publisher publisher2 = new Publisher("DC Comics", LocalDate.of(1935, 2, 15));
			publisherRepository.save(publisher2);


			Author author1 = new Author("Stan", "Lee", LocalDate.of(1922, 12, 28), "Manhattan, New York, United States of America");
			authorRepository.save(author1);
			Author author2 = new Author("Bob", "Kane", LocalDate.of(1915, 10, 24), "New York, United States of America");
			authorRepository.save(author2);


			Character character1 = new Character("Peter Benjamin Parker", "Spiderman", "Amazing Fantasy #15", "New York, United States of America", author1, publisher1);
			characterRepository.save(character1);
			Character character2 = new Character("Bruce Wayne", "Batman", "Detective Comics #27", "Gotham City, United States of America", author2, publisher2);
			characterRepository.save(character2);


			Comic comic1 = new Comic("Amazing Fantasy #15", author1, LocalDate.of(1962, 8, 10), publisher1, 1500.50, 5);
			comicRepository.save(comic1);
			Comic comic2 = new Comic("Detective Comics #27", author2, LocalDate.of(1939, 3, 30), publisher2, 2300.00, 2);
			comicRepository.save(comic2);


			ComicCharacter comicCharacter1 = new ComicCharacter(comic1, character1);
			comicCharacterRepository.save(comicCharacter1);
			ComicCharacter comicCharacter2 = new ComicCharacter(comic2, character2);
		};
	}

}
