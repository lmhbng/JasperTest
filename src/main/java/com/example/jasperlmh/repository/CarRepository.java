package com.example.jasperlmh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jasperlmh.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
