package light.it.Patient_Registration_Backend_Challenge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class PatientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testRegisterPatientIntegration_Success() throws Exception {
        MockMultipartFile documentPhoto = createDocumentPhoto();

        mockMvc.perform(multipart("/patients/")
                        .file(documentPhoto)
                        .param("name", "Juan Martinez")
                        .param("email", "juan.martinez@gmail.com")
                        .param("phone", "1164519791")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegisterPatientIntegration_BadRequest() throws Exception {
        MockMultipartFile documentPhoto = createDocumentPhoto();

        mockMvc.perform(multipart("/patients/")
                        .file(documentPhoto)
                        .param("name", "")
                        .param("email", "juan.martinez@gmail.com")
                        .param("phone", "1164519791")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is required"));
    }

    @Test
    public void testRegisterPatientIntegration_BadRequest_MultipleErrors() throws Exception {
        MockMultipartFile documentPhoto = createDocumentPhoto();

        mockMvc.perform(multipart("/patients/")
                        .file(documentPhoto)
                        .param("name", "")
                        .param("email", "invalid-email")
                        .param("phone", "12345")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is required"))
                .andExpect(jsonPath("$.email").value("Invalid email format"))
                .andExpect(jsonPath("$.phone").value("Invalid phone number format"));
    }

    private MockMultipartFile createDocumentPhoto() {
        return new MockMultipartFile(
                "documentPhoto",
                "document.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "contenido-del-archivo".getBytes()
        );
    }

}
