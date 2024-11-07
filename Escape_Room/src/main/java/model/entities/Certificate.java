package src.main.java.model.entities;

public class Certificate {

    private int idCertificate;
    private String achivementsDetails;
    private String awardDate;

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
