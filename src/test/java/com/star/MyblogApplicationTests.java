package com.star;

import com.star.dao.MessageDao;
import com.star.entity.Message;
import com.star.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailMessage;

@SpringBootTest
class MyblogApplicationTests {

    @Autowired
    private MessageDao messageDao;

    @Test
    void contextLoads() {
        Message message = messageDao.findById(101L);
        System.out.println(message);
    }

}
