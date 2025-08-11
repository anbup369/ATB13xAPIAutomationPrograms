package com.test.practice.ex_05_TestNGExamples;

import org.testng.annotations.*;

public class APITesting023AllAnnotations {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("1. @BeforeSuite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("2. @BeforeTest");
    }

    @BeforeGroups("group1")
    public void beforeGroups() {
        System.out.println("3. @BeforeGroups (group1)");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("4. @BeforeClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("5. @BeforeMethod");
    }

    @Test(groups = "group1")
    public void testMethod1() {
        System.out.println("6. @Test - testMethod1");
    }

    @Test(groups = "group1")
    public void testMethod2() {
        System.out.println("6. @Test - testMethod2");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("7. @AfterMethod");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("8. @AfterClass");
    }

    @AfterGroups("group1")
    public void afterGroups() {
        System.out.println("9. @AfterGroups (group1)");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("10. @AfterTest");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("11. @AfterSuite");
    }

    @DataProvider(name = "sampleData")
    public Object[][] dataProviderMethod() {
        return new Object[][] {
                {"Data1"}, {"Data2"}
        };
    }

    @Test(dataProvider = "sampleData")
    public void dataDrivenTest(String data) {
        System.out.println("6. @Test - dataDrivenTest with: " + data);
    }

    @Parameters({"browser"})
    @Test
    public void parameterizedTest(@Optional("Chrome") String browser) {
        System.out.println("6. @Test - parameterizedTest with browser: " + browser);

    }
}