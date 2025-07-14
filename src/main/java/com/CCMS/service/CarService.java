package com.CCMS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CCMS.model.Car;
import com.CCMS.model.Engine;
import com.CCMS.model.EngineConfig;
import com.CCMS.model.User;
import com.CCMS.repository.CarRepository;
import com.CCMS.repository.EngineConfigRepository;
import com.CCMS.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class CarService extends BaseService<Car, CarRepository> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EngineConfigRepository engineConfigRepository;

    public CarService(CarRepository repository){
        super(repository);
    }

    // Custom Methods
    
    public List<Car> findByUserId(Long userId) {
        return getRepository().findAllByUserId(userId);
    }

    public List<Car> findByEngineConfigId(Long engineConfigId) {
        return getRepository().findAllByEngineConfigId(engineConfigId);
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
    public Car updateCarEngine(@NotNull @Valid final Long carId, @NotNull @Valid final Engine engine){

        Car car = getRepository().findById(carId).get();
        car.setEngine(engine);

        return getRepository().save(car);

    }

    @Transactional
    public Car deleteCarEngine(@NotNull @Valid final Long carId) {

        Car car = getRepository().findById(carId).get();
        car.setEngine(null);

        return getRepository().save(car);

    }
    
    // Engine Config Section

    @Transactional
    public List<Car> saveEngineConfigList(@NotNull @Valid final List<Car> cars, @NotNull @Valid final long engineConfigId) throws Exception{

        EngineConfig engineConfig = engineConfigRepository.findById(engineConfigId).get();

        // TODO : backend Validations with specific Exceptions 

        /*
        if (user == null) {
            throw new NoUserExistsException (
                    String.format("No user exists with id=%d", userId));
        }
        */

        List<Car> engineConfigCars = new ArrayList<>();

        for (Car car : cars) {

            /*
            if (getRepository().existsById(car.getId())) {
                throw new Exception (String.format("There already exists an item with id=%d", car.getId()));
            }
            */

            car.setEngineConfig(engineConfig);
            engineConfigCars.add(car);
        }

        return getRepository().saveAll(engineConfigCars);

    }

    @Transactional
    public Car updateCarEngineConfig(@NotNull @Valid final Long carId, @NotNull @Valid final Long engineConfigId, @NotNull @Valid final EngineConfig engineConfig) {

        Car car = getRepository().findById(carId).get();
        EngineConfig savedEngineConfig = engineConfigRepository.findById(engineConfigId).get();

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

        engineConfig.setId(engineConfigId);
        engineConfig.setEngine(savedEngineConfig.getEngine());

        car.setEngineConfig(engineConfig);

        return getRepository().save(car);

    }

    @Transactional
    public Car deleteEngineConfigCar(Long carId, Long engineConfigId) {

        Car car = getRepository().findById(carId).get();
        car.setEngineConfig(null);

        EngineConfig engineConfig = engineConfigRepository.findById(engineConfigId).get();
        engineConfig.getCars().remove(car);

        return car;

    }

}