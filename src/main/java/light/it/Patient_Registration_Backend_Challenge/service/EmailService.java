package light.it.Patient_Registration_Backend_Challenge.service;

import light.it.Patient_Registration_Backend_Challenge.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailService implements NotificationService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendNewPatientNotification(Patient patient) {
        CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Sending email...");
                var message = mailSender.createMimeMessage();
                var helper = new MimeMessageHelper(message, true);
                helper.setTo(patient.getEmail());
                helper.setSubject("Registration was successfully");
                helper.setText("Thank you for register with us!");
                mailSender.send(message);
                System.out.println("Email sent successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
