package com.stratum.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stratum.model.Functionality;
import com.stratum.model.TestCaseStep;
import com.stratum.model.UseCase;
import com.stratum.service.FunctionalityService;
import com.stratum.service.TestCaseStepService;
import com.stratum.service.UseCaseService;

@RestController
@RequestMapping("/usecase")
@CrossOrigin(origins = "http://localhost:4200")
public class UseCaseController {

	@Autowired
	UseCaseService useCaseService;
	
	@Autowired
	TestCaseStepService testCaseStepService;
	
	@Autowired
	FunctionalityService functionalityService;
	
	@RequestMapping(value = "/for/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UseCase> getUsecaseForFunctionality(@PathVariable("id") long id) {
		Optional<Functionality> maybeFunctionality = functionalityService.getOne(id);
		if (maybeFunctionality.isPresent()) {
			UseCase useCase = useCaseService.getForFunctionality(id);
			return new ResponseEntity<UseCase>(useCase, HttpStatus.OK);
		}
		return new ResponseEntity<UseCase>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveUseCase(@RequestBody HashMap requestData) {
		Long functionalityId = Long.valueOf((Integer) requestData.get("functionality"));
		Optional<Functionality> maybeFunctionality = functionalityService.getOne(functionalityId);
		if (maybeFunctionality.isPresent()) {
			UseCase useCase = new UseCase();
			useCase.setFunctionality(maybeFunctionality.get());
			useCase.setTitle("Usecase for "+maybeFunctionality.get().getName());
			useCaseService.save(useCase);
		}
	}
	
	@RequestMapping(value = "/teststep", method = RequestMethod.POST)
	public void saveUseCaseStep(@RequestBody HashMap requestData) {
		Long useCaseId = Long.valueOf((Integer) requestData.get("usecase"));
		String value = (String) requestData.get("value");
		Optional<UseCase> maybeUseCase = useCaseService.getOne(useCaseId);
		if(maybeUseCase.isPresent()) {
			TestCaseStep step = new TestCaseStep();
			step.setUseCase(maybeUseCase.get());
			step.setName(value);
			step.setTestResult(TestCaseStep.State.NEW.toString());
			testCaseStepService.save(step);
		}

	}
}
