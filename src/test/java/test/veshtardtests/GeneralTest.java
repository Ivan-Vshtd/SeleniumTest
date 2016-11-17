package test.veshtardtests;

import org.apache.log4j.Logger;
import org.testng.annotations.*;

public class GeneralTest {
    private static final Logger log = Logger.getLogger(GeneralTest.class);
    @Test
    public  void testTask1() throws Exception {
        log.info("Run a general test");
        Task1 task1 = new Task1();
        task1.setUp();
        task1.testFF();
        task1.tearDown();
        task1.testChrome();
        task1.tearDown();
    }
    @Test
    public  void testTask2() throws Exception {

        Task2 task2 = new Task2();
        task2.setUp();
        task2.testFF();
        task2.tearDown();
        task2.testChrome();
        task2.tearDown();

    }
    @Test
    public  void testTask3() throws Exception {
        Task3 task3 = new Task3();
        task3.setUp();
        task3.testFF();
        task3.tearDown();
        task3.testChrome();
        task3.tearDown();
        log.info("Finish a general test");
    }
}
