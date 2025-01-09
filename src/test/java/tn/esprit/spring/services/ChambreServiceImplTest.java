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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ChambreServiceImplTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllChambres() {
        // Arrange
        Chambre chambre1 = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        Chambre chambre2 = new Chambre(2L, "Chambre 2", TypeChambre.DOUBLE);
        List<Chambre> chambres = List.of(chambre1, chambre2);
        when(chambreRepository.findAll()).thenReturn(chambres);

        // Act
        List<Chambre> result = chambreService.retrieveAllChambres();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Chambre 1", result.get(0).getTypeC().name());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveChambre() {
        // Arrange
        Chambre chambre = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        // Act
        Chambre result = chambreService.retrieveChambre(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Chambre 1", result.getTypeC().name());
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    void testAddChambre() {
        // Arrange
        Chambre chambre = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre);

        // Act
        Chambre result = chambreService.addChambre(chambre);

        // Assert
        assertNotNull(result);
        assertEquals("Chambre 1", result.getTypeC().name());
        verify(chambreRepository, times(1)).save(any(Chambre.class));
    }

    @Test
    void testModifyChambre() {
        // Arrange
        Chambre chambre = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre);

        // Act
        Chambre result = chambreService.modifyChambre(chambre);

        // Assert
        assertNotNull(result);
        assertEquals("Chambre 1", result.getTypeC().name());
        verify(chambreRepository, times(1)).save(any(Chambre.class));
    }

    @Test
    void testRemoveChambre() {
        // Arrange
        long chambreId = 1L;

        // Act
        chambreService.removeChambre(chambreId);

        // Assert
        verify(chambreRepository, times(1)).deleteById(chambreId);
    }

    @Test
    void testRecupererChambresSelonTyp() {
        // Arrange
        Chambre chambre1 = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        Chambre chambre2 = new Chambre(2L, "Chambre 2", TypeChambre.SIMPLE);
        List<Chambre> chambres = List.of(chambre1, chambre2);
        when(chambreRepository.findAllByTypeC(TypeChambre.SIMPLE)).thenReturn(chambres);

        // Act
        List<Chambre> result = chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(chambreRepository, times(1)).findAllByTypeC(TypeChambre.SIMPLE);
    }

    @Test
    void testTrouverChambreSelonEtudiant() {
        // Arrange
        Chambre chambre = new Chambre(1L, "Chambre 1", TypeChambre.SIMPLE);
        when(chambreRepository.trouverChselonEt(12345678L)).thenReturn(chambre);

        // Act
        Chambre result = chambreService.trouverchambreSelonEtudiant(12345678L);

        // Assert
        assertNotNull(result);
        assertEquals("Chambre 1", result.getTypeC().name());
        verify(chambreRepository, times(1)).trouverChselonEt(12345678L);
    }
}
