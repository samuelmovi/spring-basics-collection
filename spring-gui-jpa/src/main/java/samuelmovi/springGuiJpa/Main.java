package samuelmovi.springGuiJpa;

import samuelmovi.springGuiJpa.repo.OperatorRepository;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import samuelmovi.springGuiJpa.controller.Controller;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Controller controller = (Controller) context.getBean("controller");
        OperatorRepository operatorRepository = (OperatorRepository) context.getBean("operatorRepository");
        controller.setOperatorRepository(operatorRepository);
        controller.init();
        context.registerShutdownHook();
    }
}
