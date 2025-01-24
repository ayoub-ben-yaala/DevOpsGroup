package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import static org.mockito.Mockito.*;

public class ChambreServiceImplTest {

    @InjectMocks
    private ChambreServiceImpl chambreService; // Service à tester

    @Mock
    private ChambreRepository chambreRepository; // Dépendance du service, ici le repository

    private Chambre chambre;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
        chambre = new Chambre(1L, "John", TypeChambre.SIMPLE);
    }

    @Test
    public void testRemoveChambre_WhenChambreExists() {
        // Arrange
        Long chambreId = 1L;

        // Act
        chambreService.removeChambre(chambreId); // Appel de la méthode à tester

        // Assert
        verify(chambreRepository, times(1)).deleteById(chambreId); // Vérifier que deleteById est bien appelé une fois
    }
}