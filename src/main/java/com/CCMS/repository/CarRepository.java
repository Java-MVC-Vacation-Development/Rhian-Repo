package com.CCMS.repository;

import java.util.List;

import com.CCMS.model.Car;

public interface CarRepository extends BaseRepository<Car>{

    List<Car> findAllByUserId(Long userId);
}
