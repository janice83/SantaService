package com.indexzero.santaService;

import static org.assertj.core.api.Assertions.assertThat;

import com.indexzero.santaservice.controller.DefaultController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SantaServiceApplicationTests {

	@Autowired
	private DefaultController defaultController;

	@Test
	void contextLoads() {
		assertThat(defaultController).isNotNull();
	}

}
