package org.example.ex3;

public class UserService {
    private final UtilisateurApi utilisateurApi;

    public UserService(UtilisateurApi utilisateurApi) {
        this.utilisateurApi = utilisateurApi;
    }
    public void creerUtilisateur(Utilisateur utilisateur) throws ServiceException {
        if (utilisateur.getNom() == null || utilisateur.getNom().isEmpty() ||
                utilisateur.getPrenom() == null || utilisateur.getPrenom().isEmpty() ||
                !utilisateur.getEmail().contains("@")) {
            throw new IllegalArgumentException("Données utilisateur invalides");
        }
        utilisateurApi.creerUtilisateur(utilisateur);
    }
}


