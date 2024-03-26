package com.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
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

		when(userRepositoryMock.findByUsername(username)).thenReturn(Optional.of(new User(username)));

		User finderUser = userServiceMock.findByUsername("JonasDias");

		assertNotNull(finderUser);
		assertEquals(username, finderUser.getUsername());
	}

}
