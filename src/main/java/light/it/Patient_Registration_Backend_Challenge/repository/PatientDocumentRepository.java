package light.it.Patient_Registration_Backend_Challenge.repository;

import light.it.Patient_Registration_Backend_Challenge.model.PatientDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientDocumentRepository extends JpaRepository<PatientDocument, Long> {

    Optional<PatientDocument> findByPatientId(Long patientId);
}
