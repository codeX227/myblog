package com.star.aspect;

import com.star.dao.MessageDao;
import com.star.entity.Message;
import com.star.service.MailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 邮件切面
 * @Author Goodenough
 * @Date 2021/2/13 16:55
 */
@Aspect
@Component
public class MailAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String mailSubject = "某菜狗の博客";
    private final String mailContent = "您在某菜狗の博客有新的回复，请前往查看: https://xushuanglong.top/";

    @Autowired
    private MailService mailService;
    @Autowired
    private MessageDao messageDao;

//    @Pointcut("execution(* com.star.controller.*.*(..))")
    @Pointcut("execution(public int com.star.service.impl.MessageServiceImpl.saveMessage(com.star.entity.Message))")
    public void messageMail() {

    }

    @Before("messageMail()")
    public void doMessageBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Message message = (Message) args[0];
        Message parentMessage = messageDao.findById(message.getParentMessageId());
        mailService.sendSimpleMail(parentMessage.getEmail(),mailSubject,mailContent);
        logger.info("mailTo : {}", parentMessage.getEmail());
    }

//    @Pointcut("execution(public int com.star.service.impl.MessageServiceImpl.saveMessage(com.star.entity.Message))")
//    public void commentMail() {
//
//    }
//
//    @Before("commentMail()")
//    public void doCommentBefore(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        Message message = (Message) args[0];
//        Message parentMessage = messageDao.findById(message.getParentMessageId());
//        mailService.sendSimpleMail(parentMessage.getEmail(),mailSubject,mailContent);
//        logger.info("mailTo : {}", parentMessage.getEmail());
//    }
}
