package com.example.jasperlmh.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.jasperlmh.entity.Car;
import com.example.jasperlmh.repository.CarRepository;
import com.example.jasperlmh.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class GetFileController {
	@Autowired
	ReportService reportService;
	@Autowired
	CarRepository care;
	@GetMapping ("/asd")
	public String start() {
		return "start here";
	}
	@GetMapping ("/list")
	public List<Car> stasrt() {
		return care.findAll();
	}
	@GetMapping("/getJasper/{format}")
	public String report(@PathVariable String format) {
		try {
			return reportService.print(format);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}
}
