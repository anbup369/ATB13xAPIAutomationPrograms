package com.test.practice.ex_05_TestNGExamples.parallel.test_level;

import org.testng.annotations.Test;

public class UISmokeTest {
    @Test
    public void test_UI_Smoke(){
        System.out.println(Thread.currentThread().getId());
    }
}
