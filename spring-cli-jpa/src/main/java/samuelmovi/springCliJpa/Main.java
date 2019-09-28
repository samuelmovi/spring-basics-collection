package samuelmovi.springCliJpa;

import samuelmovi.springCliJpa.app.App;
import samuelmovi.springCliJpa.model.OperatorRepository;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        OperatorRepository operatorRepository = (OperatorRepository) context.getBean("operatorRepository");
        App app = (App) context.getBean("app");
        app.run(operatorRepository);
        context.registerShutdownHook();
    }
}
