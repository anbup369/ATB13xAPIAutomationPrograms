package com.test.practice.ex_05_TestNGExamples;

import org.testng.annotations.Test;

public class APITestingSoftVSHARDAlwaysRunTest {

    @Test
    public void login() { /* might fail */ }

    @Test(dependsOnMethods = "login")         // Hard dependency
    public void placeOrder() { /* runs only if login passed */ }

    @Test(dependsOnMethods = "login",         // Soft dependency
            alwaysRun = true)
    public void closeBrowser() { /* runs even if login failed */ }
}
