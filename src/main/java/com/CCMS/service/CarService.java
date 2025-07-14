package com.CCMS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CCMS.model.Car;
import com.CCMS.model.User;
import com.CCMS.repository.CarRepository;
import com.CCMS.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class CarService extends BaseService<Car, CarRepository> {

    @Autowired
    UserRepository userRepository;

    public CarService(CarRepository repository){
        super(repository);
    }

    // Custom Methods
    
    public List<Car> findByUserId(Long userId) {
        return getRepository().findAllByUserId(userId);
    }

    @Transactional
    public List<Car> saveList(@NotNull @Valid final List<Car> cars, @NotNull @Valid final long userId) throws Exception {

        User user = userRepository.findById(userId).get();

        // TODO : backend Validations with specific Exceptions 

        /*
        if (user == null) {
            throw new NoUserExistsException (
                    String.format("No user exists with id=%d", userId));
        }
        */

        List<Car> userCars = new ArrayList<>();

        for (Car car : cars) {

            /*
            if (getRepository().existsById(car.getId())) {
                throw new Exception (String.format("There already exists an item with id=%d", car.getId()));
            }
            */

            car.setUser(user);
            userCars.add(car);
        }

        return getRepository().saveAll(userCars);

    }

    @Transactional
    public Car updateUserCar(@NotNull @Valid final Car car, @NotNull @Valid final Long carId, @NotNull @Valid final Long userId) {

        User user =  userRepository.findById(userId).get();

        /*
        if (user == null) {
            throw new NoUserExistsException (
                    String.format("No user exists with id=%d", userId));
        }

        if (!getRepository().existsById(carId)) {
            throw new NoItemExistsException (
                    String.format("No item exists with id=%d", itemId));
        }
        */

        car.setId(carId);
        car.setUser(user);

        return getRepository().save(car);
    }

    @Transactional
    public Car deleteUserCar(@NotNull @Valid Long carId, @NotNull @Valid Long userId) {

        Car car = getRepository().findById(carId).get();
        getRepository().delete(car);
        return car;

    }

}