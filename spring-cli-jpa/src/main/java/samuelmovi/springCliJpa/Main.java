package samuelmovi.springCliJpa;

import samuelmovi.springCliJpa.app.App;
import samuelmovi.springCliJpa.controller.Controller;
import samuelmovi.springCliJpa.model.OperatorRepository;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        // OperatorRepository operatorRepository = (OperatorRepository) context.getBean("operatorRepository");
        Controller app = (Controller) context.getBean("controller");
        app.run();
        context.registerShutdownHook();
    }
}
