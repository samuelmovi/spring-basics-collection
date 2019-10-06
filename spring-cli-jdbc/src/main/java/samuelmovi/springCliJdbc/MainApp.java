package samuelmovi.springCliJdbc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import samuelmovi.springCliJdbc.controller.Controller;

public class MainApp {

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Controller app = (Controller) context.getBean("controller");
		app.run();
		context.registerShutdownHook();
	}
}
