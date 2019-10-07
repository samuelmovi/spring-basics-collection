package samuelmovi.springCliJpa;

import samuelmovi.springCliJpa.controller.Controller;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Controller app = (Controller) context.getBean("controller");
        app.run();
        context.registerShutdownHook();
    }
}
