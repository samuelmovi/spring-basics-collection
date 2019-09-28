package samuelmovi.bootGuiJpa;

import samuelmovi.bootGuiJpa.controller.Controller;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
		context.registerShutdownHook();
	}

}
