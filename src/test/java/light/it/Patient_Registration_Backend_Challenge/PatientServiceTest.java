package light.it.Patient_Registration_Backend_Challenge;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import light.it.Patient_Registration_Backend_Challenge.dto.PatientDTO;
import light.it.Patient_Registration_Backend_Challenge.model.Patient;
import light.it.Patient_Registration_Backend_Challenge.repository.PatientDocumentRepository;
import light.it.Patient_Registration_Backend_Challenge.repository.PatientRepository;
import light.it.Patient_Registration_Backend_Challenge.service.EmailService;
import light.it.Patient_Registration_Backend_Challenge.service.NotificationService;
import light.it.Patient_Registration_Backend_Challenge.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.mock.web.MockMultipartFile;

import java.util.Arrays;
import java.util.List;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientDocumentRepository patientDocumentRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private NotificationService emailNotificationService;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<NotificationService> notificationServices = Arrays.asList(emailNotificationService);
        patientService = new PatientService(patientRepository, patientDocumentRepository, emailService, notificationServices);
    }

    @Test
    void testRegisterPatient() throws Exception {

        String name = "Juan Martinez";
        String email = "juan.martinez@gmail.com";
        String phone = "1164519791";
        MockMultipartFile documentFile = new MockMultipartFile("documentPhoto", "document.jpg", "image/jpeg", "test content".getBytes());

        PatientDTO patientDto = new PatientDTO();
        patientDto.setEmail(email);
        patientDto.setName(name);
        patientDto.setPhone(phone);
        patientDto.setDocumentPhoto(documentFile);

        Patient patient = new Patient();
        patient.setName(name);
        patient.setEmail(email);
        patient.setPhone(phone);

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        when(patientDocumentRepository.save(any())).thenReturn(null);

        patientService.registerPatient(patientDto);

        verify(patientRepository, times(1)).save(any(Patient.class));
        verify(patientDocumentRepository, times(1)).save(any());
    }

    @Test
    void testRegisterPatient_InvalidDocument() throws Exception {

        PatientDTO patientDto = new PatientDTO();

        patientDto.setName("juancito perez");
        patientDto.setEmail("juancitoperez@gmail.com");
        patientDto.setPhone("1164519791");
        patientDto.setDocumentPhoto(null);


        assertThrows(NullPointerException.class, () -> patientService.registerPatient(patientDto));
    }

    @Test
    void testRegisterPatient_ValidDocument() throws Exception {

        String name = "juancito perez";
        String email = "juancito perez@gmail";
        String phone = "1164519791";
        MockMultipartFile documentFile = new MockMultipartFile("documentPhoto", "document.jpg", "image/jpeg", "content test".getBytes());

        PatientDTO patientDto = new PatientDTO();
        patientDto.setEmail(email);
        patientDto.setName(name);
        patientDto.setPhone(phone);
        patientDto.setDocumentPhoto(documentFile);

        Patient patient = new Patient();
        patient.setName(name);
        patient.setEmail(email);
        patient.setPhone(phone);

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        when(patientDocumentRepository.save(any())).thenReturn(null);

        patientService.registerPatient(patientDto);

        verify(patientRepository, times(1)).save(any(Patient.class));
        verify(patientDocumentRepository, times(1)).save(any());
    }
}
