package com.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.unittests.exceptions.UserException;
import com.unittests.models.User;
import com.unittests.repositories.UserRepository;
import com.unittests.services.UserService;

@SpringBootTest
class UnitTestsApplicationTests {

	@Mock
	private UserRepository userRepositoryMock;

	@InjectMocks
	private UserService userServiceMock;

	@Test
	void testFindByUserName() {
		String username = "JonasDias";
		String password = "12345678";

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		when(userRepositoryMock.findByUsername(username)).thenReturn(Optional.of(user));
		User finderUser = userServiceMock.findByUsername(username);

		assertNotNull(finderUser);
		assertEquals(username, finderUser.getUsername());
	}

	@Test
	void testSaveUser() {
		User user = new User();
		user.setUsername("JonasDias");
		user.setPassword("12345678");

		when(userRepositoryMock.save(user)).thenReturn(user);
		User savedUser = userServiceMock.save(user);

		verify(userRepositoryMock, times(1)).save(user);
		assertEquals("JonasDias", savedUser.getUsername());
		assertEquals("12345678", savedUser.getPassword());
	}

	@Test
	void testUpdateUserWhenUserAlreadyExists() {
		User savedUser = new User();
		savedUser.setId(1L);
		savedUser.setUsername("Jonas");
		savedUser.setPassword("1234");

		User updatedUser = new User();
		updatedUser.setId(1L);
		updatedUser.setUsername("Jonas Dias");
		updatedUser.setPassword("12345678");

		when(userRepositoryMock.save(any(User.class))).thenReturn(updatedUser);

		User result = userServiceMock.update(updatedUser);

		verify(userRepositoryMock, times(1)).save(updatedUser);

		assertEquals("Jonas Dias", result.getUsername());
		assertEquals("12345678", result.getPassword());
	}

	@Test
	void testDeleteUserWhenUserExists() {
		Long userId = 1L;

		when(userRepositoryMock.existsById(userId)).thenReturn(true);
		userServiceMock.delete(userId);

		verify(userRepositoryMock, times(1)).deleteById(userId);
	}

	@Test
	void testDeleteUserWhenUserDoesNotExist() {
		Long userId = 1L;

		when(userRepositoryMock.existsById(userId)).thenReturn(false);

		UserException exception = assertThrows(UserException.class, () -> userServiceMock.delete(userId));

		assertEquals("User not found", exception.getMessage());

		verify(userRepositoryMock, never()).deleteById(anyLong());
	}

}
