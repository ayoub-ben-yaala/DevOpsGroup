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
import tn.esprit.tpfoyer.service.IChambreService;
i

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EtudiantRestController.class)
@ContextConfiguration(classes = TpFoyerApplication.class)
public class ChambreRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @org.springframework.boot.test.mock.mockito.MockBean
    private IChambreService chambreService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }


    @Test
    public void testRemoveChambre() throws Exception {
        // Pas de retour attendu du service, il s'agit uniquement de tester la suppression
        mockMvc.perform(delete("/chambre/remove-chambre/1"))
                .andExpect(status().isOk());
    }

}