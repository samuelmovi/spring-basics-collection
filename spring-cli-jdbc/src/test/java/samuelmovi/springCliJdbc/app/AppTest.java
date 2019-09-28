package samuelmovi.springCliJdbc.app;

import org.junit.Test;



public class AppTest {

    @Test
    public void testAllEmployees(){
        // ADD X EMPLOYEES TO DATABASE

        // QUERY ALL THROUGH APP INSTANCE

        // ASSERT NUMBER OF ITEMS RECEIVED IS X

    }

    @Test
    public void testActiveEmployees(){
        // ADD X EMPLOYEES TO DATABASE

        // SET Y AS INACTIVE (FOR Y<X)

        // QUERY ALL THROUGH APP INSTANCE

        // ASSERT NUMBER OF ITEMS RECEIVED IS Y

    }

    @Test
    public void testNewEmployee(){
        // ADD X EMPLOYEES TO DATABASE

        // ADD NEW EMPLOYEE THROUGH APP INSTANCE

        // QUERY ALL

        // ASSERT NUMBER OF ITEMS RECEIVED IS X+1

    }

    @Test
    public void testDeleteEmployee(){
        // ADD X EMPLOYEES TO DATABASE

        // DELETE 1 EMPLOYEE THROUGH APP INSTANCE

        // QUERY ALL

        // ASSERT NUMBER OF ITEMS RECEIVED IS X-1

    }

    @Test
    public void testSetInactive(){
        // ADD X EMPLOYEES TO DATABASE

        // SET Y AS INACTIVE (FOR Y<X) THROUGH APP INSTANCE

        // QUERY ALL ACTIVE

        // ASSERT NUMBER OF ITEMS RECEIVED IS X-Y

    }

    @Test
    public void testRun(){

    }
}
