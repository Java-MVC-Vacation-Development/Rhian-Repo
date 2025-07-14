package com.CCMS.controller;

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
import com.CCMS.model.Engine;
import com.CCMS.model.EngineConfig;
import com.CCMS.repository.CarRepository;
import com.CCMS.service.CarService;


@RestController
@RequestMapping(path = "/car")
public class CarController extends BaseController<Car, CarRepository, CarService>{

    public CarController(CarRepository repository, CarService service) {
        super(repository, service);
    }

    // Engine Session

    // POST: http://localhost:8080/car/1/engine
    // Content-Type: application/json
    // Payload: { "name": "V8",  "electrical" : false, "cilinders" : 8, "fuel" : 0}
    @PostMapping(path = "/{carId}/engine")
    public ResponseEntity<Car> createCarEngine(@PathVariable long carId, @RequestBody Engine engine) throws Exception {

        Car response = getService().updateCarEngine(carId, engine);
        return (response != null) ? ResponseEntity.status(HttpStatus.CREATED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // GET: http://localhost:8080/car/1/engine
    @GetMapping(path="/{carId}/engine")
    public ResponseEntity<Engine> getCarEngine(@PathVariable Long carId) {

        Car car = getService().get(carId).get();
        Engine response = car.getEngine();

        return (response != null) ? ResponseEntity.status(HttpStatus.CREATED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // DELETE: http://localhost:8080/car/1/engine
    @DeleteMapping(path="/{carId}/engine")
    public ResponseEntity<Car> deleteCarEngine(@PathVariable Long carId) {

        Car response =  getService().deleteCarEngine(carId);
        return (response != null) ? ResponseEntity.status(HttpStatus.CREATED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // Engine Config Session

    // PUT: http://localhost:8080/car/1/engineconfig/1
    // Content-Type: application/json
    // Payload: { "configName": "test", "engineMainSyncTime" : 10, "demonUse" : true, "ecoUse" : false, "standardUse" : false }
    @PutMapping(path="/{carId}/engineconfig/{engineConfigId}")
    public ResponseEntity<Car> updateCarsEngineConfig(@PathVariable Long carId, @PathVariable Long engineConfigId, @RequestBody EngineConfig engineConfig) {

        Car response = getService().updateCarEngineConfig(carId, engineConfigId, engineConfig);
        return (response != null) ? ResponseEntity.status(HttpStatus.CREATED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}
