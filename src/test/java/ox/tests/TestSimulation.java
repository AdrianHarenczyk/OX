package ox.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSimulation {
    @BeforeMethod
    private static void init() {
        String[] args = {"PL"};

    }
    @DataProvider(name = "name")
    Object[][] validStrokeValues() {
        return new Object[][]{
                {"default\n1\n"},
                {"9"},
                {"3"},
                {"49"},
                {"7"},
                {"32"},
                {"24"},
                {"5"},
                {"30"},
                {"22"},
                {"24"},
                {"17"}
        };
    }

    @Test
    public static void testName() {
//        ApplicationRunner.run(args,);
    }
}
