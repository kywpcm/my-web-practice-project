package com.test.service;

import com.test.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    // 클래스의 멤버 변수는 멀티 스레드에서 접근해도 항상 안전해야 한다. (thread safe)
    // 이 규칙만 지켜도 많은 잠재적 문제를 예방할 수 있다.
//    private User user = new User();
    private User user;
    // DB 자료구조
    public static final List<User> USER_LIST = new ArrayList<>();

    public void createUser(String id, String name) {
        // user 생성 후, DB에 저장하는 트랜잭션에서
        // 멤버 변수인 user에 대해 thread safety를 보장 받으려면 적절한 sync 처리 등의 방법을 이용
        synchronized (this) {
            user = new User();

            user.setId(id);
            user.setName(name);

            // dao -> DB 저장 가정
            USER_LIST.add(user);
            System.out.println(Thread.currentThread() + " ##### insert user...");
        }
    }

    public User getUser() {
        return user;
    }

}
