package dao.impl;

import dao.CertificateDAO;
import exceptions.CertificateAlreadyExistsException;
import model.entities.Certificate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dao.MySQLConnection;

import exceptions.PlayerAlreadyExistsException;
import model.entities.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CertificateDAOImpl implements CertificateDAO {


    public List<Certificate> getAllCertificates() {
        List<Certificate> certificates = new ArrayList<>();
        String query = "SELECT * FROM certificates";
        try (Connection connection = MySQLConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                certificates.add(new Certificate(rs.getInt("id_certificate"), rs.getString("achievement_details"),
                        rs.getString("award_date"), rs.getInt("id_user")));
            }
        } catch (SQLException e) {
            System.out.println("Error extracting data: " + e.getMessage());
        }
        return certificates;
    }

    public void createCertificate(Certificate certificate){
        String existingCertificate = "SELECT COUNT(*) FROM players WHERE id_certificate = ?";
        String insertQuery = "INSERT INTO certificates (id_certificate, achivement_details, award_date, id_user ) VALUES (?,?,?,?)";

        try (Connection conn = MySQLConnection.getInstance().getConnection()){

            try(PreparedStatement checkStmt = conn.prepareStatement(existingCertificate)){
                checkStmt.setInt(1, certificate.getIdCertificate());
                try(ResultSet rs = checkStmt.executeQuery()){
                    if(rs.next() && rs.getInt(1)>0){
                        throw new CertificateAlreadyExistsException("Error: the player already has the achievement");
                    }
                }
            }


            try(PreparedStatement insertStmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
                insertStmt.setInt(1, certificate.getIdCertificate());
                insertStmt.setString(2, certificate.getAchivementsDetails());
                insertStmt.setString(3, certificate.getAwardDate());
                insertStmt.setInt(4, certificate.getId_user());

                insertStmt.executeUpdate();

                try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        certificate.setIdCertificate(generatedId);
                        System.out.println("Certificate created with ID: " + generatedId);
                    }
                }
            }
        } catch (SQLException | CertificateAlreadyExistsException e) {
            System.out.println("The certificate is already claimed by a pleyer: " + e.getMessage());
        }
    }

    public void assignCertificateToPlayer(int id_certificate, int idPlayer) {
        String query = "INSERT INTO certificates (id_certificate, id_user) VALUES (?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {



            stmt.setInt(1, id_certificate);
            stmt.setInt(2, idPlayer);


            stmt.executeUpdate();
            System.out.println("Achivement succesfully added to the player");
        } catch (SQLException e) {
            System.out.println("Error assigning achivement to te player " + e.getMessage());
        }
    }

}