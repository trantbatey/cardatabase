package com.packt.cardatabase.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.models.Car;
import com.packt.cardatabase.repositories.CarRepository;

@RestController
public class CarController {
	private final CarRepository carRepo;
	
	public CarController (CarRepository carRepo) {
		this.carRepo = carRepo;
	}
	
	@RequestMapping("/cars")
	public Iterable<Car> getCars() {
		return carRepo.findAll();
	}

}
