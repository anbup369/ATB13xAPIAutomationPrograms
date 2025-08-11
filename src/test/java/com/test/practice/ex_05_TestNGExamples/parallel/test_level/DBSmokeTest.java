package com.test.practice.ex_05_TestNGExamples.parallel.test_level;

import org.testng.annotations.Test;

public class DBSmokeTest {
    @Test
    public void test_DB_Smoke(){
        System.out.println(Thread.currentThread().getId());
    }
}
