package com.willbe.giftapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ActiveProfiles("test")
public class BaseTestJava {
    @Test
    public void test() {
        System.out.println("load");
    }
}
