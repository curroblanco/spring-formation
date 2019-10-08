package es.urjc.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UrjcFinalExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrjcFinalExerciseApplication.class, args);
	}

}
