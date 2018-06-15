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
import com.stratum.model.Project;
import com.stratum.model.ProjectParticipant;
import com.stratum.model.Sprint;
import com.stratum.model.User;
import com.stratum.repository.FunctionalityRepository;
import com.stratum.repository.SprintRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionalityServiceTest {

	@Mock
	FunctionalityRepository functionalityRepository;
	
	@Mock
	SprintRepository sprintRepository;

	@InjectMocks
	FunctionalityServiceImpl functionalityService;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnEmptyListIfNothingSaved() {
		Mockito.when(functionalityRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(functionalityService.list()).isEmpty();
	}

	@Test
	public void shouldReturnPopulatedListIfSaved() {
		Functionality functionality = new Functionality();
		functionality.setId(99L);
		Mockito.when(functionalityRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(functionalityService.list()).isEmpty();
		functionalityService.save(functionality);
		List<Functionality> functionalityList = new ArrayList<>();
		functionalityList.add(functionality);
		Mockito.when(functionalityRepository.findAll()).thenReturn(functionalityList);
		assertThat(functionalityService.list()).isNotEmpty();
	}
	
	@Test
	public void shouldReturnFunctionalityIfSavedWithValidId() {
		Functionality functionality = new Functionality();
		functionality.setId(99L);
		Mockito.when(functionalityRepository.findById(99L)).thenReturn(Optional.of(functionality));
		assertThat(functionalityService.getOne(99L).get()).isEqualTo(functionality);
	}
			
	@Test
	public void shouldReturnAllForSprintIfAssignedToSprint() {
		Functionality functionality = new Functionality();
		functionality.setId(99L);
		Sprint sprint = new Sprint();
		functionality.setSprint(sprint);
		List<Functionality> functionalities = new ArrayList<>();
		functionalities.add(functionality);
		Mockito.when(functionalityRepository.getAllForSprint(sprint.getId())).thenReturn(functionalities);
		assertThat(functionalityService.getAllForSprint(sprint.getId()))
		.isNotEmpty()
		.contains(functionality)
		.hasSize(1);
	}
	
	@Test
	public void shouldReturnAllForProjectIfAddedToProject() {
		Functionality functionality = new Functionality();
		functionality.setId(99L);
		Project project = new Project();
		Sprint sprint = new Sprint();
		sprint.setProject(project);
		functionality.setSprint(sprint);
		List<Sprint> sprints = new ArrayList<>();
		sprints.add(sprint);
		List<Functionality> functionalities = new ArrayList<>();
		functionalities.add(functionality);
		Mockito.when(sprintRepository.getAllForProject(project.getId())).thenReturn(sprints);
		Mockito.when(functionalityRepository.getAllForSprint(sprint.getId())).thenReturn(functionalities);
		assertThat(functionalityService.getAllForSprint(sprint.getId()))
		.isNotEmpty()
		.contains(functionality)
		.hasSize(1);
	}
	
	@Test
	public void shouldReturnAllForUserIfUserIsParticipant() {
		User user = new User();
		Functionality functionality = new Functionality();
		functionality.setId(99L);
		Project project = new Project();
		Sprint sprint = new Sprint();
		sprint.setProject(project);
		functionality.setSprint(sprint);
		ProjectParticipant participant = new ProjectParticipant();
		participant.setParticipant(user);
		participant.setProject(project);
		List<Functionality> functionalities = new ArrayList<>();
		functionalities.add(functionality);
		Mockito.when(functionalityRepository.getAllForUser(user.getEmail())).thenReturn(functionalities);
		assertThat(functionalityService.getAllForUser(user.getEmail()))
		.isNotEmpty()
		.contains(functionality)
		.hasSize(1);
	}
}
