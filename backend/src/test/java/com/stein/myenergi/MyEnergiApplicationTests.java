package com.stein.myenergi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MyEnergiApplicationTests {

	// TODO add wiremock for calls to myenergihub for testing environment
	@Test
	void contextLoads() {
	}

}
