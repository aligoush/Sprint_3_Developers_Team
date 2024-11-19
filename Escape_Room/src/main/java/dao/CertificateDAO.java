package dao;

import model.entities.Certificate;

import java.sql.SQLException;
import java.util.List;

public interface CertificateDAO {

    List<Certificate> getAllCertificates();
    void createCertificate(Certificate certificate);
    void assignCertificateToPlayer(int id_certificate, int idPlayer);
}