package tn.esprit.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.tpfoyer.TpFoyerApplication;
import tn.esprit.tpfoyer.control.EtudiantRestController;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EtudiantRestController.class)
@ContextConfiguration(classes = TpFoyerApplication.class)
public class EtudiantRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @org.springframework.boot.test.mock.mockito.MockBean
    private IEtudiantService etudiantService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetEtudiants() throws Exception {
        Etudiant etudiant1 = new Etudiant(1L, "Smith", "John", 12345678, "aa");
        Etudiant etudiant2 = new Etudiant(2L, "Jane", "Smith", 87654321, "aa");
        List<Etudiant> etudiants = Arrays.asList(etudiant1, etudiant2);

        given(etudiantService.retrieveAllEtudiants()).willReturn(etudiants);

        mockMvc.perform(get("/etudiant/retrieve-all-etudiants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].idEtudiant").value(1L))
                .andExpect(jsonPath("$[0].nomEtudiant").value("John"))
                .andExpect(jsonPath("$[1].idEtudiant").value(2L))
                .andExpect(jsonPath("$[1].nomEtudiant").value("Jane"));
    }

    @Test
    public void testRetrieveEtudiantParCin() throws Exception {
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", 12345678, "aa");

        given(etudiantService.recupererEtudiantParCin(12345678L)).willReturn(etudiant);

        mockMvc.perform(get("/etudiant/retrieve-etudiant-cin/12345678"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.idEtudiant").value(1L))
                .andExpect(jsonPath("$.nomEtudiant").value("John"));
    }

    @Test
    public void testRetrieveEtudiant() throws Exception {
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", 12345678, "aa");

        given(etudiantService.retrieveEtudiant(1L)).willReturn(etudiant);

        mockMvc.perform(get("/etudiant/retrieve-etudiant/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.idEtudiant").value(1L))
                .andExpect(jsonPath("$.nomEtudiant").value("John"));
    }

    @Test
    public void testAddEtudiant() throws Exception {
        Etudiant etudiant = new Etudiant(2L, "John", "Doe", 12345678, "aa");
        Etudiant savedEtudiant = new Etudiant(1L, "John", "Doe", 12345678, "aa");

        given(etudiantService.addEtudiant(etudiant)).willReturn(savedEtudiant);

        mockMvc.perform(post("/etudiant/add-etudiant")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(etudiant)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.idEtudiant").value(1L))
                .andExpect(jsonPath("$.nomEtudiant").value("John"));
    }

    @Test
    public void testRemoveEtudiant() throws Exception {
        // Pas de retour attendu du service, il s'agit uniquement de tester la suppression
        mockMvc.perform(delete("/etudiant/remove-etudiant/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifyEtudiant() throws Exception {
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", 12345678, "aa");
        Etudiant updatedEtudiant = new Etudiant(1L, "John", "Smith", 12345678, "aa");

        given(etudiantService.modifyEtudiant(etudiant)).willReturn(updatedEtudiant);

        mockMvc.perform(put("/etudiant/modify-etudiant")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(etudiant)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.idEtudiant").value(1L))
                .andExpect(jsonPath("$.nomEtudiant").value("John"))
                .andExpect(jsonPath("$.prenomEtudiant").value("Smith"));
    }
}
