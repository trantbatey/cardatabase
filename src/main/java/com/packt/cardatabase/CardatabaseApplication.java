package com.packt.cardatabase;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.packt.cardatabase.models.Car;
import com.packt.cardatabase.models.Owner;
import com.packt.cardatabase.repositories.CarRepository;
import com.packt.cardatabase.repositories.OwnerRepository;

@SpringBootApplication
public class CardatabaseApplication {
	private final CarRepository carRepo;
	private final OwnerRepository ownRepo;

	public CardatabaseApplication(CarRepository carRepo, OwnerRepository ownRepo) {
		this.carRepo = carRepo;
		this.ownRepo = ownRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
	}

	/**
	 * Add seeder data to the database Use command line argument. EX: "mvnw
	 * spring-boot:run -Dspring-boot.run.arguments=seed"
	 * 
	 * @return
	 */
	@Bean
	CommandLineRunner runner() {
		return args -> {

			// Loop through all of the command line options to see is the database
			// should be seeded
			for (String option : args) {
				if (option.equals("seed")) {

					// remove old data
					List<Owner> ownerList = ownRepo.findByFirstNameAndLastName("John", "Johnson");
					if (ownerList.size() == 0) {

						// add owners to the database
						Owner owner1 = new Owner("John", "Johnson");
						Owner owner2 = new Owner("Mary", "Robinson");
						ownRepo.save(owner1);
						ownRepo.save(owner2);

						// Add cars database
						carRepo.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 1964, 8000, owner1));
						carRepo.save(new Car("Cheverlet", "Silverado", "Silver", "BF5-JVM", 2011, 10000, owner1));
						carRepo.save(new Car("Audio", "Q5", "Silver", "BF5-1340", 2016, 32000, owner2));
					}
				}
			}
		};
	}

}
