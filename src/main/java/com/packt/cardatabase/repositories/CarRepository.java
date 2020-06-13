package com.packt.cardatabase.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.packt.cardatabase.models.Car;

@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, Long> {
	List<Car> findByBrand(@Param("brand") String brand);
	List<Car> findByColor(@Param("color") String color);
	List<Car> findByYear(@Param("year") String year);
	List<Car> findByBrandAndModel(@Param("brand") String brand, @Param("model") String model);
	List<Car> findByBrandOrColor(@Param("brand") String brand, @Param("color") String color);
	List<Car> findByBrandOrderByYearAsc(@Param("brand") String brand);
	
	@Query("SELECT c FROM Car c where c.model = ?1")
	List<Car> findByModel(@Param("model") String model);
	
	@Query("SELECT c FROM Car c where c.model LIKE %?1%")
	List<Car> findByModelLike(@Param("model") String model);
	
}
