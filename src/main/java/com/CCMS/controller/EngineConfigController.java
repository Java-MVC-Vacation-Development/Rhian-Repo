package com.CCMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMS.model.Car;
import com.CCMS.model.EngineConfig;
import com.CCMS.repository.EngineConfigRepository;
import com.CCMS.service.CarService;
import com.CCMS.service.EngineConfigService;

@RestController
@RequestMapping(path = "/engineconfig")
public class EngineConfigController extends BaseController<EngineConfig, EngineConfigRepository, EngineConfigService>{

    @Autowired
    CarService carService;

    public EngineConfigController(EngineConfigRepository repository, EngineConfigService service) {
        super(repository, service);
    }

    // Car Session

    // POST: http://localhost:8080/engineconfig/1/cars
    // Content-Type: application/json
    // Payload: [{ "name": "car" }, { "name": "car2" }]
    @PostMapping(path = "/{engineConfigId}/cars")
    public ResponseEntity<List<Car>> createEngineConfigCars(@PathVariable long engineConfigId, @RequestBody List<Car> cars) throws Exception {

        List<Car> response = carService.saveEngineConfigList(cars, engineConfigId);
        return (response != null) ? ResponseEntity.status(HttpStatus.CREATED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // GET: http://localhost:8080/engineconfig/1/cars
    @GetMapping(path="/{engineConfigId}/cars")
    public ResponseEntity<List<Car>> getEngineConfigCars(@PathVariable Long engineConfigId) {

        List<Car> response = carService.findByEngineConfigId(engineConfigId);
        return (response != null) ? ResponseEntity.status(HttpStatus.CREATED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // DELETE: http://localhost:8080/users/1/cars/1
    @DeleteMapping(path="/{engineConfigId}/cars/{carId}")
    public ResponseEntity<Car> deleteEngineConfigCar(@PathVariable Long engineConfigId, @PathVariable Long carId) {

        Car response =  carService.deleteEngineConfigCar(carId, engineConfigId);
        return (response != null) ? ResponseEntity.status(HttpStatus.CREATED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}

