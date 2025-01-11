package light.it.Patient_Registration_Backend_Challenge.controller;

import jakarta.validation.Valid;
import light.it.Patient_Registration_Backend_Challenge.dto.PatientDTO;
import light.it.Patient_Registration_Backend_Challenge.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/")
    public ResponseEntity<String> registerPatient(@Valid PatientDTO patientDto) {
        try {
            patientService.registerPatient(patientDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Patient registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
