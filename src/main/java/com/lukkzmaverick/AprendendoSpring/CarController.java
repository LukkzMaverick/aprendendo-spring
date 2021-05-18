package com.lukkzmaverick.AprendendoSpring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cars")
public class CarController {
	
	@Autowired
	private CarRepository carRepository;
	public void carController(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public Car create(@RequestBody Car car) {
		return this.carRepository.save(car);
	}
	
	@GetMapping("{id}")
	@ResponseBody
	public Optional<Car> findById(@PathVariable("id") Long id) {
		return this.carRepository.findById(id);
	}
	
	@GetMapping
	@ResponseBody
	public List<Car> findAll(){
		return this.carRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Long id) {
		this.carRepository.deleteById(id);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void updateById(@PathVariable("id") Long id, @RequestBody Car car) {
		this.carRepository.findById(id).map(carInDatabase -> {
			carInDatabase.setName(car.getName());
			carInDatabase.setColor(car.getColor());
			carInDatabase.setYear(car.getYear());
			Car updated = this.carRepository.save(carInDatabase);
			return updated;
		});
	}
}
