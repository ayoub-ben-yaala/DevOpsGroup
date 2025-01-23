package tn.esprit.tpfoyer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.tpfoyer.control.FoyerRestController;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.service.IFoyerService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FoyerRestController.class)
public class FoyerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFoyerService foyerService; // Remplace @Mock par @MockBean

    private ObjectMapper objectMapper;
    private Foyer foyer;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        foyer = new Foyer(1L, "Foyer A", 100, null, null);
    }

    @Test
    public void testDeleteFoyer() throws Exception {
        // Arrange
        doNothing().when(foyerService).removeFoyer(1L);

        // Act
        mockMvc.perform(delete("/foyer/remove-foyer/1"))
                .andExpect(status().isOk());

        // Assert
        verify(foyerService, times(1)).removeFoyer(1L);
    }
}
