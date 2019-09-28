package samuelmovi.springCliJdbc;

import samuelmovi.springCliJdbc.app.App;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		App app = (App) context.getBean("app");
		app.run();
		context.registerShutdownHook();
	}
}
