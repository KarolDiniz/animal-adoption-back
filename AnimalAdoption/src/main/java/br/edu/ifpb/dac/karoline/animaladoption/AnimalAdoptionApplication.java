package br.edu.ifpb.dac.karoline.animaladoption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AnimalAdoptionApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AnimalAdoptionApplication.class, args);
		context.close();
	}
}