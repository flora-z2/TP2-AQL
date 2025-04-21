package org.example.ex3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceScenariosTest {

    @Mock
    private UtilisateurApi utilisateurApiMock;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(utilisateurApiMock);
    }

    @Test
    void testCreerUtilisateur_Exception() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jean@email.com");
        doThrow(new ServiceException("Échec")).when(utilisateurApiMock).creerUtilisateur(utilisateur);

        assertThrows(ServiceException.class, () -> {
            userService.creerUtilisateur(utilisateur);
        });
    }

    @Test
    void testValidationErreur() throws ServiceException {
        verify(utilisateurApiMock, never()).creerUtilisateur(any());
    }

    @Test
    void testCaptureArguments() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("Alice", "Martin", "alice@mail.com");

        ArgumentCaptor<Utilisateur> captor = ArgumentCaptor.forClass(Utilisateur.class);

        doNothing().when(utilisateurApiMock).creerUtilisateur(any());

        userService.creerUtilisateur(utilisateur);

        verify(utilisateurApiMock).creerUtilisateur(captor.capture());
        Utilisateur capt = captor.getValue();
        assertEquals("Alice", capt.getNom());
        assertEquals("Martin", capt.getPrenom());
        assertEquals("alice@mail.com", capt.getEmail());
    }
}
