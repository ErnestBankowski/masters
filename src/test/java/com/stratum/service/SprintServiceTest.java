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

import com.stratum.model.Project;
import com.stratum.model.ProjectParticipant;
import com.stratum.model.Sprint;
import com.stratum.model.User;
import com.stratum.repository.SprintRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SprintServiceTest {
	
	@InjectMocks
	SprintServiceImpl sprintService;

	@Mock
	SprintRepository sprintRepository;
	
	@Mock
	ProjectService projectService;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnEmptyListIfNothingSaved() {
		Mockito.when(sprintRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(sprintService.list()).isEmpty();
	}

	@Test
	public void shouldReturnPopulatedListIfSaved() {
		Sprint sprint = new Sprint();
		sprint.setId(99L);
		Mockito.when(sprintRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(sprintService.list()).isEmpty();
		sprintService.save(sprint);
		List<Sprint> sprintList = new ArrayList<>();
		sprintList.add(sprint);
		Mockito.when(sprintRepository.findAll()).thenReturn(sprintList);
		assertThat(sprintService.list()).isNotEmpty();
	}

	@Test
	public void shouldReturnSprintIfSavedWithValidId() {
		Sprint sprint = new Sprint();
		sprint.setId(99L);
		Mockito.when(sprintRepository.findById(99L)).thenReturn(Optional.of(sprint));
		assertThat(sprintService.getOne(99L).get()).isEqualTo(sprint);
	}
	
	@Test
	public void shouldReturnPopulatedListIfUserIsParticipant() {
		Sprint sprint = new Sprint();
		Project project = new Project();
		User user = new User();
		ProjectParticipant participant = new ProjectParticipant();
		participant.setParticipant(user);
		participant.setProject(project);
		sprint.setProject(project);
		List<Project> projects = new ArrayList<>();
		List<Sprint> sprints = new ArrayList<>();
		projects.add(project);
		sprints.add(sprint);
		Mockito.when(projectService.getForUser(user.getEmail())).thenReturn(projects);
		Mockito.when(sprintRepository.getAllForProject(project.getId())).thenReturn(sprints);
		assertThat(sprintService.getForUser(user.getEmail()))
		.isNotEmpty()
		.contains(sprint)
		.hasSize(1);
	}
}
