package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EtudiantServiceImplTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    private Etudiant etudiant;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        etudiant = new Etudiant(1L, "John", "Doe", "12345678","");
    }

    @Test
    public void testRetrieveAllEtudiants() {
        // Arrange
        when(etudiantRepository.findAll()).thenReturn(List.of(etudiant));

        // Act
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        // Assert
        assertNotNull(etudiants);
        assertEquals(1, etudiants.size());
        assertEquals("John", etudiants.get(0).getNomEtudiant());
    }

    @Test
    public void testRetrieveEtudiant() {
        // Arrange
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1L);

        // Assert
        assertNotNull(retrievedEtudiant);
        assertEquals("John", retrievedEtudiant.getNomEtudiant());
        assertEquals("Doe", retrievedEtudiant.getPrenomEtudiant());
    }

    @Test
    public void testAddEtudiant() {
        // Arrange
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);

        // Assert
        assertNotNull(savedEtudiant);
        assertEquals("John", savedEtudiant.getNomEtudiant());
        assertEquals("Doe", savedEtudiant.getPrenomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant); // Verify save method is called
    }

    @Test
    public void testModifyEtudiant() {
        // Arrange
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant modifiedEtudiant = etudiantService.modifyEtudiant(etudiant);

        // Assert
        assertNotNull(modifiedEtudiant);
        assertEquals("John", modifiedEtudiant.getNomEtudiant());
        assertEquals("Doe", modifiedEtudiant.getPrenomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant); // Verify save method is called
    }

    @Test
    public void testRemoveEtudiant() {
        // Act
        etudiantService.removeEtudiant(1L);

        // Assert
        verify(etudiantRepository, times(1)).deleteById(1L); // Verify delete method is called
    }

    @Test
    public void testRecupererEtudiantParCin() {
        // Arrange
        long cin = 12345678L;
        when(etudiantRepository.findEtudiantByCinEtudiant(cin)).thenReturn(etudiant);

        // Act
        Etudiant retrievedEtudiant = etudiantService.recupererEtudiantParCin(cin);

        // Assert
        assertNotNull(retrievedEtudiant);
        assertEquals(cin, retrievedEtudiant.getCinEtudiant());
        assertEquals("John", retrievedEtudiant.getNomEtudiant());
    }
}
