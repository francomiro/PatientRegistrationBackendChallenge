package light.it.Patient_Registration_Backend_Challenge.config;

import light.it.Patient_Registration_Backend_Challenge.service.EmailService;
import light.it.Patient_Registration_Backend_Challenge.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class NotificationConfig {

    @Autowired
    private EmailService emailService;

    //TODO:
    //@Autowired
    //private SMSService smsService;

    @Bean
    public List<NotificationService> notificationServices() {
        //All the notification methods
        return Arrays.asList(emailService);
    }
}
