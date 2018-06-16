package com.stratum.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.stratum.StratumApplication;
import com.stratum.model.Project;
import com.stratum.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc()
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;
	
	@Mock
	private ProjectService projectService;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	    
	private static final String PROJECT_API = "/project";
	private static final String SPRINT_API = "/sprint";
	private static final String FUNCTIONALITY_API = "/functionality";
	private static final String USECASE_API = "/usecase";
	private static final String SPECIFICATION_API = "/specification";
	
	@Test
	public void shouldBlockProjectController() throws Exception {
		this.mockMvc.perform(get(PROJECT_API))
			.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void shouldBlockSprintController() throws Exception {
		this.mockMvc.perform(get(SPRINT_API))
			.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void shouldBlockFunctionalityController() throws Exception {
		this.mockMvc.perform(get(FUNCTIONALITY_API))
			.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void shouldBlockUsecaseController() throws Exception {
		this.mockMvc.perform(get(USECASE_API))
			.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void shouldBlockSpecificationController() throws Exception {
		this.mockMvc.perform(get(SPECIFICATION_API))
			.andExpect(status().isUnauthorized());
	}
}
