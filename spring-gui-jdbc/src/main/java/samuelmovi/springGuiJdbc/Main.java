package samuelmovi.springGuiJdbc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import samuelmovi.springGuiJdbc.controller.Controller;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Controller controller = (Controller) context.getBean("controller");
        controller.init();
        context.registerShutdownHook();
    }
}
