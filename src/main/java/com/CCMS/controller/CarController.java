package com.CCMS.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMS.model.Car;
import com.CCMS.repository.CarRepository;
import com.CCMS.service.CarService;

@RestController
@RequestMapping(path = "/car")
public class CarController extends BaseController<Car, CarRepository, CarService>{

    public CarController(CarRepository repository, CarService service) {
        super(repository, service);
    }

}
