package com.sun;

import com.sun.pojo.User;
import com.sun.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShrioSpringbootApplicationTests {

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        User user = userService.queryUserByName("张三");
        System.out.println(user);
    }

}
