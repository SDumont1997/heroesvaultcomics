package com.mindhub.ecommerce;

import com.mindhub.ecommerce.models.*;
import com.mindhub.ecommerce.models.Character;
import com.mindhub.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class EcommerceApplication {

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PublisherRepository publisherRepository, AuthorRepository authorRepository, CharacterRepository characterRepository, ComicRepository comicRepository, MerchRepository merchRepository, AppUserRepository appUserRepository, PurchaseRepository purchaseRepository){
		return args -> {
			Publisher publisher1 = new Publisher("Marvel Comics", LocalDate.of(1939, 1, 10));
			publisherRepository.save(publisher1);
			Publisher publisher2 = new Publisher("DC Comics", LocalDate.of(1935, 2, 15));
			publisherRepository.save(publisher2);


			Author author1 = new Author("Stan", "Lee", LocalDate.of(1922, 12, 28), "Manhattan, New York, United States of America");
			authorRepository.save(author1);
			Author author2 = new Author("Bob", "Kane", LocalDate.of(1915, 10, 24), "New York, United States of America");
			authorRepository.save(author2);
			Author author3 = new Author("Jerry", "Siegel", LocalDate.of(1914, 10, 17), "Cleveland, Ohio, United States of America");
			authorRepository.save(author3);


			Character character1 = new Character("Peter Benjamin Parker", "Spiderman", "Amazing Fantasy #15", "New York, United States of America", author1, publisher1);
			character1.setImgUrl("https://www.superherodb.com/gallery2/075/413/41367.jpg");
			characterRepository.save(character1);
			Character character2 = new Character("Bruce Wayne", "Batman", "Detective Comics #27", "Gotham City, United States of America", author2, publisher2);
			character2.setImgUrl("https://www.superherodb.com/gallery2/075/429/42957.jpg");
			characterRepository.save(character2);
			Character character3 = new Character("Kal-El / Clark Joseph Kent", "Superman", "Action Comics #1", "Krypton", author3, publisher2);
			character3.setImgUrl("https://www.superherodb.com/gallery2/075/424/42430.jpg");
			characterRepository.save(character3);


			Comic comic1 = new Comic("Amazing Fantasy #15", author1, LocalDate.of(1962, 8, 10), publisher1, 1500.50, 5);
			comic1.addProtagonist(character1);
			comic1.setCoverImgUrl("https://static.wikia.nocookie.net/spiderman/images/e/ef/Amazing_Fantasy_Vol_1_15.png/revision/latest/scale-to-width-down/337?cb=20190315162328&path-prefix=es");
			comicRepository.save(comic1);
			Comic comic2 = new Comic("Detective Comics #27", author2, LocalDate.of(1939, 3, 30), publisher2, 2300.00, 2);
			comic2.addProtagonist(character2);
			comic2.setCoverImgUrl("https://static.wikia.nocookie.net/comicdc/images/4/43/Detective_Comics_Vol_1_27.jpg/revision/latest/scale-to-width-down/350?cb=20151230213656&path-prefix=es");
			comicRepository.save(comic2);
			Comic comic3 = new Comic("Action Comics #1", author3, LocalDate.of(1938, 4, 1), publisher2, 2350.00, 1);
			comic3.addProtagonist(character3);
			comic3.setCoverImgUrl("https://static.wikia.nocookie.net/superman/images/c/ce/Action_Comics_1.png/revision/latest/scale-to-width-down/300?cb=20150623162141&path-prefix=es");
			comicRepository.save(comic3);


			Merch merch1 = new Merch("Batman Beer Mug", MerchType.CUPS, character2, 75.00, 10);
			merch1.setImgUrl("https://cdn.shopify.com/s/files/1/0040/6624/6769/products/81zxcMBkY6S._SL1500.jpg?v=1632394111");
			merchRepository.save(merch1);
			Merch merch2 = new Merch("Superman T-shirt", MerchType.CLOTHING, character3, 60.00, 15);
			merch2.setImgUrl("https://cdn.shopify.com/s/files/1/0882/5118/products/Superman_Logo_T-Shirt-0056552_1024x1024.jpg?v=1598973660");
			merchRepository.save(merch2);
			Merch merch3 = new Merch("Iron Spider Funko Pop", MerchType.TOY, character1, 25.00, 12);
			merch3.setImgUrl("https://m.media-amazon.com/images/I/7187N5hjBoL._AC_SX522_.jpg");
			merchRepository.save(merch3);


			AppUser appUser1 = new AppUser("SDumont", "sdumont@gmail.com", passwordEncoder.encode("santiago1234"), "Santiago", "Dumont", LocalDate.of(1997, 3, 30), true);
			appUserRepository.save(appUser1);


			Set<Comic> comics1 = new HashSet<>();
			comics1.add(comic1);
			comics1.add(comic2);
			Set<Merch> purchaseMerch1 = new HashSet<>();
			purchaseMerch1.add(merch2);
			purchaseMerch1.add(merch3);
			Double amount1 = comic1.getPrice() + comic2.getPrice() + merch2.getPrice() + merch3.getPrice();
			Purchase purchase1 = new Purchase(appUser1, comics1, purchaseMerch1, amount1, PaymentOption.CASH);
			purchaseRepository.save(purchase1);
		};
	}

}
