package com.stratum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stratum.service.SpecificationService;

@RestController
@RequestMapping("/specification")
@CrossOrigin(origins = "http://localhost:4200")
public class SpecificationController {

	@Autowired
	SpecificationService specificationService;
}
