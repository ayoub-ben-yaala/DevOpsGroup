package tn.esprit.tpfoyer.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FoyerServiceImplTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    private Foyer foyer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        foyer = new Foyer(1L, "Foyer A", 100, null, null);
    }

    @Test
    public void testRemoveFoyer() {
        // Act
        foyerService.removeFoyer(1L);

        // Assert
        verify(foyerRepository, times(1)).deleteById(1L);
    }
}