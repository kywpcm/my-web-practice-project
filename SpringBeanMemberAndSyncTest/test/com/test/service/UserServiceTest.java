package com.test.service;

import com.test.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 1. 스프링 컨테이너 annotaion 기동 방식
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
//        "classpath:service-servlet.xml",
        "classpath:applicationContext.xml"
})
//@WebAppConfiguration // web.xml : servlet mapping, DispatcherServlet, service-servlet.xml, handlerMapping, ViewResolver, Controller
public class UserServiceTest {

    private static final int THREAD_POOL_SIZE = 10; // thread pool size

    @Autowired
    private UserService userService;
//    private static ApplicationContext container;
//    private final UserService userService = (UserService) container.getBean("userService");

    @BeforeClass
    public static void launchSpring() {
        // 2. 스프링 컨테이너 클래스 기동 방식
//        String[] inputXMLs = {"applicationContext.xml"};
//        container = new ClassPathXmlApplicationContext(inputXMLs);
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

        for (int i = 0; i < 1000; i++) {
            // 1 request
            es.execute(new Runnable() {
                @Override
                public void run() {
                    // case 1: user 생성후, 해당 user를 바로 가져오고 싶은 경우
                    // 오직 하나의 스레드만이 pass 할 수 있게 클래스 원형 오브젝트로 lock을 건다.
                    // multi thread 환경에서 sync를 보장받고 싶은 부분이 있을 때
//                    synchronized (Runnable.class) {
                        userService.createUser(Thread.currentThread().toString(), "User[" + Thread.currentThread().getName() + "]");
                        System.out.println(Thread.currentThread() + " ##### get user name : " + userService.getUser().getName());
//                    }
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