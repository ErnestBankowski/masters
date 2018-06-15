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
import com.stratum.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {
		
	@InjectMocks
	ProjectServiceImpl projectService;
	
	@Mock
	ProjectRepository projectRepository;
	
	@Mock
	ProjectParticipantRepository projectParticipantRepository;
	
	 @Before
	 public void initMocks(){
	     MockitoAnnotations.initMocks(this);
	 }
	 
	@Test
	public void shouldReturnEmptyListIfNothingSaved() {
		Mockito.when(projectRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(projectService.list()).isEmpty();
	}
		
	@Test
	public void shouldReturnPopulatedListIfSaved() {
		Project project = new Project();
		project.setId(99L);
		Mockito.when(projectRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(projectService.list()).isEmpty();
		projectService.save(project);
		List<Project> projectList = new ArrayList<>();
		projectList.add(project);
		Mockito.when(projectRepository.findAll()).thenReturn(projectList);
		assertThat(projectService.list()).isNotEmpty();
	}
	
	@Test
	public void shouldReturnProjectIfSavedWithValidId() {
		Project project = new Project();
		project.setId(99L);
		Mockito.when(projectRepository.findById(99L)).thenReturn(Optional.of(project));
		assertThat(projectService.getOne(99L).get()).isEqualTo(project);	
	}
	
	@Test
	public void shouldGetProjectForUserIfSavedAsCreator() {
		Project project = new Project();
		project.setId(99L);
		User user = new User();
		user.setEmail("test");
		project.setProjectOwnerId(user);
		projectService.save(project);
		ProjectParticipant participant = new ProjectParticipant();
		participant.setParticipant(user);
		participant.setProject(project);
		List<ProjectParticipant> projectParticipantList = new ArrayList<>();
		projectParticipantList.add(participant);
		Mockito.when(projectParticipantRepository.getAllForUser(user.getEmail())).thenReturn(projectParticipantList);
		assertThat(projectParticipantRepository.getAllForUser(user.getEmail())).isNotEmpty();
	}
	

	
}
