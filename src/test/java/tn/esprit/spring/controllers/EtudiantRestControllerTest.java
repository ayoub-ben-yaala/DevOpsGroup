package tn.esprit.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.tpfoyer.control.EtudiantRestController;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(EtudiantRestController.class)
public class EtudiantRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IEtudiantService etudiantService;

    @InjectMocks
    private EtudiantRestController etudiantRestController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetEtudiants() throws Exception {
        Etudiant etudiant1 = new Etudiant( 1L,"Smith","John", 12345678,"aa");
        Etudiant etudiant2 = new Etudiant(2L, "Jane", "Smith", 87654321,"aa");
        List<Etudiant> etudiants = Arrays.asList(etudiant1, etudiant2);

        when(etudiantService.retrieveAllEtudiants()).thenReturn(etudiants);

        mockMvc.perform(get("/etudiant/retrieve-all-etudiants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nom").value("John"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nom").value("Jane"));
    }

    @Test
    public void testRetrieveEtudiantParCin() throws Exception {
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", "12345678");

        when(etudiantService.recupererEtudiantParCin(12345678L)).thenReturn(etudiant);

        mockMvc.perform(get("/etudiant/retrieve-etudiant-cin/12345678"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nom").value("John"));
    }

    @Test
    public void testRetrieveEtudiant() throws Exception {
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", 12345678,"");

        when(etudiantService.retrieveEtudiant(1L)).thenReturn(etudiant);

        mockMvc.perform(get("/etudiant/retrieve-etudiant/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nom").value("John"));
    }

    @Test
    public void testAddEtudiant() throws Exception {
        Etudiant etudiant = new Etudiant(null, "John", "Doe", "12345678");
        Etudiant savedEtudiant = new Etudiant(1L, "John", "Doe", "12345678");

        when(etudiantService.addEtudiant(any(Etudiant.class))).thenReturn(savedEtudiant);

        mockMvc.perform(post("/etudiant/add-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(etudiant)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nom").value("John"));
    }

    @Test
    public void testRemoveEtudiant() throws Exception {
        doNothing().when(etudiantService).removeEtudiant(1L);

        mockMvc.perform(delete("/etudiant/remove-etudiant/1"))
                .andExpect(status().isOk());

        verify(etudiantService, times(1)).removeEtudiant(1L);
    }

    @Test
    public void testModifyEtudiant() throws Exception {
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", "12345678");
        Etudiant updatedEtudiant = new Etudiant(1L, "John", "Smith", "12345678");

        when(etudiantService.modifyEtudiant(any(Etudiant.class))).thenReturn(updatedEtudiant);

        mockMvc.perform(put("/etudiant/modify-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(etudiant)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom").value("John"))
                .andExpect(jsonPath("$.prenom").value("Smith"));
    }
}
