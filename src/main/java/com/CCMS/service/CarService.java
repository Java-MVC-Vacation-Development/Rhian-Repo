package com.CCMS.service;

import com.CCMS.model.Car;
import com.CCMS.repository.CarRepository;

import org.springframework.stereotype.Service;

@Service
public class CarService extends BaseService<Car, CarRepository> {

    public CarService(CarRepository repository){
        super(repository);
    }

}