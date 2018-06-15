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
import com.stratum.repository.UseCaseRepository;
import com.stratum.repository.UseCaseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UseCaseServiceTest {
	
	@InjectMocks
	UseCaseServiceImpl usecaseService;
	
	@Mock
	UseCaseRepository useCaseRepository;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnEmptyListIfNothingSaved() {
		Mockito.when(useCaseRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(usecaseService.list()).isEmpty();
	}

	@Test
	public void shouldReturnPopulatedListIfSaved() {
		UseCase step = new UseCase();
		step.setId(99L);
		Mockito.when(useCaseRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(usecaseService.list()).isEmpty();
		usecaseService.save(step);
		List<UseCase> stepList = new ArrayList<>();
		stepList.add(step);
		Mockito.when(useCaseRepository.findAll()).thenReturn(stepList);
		assertThat(usecaseService.list()).isNotEmpty();
	}

	@Test
	public void shouldReturnUseCaseIfSavedWithValidId() {
		UseCase step = new UseCase();
		step.setId(99L);
		Mockito.when(useCaseRepository.findById(99L)).thenReturn(Optional.of(step));
		assertThat(usecaseService.getOne(99L).get()).isEqualTo(step);
	}
	
	public void shouldReturnForFunctionalityIfAssigned() {
		UseCase usecase = new UseCase();
		TestCaseStep step = new TestCaseStep();
		step.setUseCase(usecase);		
		List<UseCase> usecases = new ArrayList<>();
		usecases.add(usecase);
		Mockito.when(useCaseRepository.getForFunctionality(usecase.getFunctionality().getId())).thenReturn(usecases);
		assertThat(usecaseService.getForFunctionality(usecase.getFunctionality().getId())).isNotNull();
	}
}
