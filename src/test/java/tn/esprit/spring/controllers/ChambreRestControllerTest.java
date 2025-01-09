package tn.esprit.spring.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tpfoyer.control.ChambreRestController;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChambreRestController.class)
class ChambreRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IChambreService chambreService;

    @InjectMocks
    private ChambreRestController chambreRestController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(chambreRestController).build();
    }

    @Test
    void testGetChambres() throws Exception {
        // Arrange
        Chambre chambre1 = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        Chambre chambre2 = new Chambre(2L, "Chambre 2", TypeChambre.DOUBLE);
        List<Chambre> chambres = List.of(chambre1, chambre2);
        when(chambreService.retrieveAllChambres()).thenReturn(chambres);

        // Act & Assert
        mockMvc.perform(get("/chambre/retrieve-all-chambres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));

        verify(chambreService, times(1)).retrieveAllChambres();
    }

    @Test
    void testRetrieveChambre() throws Exception {
        // Arrange
        Chambre chambre = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        when(chambreService.retrieveChambre(1L)).thenReturn(chambre);

        // Act & Assert
        mockMvc.perform(get("/chambre/retrieve-chambre/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Chambre 1"));

        verify(chambreService, times(1)).retrieveChambre(1L);
    }

    @Test
    void testAddChambre() throws Exception {
        // Arrange
        Chambre chambre = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        when(chambreService.addChambre(any(Chambre.class))).thenReturn(chambre);

        // Act & Assert
        mockMvc.perform(post("/chambre/add-chambre")
                        .contentType("application/json")
                        .content("{\"id\":1,\"name\":\"Chambre 1\",\"type\":\"SIMPLE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Chambre 1"));

        verify(chambreService, times(1)).addChambre(any(Chambre.class));
    }

    @Test
    void testRemoveChambre() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/chambre/remove-chambre/1"))
                .andExpect(status().isNoContent());

        verify(chambreService, times(1)).removeChambre(1L);
    }

    @Test
    void testModifyChambre() throws Exception {
        // Arrange
        Chambre chambre = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        when(chambreService.modifyChambre(any(Chambre.class))).thenReturn(chambre);

        // Act & Assert
        mockMvc.perform(put("/chambre/modify-chambre")
                        .contentType("application/json")
                        .content("{\"id\":1,\"name\":\"Chambre 1\",\"type\":\"SIMPLE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Chambre 1"));

        verify(chambreService, times(1)).modifyChambre(any(Chambre.class));
    }

    @Test
    void testTrouverChambresSelonTC() throws Exception {
        // Arrange
        Chambre chambre1 = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        List<Chambre> chambres = List.of(chambre1);
        when(chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE)).thenReturn(chambres);

        // Act & Assert
        mockMvc.perform(get("/chambre/trouver-chambres-selon-typ/SIMPLE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));

        verify(chambreService, times(1)).recupererChambresSelonTyp(TypeChambre.SIMPLE);
    }

    @Test
    void testTrouverChambreSelonEtudiant() throws Exception {
        // Arrange
        Chambre chambre = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        when(chambreService.trouverchambreSelonEtudiant(12345678L)).thenReturn(chambre);

        // Act & Assert
        mockMvc.perform(get("/chambre/trouver-chambre-selon-etudiant/123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(chambreService, times(1)).trouverchambreSelonEtudiant(12345678L);
    }
}
