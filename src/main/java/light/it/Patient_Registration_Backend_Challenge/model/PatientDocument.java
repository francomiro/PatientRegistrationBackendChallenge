package light.it.Patient_Registration_Backend_Challenge.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PatientDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileExtension;

    @Lob
    private byte[] fileContent;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
