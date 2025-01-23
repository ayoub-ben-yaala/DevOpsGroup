package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import tn.esprit.tpfoyer.TpFoyerApplication;
import tn.esprit.tpfoyer.control.EtudiantRestController;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@WebMvcTest(EtudiantServiceImplTest.class)
@ContextConfiguration(classes = TpFoyerApplication.class)

public class EtudiantServiceImplTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService; // Service à tester

    @Mock
    private EtudiantRepository etudiantRepository; // Dépendance du service, ici le repository

    private Etudiant etudiant;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
        etudiant = new Etudiant(1L, "John", "Doe", "12345678", "email@example.com"); // Création d'un étudiant
    }

    @Test
    public void testRemoveEtudiant_WhenEtudiantExists() {
        // Arrange
        Long etudiantId = 1L;

        // Act
        etudiantService.removeEtudiant(etudiantId); // Appel de la méthode à tester

        // Assert
        verify(etudiantRepository, times(1)).deleteById(etudiantId); // Vérifier que deleteById est bien appelé une fois
    }

}

