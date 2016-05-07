package Beany;


import java.sql.Date;


public class WiadomoscBean 
{
    Integer wiadomoscID;
    Integer wiadomoscIDOdbiorcy;
    Integer wiadomoscIDNadawcy;
    String wiadomoscTemat;
    String wiadomoscTresc;
    Date wiadomoscData;
    String wiadomoscTyp;

    public Integer getWiadomoscID() {
        return wiadomoscID;
    }

    public void setWiadomoscID(Integer wiadomoscID) {
        this.wiadomoscID = wiadomoscID;
    }

    public Integer getWiadomoscIDOdbiorcy() {
        return wiadomoscIDOdbiorcy;
    }

    public void setWiadomoscIDOdbiorcy(Integer wiadomoscIDOdbiorcy) {
        this.wiadomoscIDOdbiorcy = wiadomoscIDOdbiorcy;
    }

    public Integer getWiadomoscIDNadawcy() {
        return wiadomoscIDNadawcy;
    }

    public void setWiadomoscIDNadawcy(Integer wiadomoscIDNadawcy) {
        this.wiadomoscIDNadawcy = wiadomoscIDNadawcy;
    }

    public String getWiadomoscTemat() {
        return wiadomoscTemat;
    }

    public void setWiadomoscTemat(String wiadomoscTemat) {
        this.wiadomoscTemat = wiadomoscTemat;
    }

    public String getWiadomoscTresc() {
        return wiadomoscTresc;
    }

    public void setWiadomoscTresc(String wiadomoscTresc) {
        this.wiadomoscTresc = wiadomoscTresc;
    }

    public Date getWiadomoscData() {
        return wiadomoscData;
    }

    public void setWiadomoscData(Date wiadomoscData) {
        this.wiadomoscData = wiadomoscData;
    }

    public String getWiadomoscTyp() {
        return wiadomoscTyp;
    }

    public void setWiadomoscTyp(String wiadomoscTyp) {
        this.wiadomoscTyp = wiadomoscTyp;
    }
    
    
}
