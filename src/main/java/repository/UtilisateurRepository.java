package repository;

import database.Database;
import model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtilisateurRepository {

    private Connection connexion;

    public UtilisateurRepository() {
        connexion = Database.getConnexion();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO user (nom, prenom, email, mdp, role) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMdp());
            stmt.setString(5, utilisateur.getRole());
            stmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }
    public boolean connexion(String email, String mdp) {
        String sql = "SELECT * FROM user WHERE email = ? AND mdp = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, mdp);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            System.out.println("connexion réussi");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'utilisateur : " + e.getMessage());
            return false;
        }

    }
    public boolean verifEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            System.out.println("compte existe déja");
            return false;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'utilisateur : " + e.getMessage());
            return true;
        }

    }
    public Object getUtilisateurParEmail(String email) {
        Utilisateur user = null;
        String sql = "SELECT * FROM user WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                user = new Utilisateur(rs.getInt("id_user"),rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),rs.getString("mdp"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
        return user;
    }
    public ArrayList<Utilisateur> getTousLesUtilisateurs() {
        ArrayList<Utilisateur> users = new ArrayList<>();
        Utilisateur user = null;
        String sql = "SELECT * FROM user";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                user = new Utilisateur(rs.getInt("id_user"),rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),rs.getString("mdp"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
        return users;
    }
    public void supprimerUtilisateurParEmail(String email) {
        String sql = "DELETE FROM user WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeUpdate();
            System.out.println("Utilisateur supprimer avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    public void mettreAJourUtilisateur(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateurs SET nom = ?, prenom = ?, mdp = ?, role = ? " +
                "WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getMdp());
            stmt.setString(4, utilisateur.getRole());
            stmt.setString(5, utilisateur.getEmail());
            stmt.executeUpdate();
            System.out.println("Utilisateur modifier avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

}
