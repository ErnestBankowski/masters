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
import com.stratum.model.User;
import com.stratum.repository.ProjectParticipantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectParticipantServiceTest {

	@InjectMocks
	ProjectParticipantServiceImpl projectParticipantService;

	@Mock
	ProjectParticipantRepository projectParticipantRepository;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturnProjectParticipantIfSavedWithValidId() {
		ProjectParticipant participant = new ProjectParticipant();
		participant.setId(99L);
		Mockito.when(projectParticipantRepository.findById(99L)).thenReturn(Optional.of(participant));
		assertThat(projectParticipantService.getOne(99L).get()).isEqualTo(participant);
	}
	
	@Test
	public void shouldReturnAllForuUserIfIsParticipant() {
		User user = new User();
		Project project = new Project();
		ProjectParticipant participant = new ProjectParticipant();
		participant.setProject(project);
		participant.setParticipant(user);
		List<ProjectParticipant> participants = new ArrayList<>();
		participants.add(participant);
		Mockito.when(projectParticipantRepository.getAllForUser(user.getEmail())).thenReturn(participants);
		assertThat(projectParticipantService.getAllForUser(user.getEmail()))
		.isNotEmpty()
		.contains(participant)
		.hasSize(1);
	}
	
	@Test
	public void shouldReturnAllForProjectIfHasParticipants() {
		Project project = new Project();
		ProjectParticipant participant = new ProjectParticipant();
		participant.setProject(project);
		List<ProjectParticipant> participants = new ArrayList<>();
		participants.add(participant);
		Mockito.when(projectParticipantRepository.getAllForProject(project.getId())).thenReturn(participants);
		assertThat(projectParticipantService.getAllForProject(project.getId()))
		.isNotEmpty()
		.contains(participant)
		.hasSize(1);
	}
	
	@Test
	public void shouldReturnOneRoleIfHasOneParticipant() {
		User user = new User();
		Project project = new Project();
		ProjectParticipant participant = new ProjectParticipant();
		participant.setProject(project);
		participant.setParticipant(user);
		participant.setRole("TEST");
		List<ProjectParticipant> participants = new ArrayList<>();
		participants.add(participant);
		Mockito.when(projectParticipantRepository.getParticipantEntriesForProject(project.getId(), user.getEmail())).thenReturn(participants);
		assertThat(projectParticipantService.getUserRoles(user.getEmail(), project.getId()))
		.isNotEmpty()
		.hasSize(1);
	}
	
	@Test
	public void shouldReturnTwoRolesIfOneParticipantHasTwoRolesAndThereAreMoreParticipants() {
		User user1 = new User();
		user1.setEmail("test1");
		User user2 = new User();
		user2.setEmail("test2");
		Project project = new Project();
		ProjectParticipant participant1 = new ProjectParticipant();
		participant1.setProject(project);
		participant1.setParticipant(user1);
		participant1.setRole("TEST1");
		ProjectParticipant participant2 = new ProjectParticipant();
		participant2.setProject(project);
		participant2.setParticipant(user1);
		participant2.setRole("TEST2");
		ProjectParticipant participant3 = new ProjectParticipant();
		participant3.setProject(project);
		participant3.setParticipant(user2);
		participant3.setRole("TEST3");
		List<ProjectParticipant> participants = new ArrayList<>();
		participants.add(participant1);
		participants.add(participant1);
		Mockito.when(projectParticipantRepository.getParticipantEntriesForProject(project.getId(), user1.getEmail())).thenReturn(participants);
		assertThat(projectParticipantService.getUserRoles(user1.getEmail(), project.getId()))
		.isNotEmpty()
		.hasSize(2);
	}

}
