package juchoi.study.practice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import juchoi.study.practice.repogitory.entity.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test springboottest")
class PracticeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Display Name test")
	void testSpringBootTest(@Autowired TestRestTemplate testRestTemplate) {
		ResponseEntity<List<User>> response = testRestTemplate.exchange(
				"/users",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<User>>() {
				});
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

}
