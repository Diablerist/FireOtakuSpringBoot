package com.kitsuapi.firedev;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.kitsuapi.firedev.service.ApiRequests;

@SpringBootTest
class FiredevApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(ApiRequests.episodesByAnime(12, 0).getData());
    }
}
