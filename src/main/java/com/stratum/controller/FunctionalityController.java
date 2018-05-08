package com.stratum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stratum.service.FunctionalityService;

@RestController
@RequestMapping("/functionality")
@CrossOrigin(origins = "http://localhost:4200")
public class FunctionalityController {

	@Autowired
	FunctionalityService functionalityService;
	
}
