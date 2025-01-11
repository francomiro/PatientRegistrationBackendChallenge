package light.it.Patient_Registration_Backend_Challenge.repository;

import light.it.Patient_Registration_Backend_Challenge.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
