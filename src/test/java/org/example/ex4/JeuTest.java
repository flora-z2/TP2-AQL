package org.example.ex4;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class JeuTest {

    @Test
    public void testJeuFerme() {
        Banque banque = mock(Banque.class);
        Jeu jeu = new Jeu(banque);
        jeu.fermer();

        Joueur joueur = mock(Joueur.class);
        De de1 = mock(De.class);
        De de2 = mock(De.class);

        assertThrows(JeuFermeException.class, () -> jeu.jouer(joueur, de1, de2));
    }

    @Test
    public void testJoueurInsolvable() throws JeuFermeException, DebitImpossibleException {
        Banque banque = mock(Banque.class);
        Joueur joueur = mock(Joueur.class);
        De de1 = mock(De.class);
        De de2 = mock(De.class);

        Jeu jeu = new Jeu(banque);
        when(joueur.mise()).thenReturn(10);
        doThrow(new DebitImpossibleException()).when(joueur).debiter(10);

        jeu.jouer(joueur, de1, de2);

        verifyNoInteractions(de1, de2);
    }

    @Test
    public void testGagneEtBanqueFermee() throws Exception {
        Banque banque = mock(Banque.class);
        Joueur joueur = mock(Joueur.class);
        De de1 = mock(De.class);
        De de2 = mock(De.class);

        Jeu jeu = new Jeu(banque);

        when(joueur.mise()).thenReturn(10);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4);
        when(banque.est_solvable()).thenReturn(false);

        jeu.jouer(joueur, de1, de2);

        verify(joueur).crediter(20);
        assertFalse(jeu.estOuvert());
    }
}
