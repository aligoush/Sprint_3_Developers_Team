package model.entities;

public class Certificate {

    private int idCertificate;
    private String achivementsDetails;
    private String awardDate;
    private int id_user;

    public Certificate(int idCertificate, String achivementsDetails, String awardDate, int id_user) {

        this.idCertificate = idCertificate;
        this.achivementsDetails = achivementsDetails;
        this.awardDate = awardDate;
        this.id_user = id_user;
    }



    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getIdCertificate() {
        return idCertificate;
    }

    public void setIdCertificate(int idCertificate) {
        this.idCertificate = idCertificate;
    }

    public String getAchivementsDetails() {
        return achivementsDetails;
    }

    public void setAchivementsDetails(String achivementsDetails) {
        this.achivementsDetails = achivementsDetails;
    }

    public String getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(String awardDate) {
        this.awardDate = awardDate;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "idCertificate=" + idCertificate +
                ", achivementsDetails='" + achivementsDetails + '\'' +
                ", awardDate='" + awardDate + '\'' +
                '}';
    }
}
