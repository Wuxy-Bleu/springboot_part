package icu.bleuweb.controller;

import icu.bleuweb.EMailApplication;
import icu.bleuweb.mail.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MailController {

    @Autowired
    EmailService emailService;

    @GetMapping("/send")
    public void sendMail() {
        log.info("发送邮件");
        emailService.sendMail("wgrantbleuthefall@gmail.com",
                "test", "asfjdlfalskdfj");
    }
}
