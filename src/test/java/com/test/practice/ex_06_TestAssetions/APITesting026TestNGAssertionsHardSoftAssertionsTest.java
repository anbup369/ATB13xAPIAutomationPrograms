package com.test.practice.ex_06_TestAssetions;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APITesting026TestNGAssertionsHardSoftAssertionsTest {


    @Test(priority = 2)
    public void test_hardAssertExample() {
        System.out.println("Start of the program");
        Assert.assertEquals("pramod", "Pramod");
        System.out.println("End of the program");
    }


    @Test(priority = 1)
    public void test_softAssertExample() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("Start of the program");
        softAssert.assertEquals("pramod", "Pramod");
        System.out.println("End of program");
        softAssert.assertAll();
    }


}
