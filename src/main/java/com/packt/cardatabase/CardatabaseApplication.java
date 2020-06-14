package com.packt.cardatabase;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.packt.cardatabase.models.Car;
import com.packt.cardatabase.models.Owner;
import com.packt.cardatabase.models.User;
import com.packt.cardatabase.repositories.CarRepository;
import com.packt.cardatabase.repositories.OwnerRepository;
import com.packt.cardatabase.repositories.UserRepository;

@SpringBootApplication
public class CardatabaseApplication {
	private final CarRepository carRepo;
	private final OwnerRepository ownerRepo;
	private final UserRepository userRepo;

	public CardatabaseApplication(CarRepository carRepo, OwnerRepository ownerRepo, UserRepository userRepo) {
		this.carRepo = carRepo;
		this.ownerRepo = ownerRepo;
		this.userRepo = userRepo;
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
					List<Owner> ownerList = ownerRepo.findByFirstNameAndLastName("John", "Johnson");
					if (ownerList.size() == 0) {

						// add owners to the database
						Owner owner1 = new Owner("John", "Johnson");
						Owner owner2 = new Owner("Mary", "Robinson");
						ownerRepo.save(owner1);
						ownerRepo.save(owner2);

						// Add cars database
						carRepo.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 1964, 8000, owner1));
						carRepo.save(new Car("Cheverlet", "Silverado", "Silver", "BF5-JVM", 2011, 10000, owner1));
						carRepo.save(new Car("Audi", "Q5", "Silver", "BF5-1340", 2016, 32000, owner2));

						// username: user password: user
						userRepo.save(new User("user",
								"$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi", "USER"));
						// username: admin password: admin
						userRepo.save(new User("admin",
								"$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", "ADMIN"));

					}
				}
			}
		};
	}

}
