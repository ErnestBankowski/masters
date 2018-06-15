package com.stratum.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stratum.model.TestCaseStep;
import com.stratum.model.UseCase;
import com.stratum.repository.TestCaseStepRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseStepServiceTest {
	
	@InjectMocks
	TestCaseStepServiceImpl stepService;
	
	@Mock
	TestCaseStepRepository testCaseStepRepository;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnEmptyListIfNothingSaved() {
		Mockito.when(testCaseStepRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(stepService.list()).isEmpty();
	}

	@Test
	public void shouldReturnPopulatedListIfSaved() {
		TestCaseStep step = new TestCaseStep();
		step.setId(99L);
		Mockito.when(testCaseStepRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(stepService.list()).isEmpty();
		stepService.save(step);
		List<TestCaseStep> stepList = new ArrayList<>();
		stepList.add(step);
		Mockito.when(testCaseStepRepository.findAll()).thenReturn(stepList);
		assertThat(stepService.list()).isNotEmpty();
	}

	@Test
	public void shouldReturnTestCaseStepIfSavedWithValidId() {
		TestCaseStep step = new TestCaseStep();
		step.setId(99L);
		Mockito.when(testCaseStepRepository.findById(99L)).thenReturn(Optional.of(step));
		assertThat(stepService.getOne(99L).get()).isEqualTo(step);
	}
	
	@Test
	public void shouldReturnThreeStepsIfStoredForUsecase() {
		UseCase usecase = new UseCase();
		TestCaseStep step1 = new TestCaseStep();
		step1.setUseCase(usecase);
		TestCaseStep step2 = new TestCaseStep();
		step2.setUseCase(usecase);
		TestCaseStep step3 = new TestCaseStep();
		step3.setUseCase(usecase);
		List<TestCaseStep> steps = new ArrayList<TestCaseStep>();
		steps.add(step1);
		steps.add(step2);
		steps.add(step3);
		Mockito.when(testCaseStepRepository.getAllForUsecase(usecase.getId())).thenReturn(steps);
		assertThat(stepService.getAllForUsecase(usecase.getId()))
		.isNotEmpty()
		.contains(step1,step2,step3)
		.hasSize(3);
	}
}
