package light.it.Patient_Registration_Backend_Challenge.service;

import light.it.Patient_Registration_Backend_Challenge.model.Patient;

public interface NotificationService {
    void sendNewPatientNotification(Patient patient);
}
