package com.stratum.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.stratum.exception.ResourceNotFoundException;
import com.stratum.repository.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	private UserService userService;
	private UserRepository userRepositoryMock;
	
	@Before
	public void setUp() {
		userService = new UserServiceImpl();
		userRepositoryMock = mock(UserRepository.class);
	}
	
	/*@Test(expected = ResourceNotFoundException.class)
	public void shouldThrowExceptionWhenNoUserWithRequestedIdFound() {
		//when
		when(userRepositoryMock.findById(10L)).thenReturn(Optional.empty());
		//then
		userService.getUserById(10L);
	}
	
	@Test
	public void shouldNotSaveUserWithEmptyId() {
		//given
		User user = new User();
		user.setRoleId(1);
		user.setUserDataId(1);
		//when
		
		//then
	}
	
	@Test
	public void shouldNotSaveUserWithNoUserDetailsDefined() {
		//given
		
		//when
		
		//then
	}
	
	@Test
	public void shouldNotSaveUserWithNoRoleDefined() {
		//given
		
		//when
		
		//then
	}
	
	@Test
	public void shouldNotSaveUserIfIdAlreadyPresentInDatabase() {
		//given
		
		//when
		
		//then
	}*/
}
