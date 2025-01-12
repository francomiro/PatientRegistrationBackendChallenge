package light.it.Patient_Registration_Backend_Challenge.service;

import light.it.Patient_Registration_Backend_Challenge.dto.PatientDTO;
import light.it.Patient_Registration_Backend_Challenge.model.Patient;
import light.it.Patient_Registration_Backend_Challenge.model.PatientDocument;
import light.it.Patient_Registration_Backend_Challenge.repository.PatientDocumentRepository;
import light.it.Patient_Registration_Backend_Challenge.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientDocumentRepository patientDocumentRepository;

    @Autowired
    private EmailService emailService;

    private final List<NotificationService> notificationServices;

    public PatientService(List<NotificationService> notificationServices) {
        this.notificationServices = notificationServices;
    }

    @Transactional
    public void registerPatient(PatientDTO patientDto) throws IOException {
        System.out.println("Creating new Patient.");
        // Convert file to bytes
        MultipartFile document = patientDto.getDocumentPhoto();
        byte[] content = document.getBytes();

        // Creating Patient Entity
        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        patient.setEmail(patientDto.getEmail());
        patient.setPhone(patientDto.getPhone());
        patientRepository.save(patient);

        // Creating PatientDocument Entity
        PatientDocument documentEntity = new PatientDocument();
        documentEntity.setFileName(document.getOriginalFilename());
        documentEntity.setFileExtension(getFileExtension(document.getOriginalFilename()));
        documentEntity.setFileContent(content);
        documentEntity.setPatient(patient);
        patientDocumentRepository.save(documentEntity);

        System.out.println("Patient created successfully.");
        
        // Sending notification email (async)
        for (NotificationService notificationService : notificationServices) {
            notificationService.sendNewPatientNotification(patient);
        }

    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
