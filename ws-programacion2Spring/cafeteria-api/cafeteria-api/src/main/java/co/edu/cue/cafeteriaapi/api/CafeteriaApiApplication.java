package co.edu.cue.cafeteriaapi.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.cue.cafeteria.api.controllers.ClienteRestController;

@SpringBootApplication
public class CafeteriaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafeteriaApiApplication.class, args);
		
	}

}
