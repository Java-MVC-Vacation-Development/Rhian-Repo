package com.CCMS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CCMS.model.Car;
import com.CCMS.model.Engine;
import com.CCMS.model.User;
import com.CCMS.repository.CarRepository;
import com.CCMS.repository.EngineConfigRepository;
import com.CCMS.repository.EngineRepository;
import com.CCMS.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class CarService extends BaseService<Car, CarRepository> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EngineConfigRepository engineConfigRepository;

    @Autowired
    EngineRepository engineRepository;

    public CarService(CarRepository repository){
        super(repository);
    }

    // Custom Methods
    
    public List<Car> findByUserId(Long userId) {
        return getRepository().findAllByUserId(userId);
    }

    // User Section

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

    // Engine Section

    @Transactional
    public Car createCarEngine(@NotNull @Valid final Long carId, @NotNull @Valid final Engine engine){

        Car car = getRepository().findById(carId).get();

        car.setEngine(engineRepository.save(engine));

        return getRepository().save(car);

    }

    @Transactional
    public Car updateCarEngine(@NotNull @Valid final Long carId, @NotNull @Valid final Engine engine){

        Car car = getRepository().findById(carId).get();

        if (car.getEngine() != null){

            car.setEngine(null);
            
            getRepository().save(car);

            car.setEngine(engine);
    
            return getRepository().save(car);
        }
        else{

            return this.createCarEngine(carId, engine);
            
        }

    }

    @Transactional
    public Car deleteCarEngine(@NotNull @Valid final Long carId) {

        Car car = getRepository().findById(carId).get();
        car.setEngine(null);

        return getRepository().save(car);

    }
    
}