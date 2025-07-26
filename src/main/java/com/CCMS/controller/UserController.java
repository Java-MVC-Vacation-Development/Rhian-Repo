package com.CCMS.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMS.model.Car;
import com.CCMS.model.User;
import com.CCMS.repository.UserRepository;
import com.CCMS.service.CarService;
import com.CCMS.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController extends BaseController<User, UserRepository, UserService>{

    @Autowired
    CarService carService;

    public UserController(UserRepository repository, UserService service, CarService carService) {
        super(repository, service);
    }

    //Car Session

    // POST: http://localhost:8080/users/1/cars
    // Content-Type: application/json
    // Payload: [{ "name": "car" }, { "name": "car2" }]
    @PostMapping(path = "/{userId}/cars")
    public ResponseEntity<List<Car>> createUserCars(@PathVariable long userId, @RequestBody List<Car> cars) throws Exception {

        List<Car> response = carService.saveList(cars, userId);
        return (response != null) ? ResponseEntity.status(HttpStatus.CREATED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // GET: http://localhost:8080/users/1/cars
    @GetMapping(path="/{userId}/cars")
    public ResponseEntity<List<Car>> getUserCars(@PathVariable Long userId) {

        List<Car> response = carService.findByUserId(userId);
        return (response != null) ? ResponseEntity.status(HttpStatus.OK).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // GET: http://localhost:8080/users/1/cars/1
    @GetMapping(path="/{userId}/cars/{carId}")
    public ResponseEntity<Car> getUserCars(@PathVariable Long userId, @PathVariable Long carId) {

        List<Car> response = carService.findByUserId(userId);

        Car carResponse = null;

        for (Car car : response) {
            if(Objects.equals(car.getId(), carId))
                carResponse = car;
        }

        return (carResponse != null) ? ResponseEntity.status(HttpStatus.OK).body(carResponse) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // PUT: http://localhost:8080/users/1/cars/1
    // Content-Type: application/json
    // Payload: { "name": "test" }
    @PutMapping(path="/{userId}/cars/{carId}")
    public ResponseEntity<Car> updateUserCars(@PathVariable Long userId, @PathVariable Long carId, @RequestBody Car car) {

        Car response = carService.updateUserCar(car, carId, userId);
        return (response != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // DELETE: http://localhost:8080/users/1/cars/1
    @DeleteMapping(path="/{userId}/cars/{carId}")
    public ResponseEntity<Car> deleteUserCar(@PathVariable Long userId, @PathVariable Long carId) {

        Car response =  carService.deleteUserCar(carId, userId);
        return (response != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}
