package com.api.sweetshop;

import static junit.framework.TestCase.assertEquals;

import java.util.List;

import com.api.sweetshop.exceptions.EmailException;
import com.api.sweetshop.model.Email;
import com.api.sweetshop.model.Gender;
import com.api.sweetshop.model.UserProfile;
import com.api.sweetshop.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    private static final int NO_EMAILS = 50;
    private UserProfile user;
    private UserProfile to;

    @BeforeEach
    public void setup() {
        user = new UserProfile("Anna", null, Gender.FEMALE);
        to = new UserProfile("Emma", null, Gender.FEMALE);
    }

    @Test
    public void testSendMail() throws InterruptedException {

        EmailService emailService = new EmailService();
        for (int i = 0; i < NO_EMAILS; i++) {
            try {
                emailService.sendNotificationEmail(
                    new Email(user, user, List.of(to), "New client has been added",
                        "New client " + user + " has been added in system")
                );
            } catch (EmailException e) {
                e.printStackTrace();
            }
            Thread.sleep(100);
        }

        assertEquals(NO_EMAILS, emailService.getSentEmails());
    }
}
