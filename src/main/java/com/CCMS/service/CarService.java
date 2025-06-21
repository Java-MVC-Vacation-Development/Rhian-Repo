package com.CCMS.service;

import com.CCMS.model.Car;
import com.CCMS.repository.BaseRepository;
import com.CCMS.repository.CarRepository;

import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
public class CarService implements BaseService<Car> {

    CarRepository carRepository;

    @Override
    public BaseRepository<Car> getRepository() {
        return carRepository;
    }

}