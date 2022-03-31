package org.zerock.partFive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PartFiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartFiveApplication.class, args);
	}

}
