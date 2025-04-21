package org.example.ex1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatriceTest {

    @Mock
    private Calculatrice calculatrice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdditionner() {
        when(calculatrice.additionner(2, 3)).thenReturn(5);

        int resultat = calculatrice.additionner(2, 3);

        assertEquals(5, resultat);

        verify(calculatrice).additionner(2, 3);
        verifyNoMoreInteractions(calculatrice);
    }
}

