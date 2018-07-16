package com.test.service;

import com.test.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("Convert2Lambda")
public class UserServiceTest {

    private static final int THREAD_POOL_SIZE = 10; // thread pool size
    private static ApplicationContext container;
    private final UserService userService = (UserService) container.getBean("userService");

    @BeforeClass
    public static void launchSpring() {
        container = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public void getBean() {
//        userService = (UserService) container.getBean("userService");
    }

    @Test
    public void testCreateUser() {
        userService.createUser("kywpcm", "권영우");
    }

    @Test
    public void testCreateUserMultiThread() {
        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            int finalI = i;
            // 1 request
            es.execute(new Runnable() {
                @Override
                public void run() {
                    // user 생성후, 해당 user를 바로 가져오고 싶은 경우
                    // userService lock을 걸어 sync를 맞춘다.
                    synchronized (userService) {
                        userService.createUser(Thread.currentThread().toString(), "User" + (finalI + 1));
                        System.out.println(Thread.currentThread() + " ##### get user " + userService.getUser().getId());
                    }
                }
            });
        }

        es.shutdown();
        try {
            es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void printUserList() {
        for (User m : UserService.USER_LIST) {
            System.out.println("id : " + m.getId());
            System.out.println("name : " + m.getName());
        }
    }

}