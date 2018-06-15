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

import com.stratum.model.Functionality;
import com.stratum.model.Specification;
import com.stratum.repository.SpecificationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecificationServiceTest {

	@InjectMocks
	SpecificationServiceImpl specificationService;

	@Mock
	SpecificationRepository specificationRepository;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnEmptyListIfNothingSaved() {
		Mockito.when(specificationRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(specificationService.list()).isEmpty();
	}

	@Test
	public void shouldReturnPopulatedListIfSaved() {
		Specification specification = new Specification();
		specification.setId(99L);
		Mockito.when(specificationRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(specificationService.list()).isEmpty();
		specificationService.save(specification);
		List<Specification> specificationList = new ArrayList<>();
		specificationList.add(specification);
		Mockito.when(specificationRepository.findAll()).thenReturn(specificationList);
		assertThat(specificationService.list()).isNotEmpty();
	}

	@Test
	public void shouldReturnSpecificationIfSavedWithValidId() {
		Specification specification = new Specification();
		specification.setId(99L);
		Mockito.when(specificationRepository.findById(99L)).thenReturn(Optional.of(specification));
		assertThat(specificationService.getOne(99L).get()).isEqualTo(specification);
	}

	@Test
	public void shouldReturnedPopulatedListIfAssignedToFunctionality() {
		Functionality functionality = new Functionality();
		Specification specification = new Specification();
		List<Specification> specs = new ArrayList<>();
		specs.add(specification);
		Mockito.when(specificationRepository.getForFunctionality(functionality.getId())).thenReturn(specs);
		assertThat(specificationService.getForFunctionality(functionality.getId())).isNotNull();
	}

}
