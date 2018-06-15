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

import com.stratum.model.User;
import com.stratum.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserRepository userRepository;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnEmptyListIfNothingSaved() {
		Mockito.when(userRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(userService.list()).isEmpty();
	}

	@Test
	public void shouldReturnPopulatedListIfSaved() {
		User user = new User();
		user.setEmail("test");
		Mockito.when(userRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertThat(userService.list()).isEmpty();
		userService.save(user);
		List<User> userList = new ArrayList<>();
		userList.add(user);
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		assertThat(userService.list()).isNotEmpty();
	}

	@Test
	public void shouldReturnUserIfSavedWithValidId() {
		User user = new User();
		user.setEmail("test");
		Mockito.when(userRepository.findById("test")).thenReturn(Optional.of(user));
		assertThat(userService.getOne("test").get()).isEqualTo(user);
	}

}
