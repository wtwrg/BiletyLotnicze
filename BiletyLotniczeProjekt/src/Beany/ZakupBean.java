package Beany;


import java.sql.Date;


public class ZakupBean 
{
    Integer zakupID;
    Integer zakupUzytkownikID;
    Integer zakupLotID;
    Date zakupData;
    String zakupRzadMejsce;
    String zakupKlasa;
    float zakupKwota;

    public Integer getZakupID() {
        return zakupID;
    }

    public void setZakupID(Integer zakupID) {
        this.zakupID = zakupID;
    }

    public Integer getZakupUzytkownikID() {
        return zakupUzytkownikID;
    }

    public void setZakupUzytkownikID(Integer zakupUzytkownikID) {
        this.zakupUzytkownikID = zakupUzytkownikID;
    }

    public Integer getZakupLotID() {
        return zakupLotID;
    }

    public void setZakupLotID(Integer zakupLotID) {
        this.zakupLotID = zakupLotID;
    }

    public Date getZakupData() {
        return zakupData;
    }

    public void setZakupData(Date zakupData) {
        this.zakupData = zakupData;
    }

    public String getZakupRzadMejsce() {
        return zakupRzadMejsce;
    }

    public void setZakupRzadMejsce(String zakupRzadMejsce) {
        this.zakupRzadMejsce = zakupRzadMejsce;
    }

    public String getZakupKlasa() {
        return zakupKlasa;
    }

    public void setZakupKlasa(String zakupKlasa) {
        this.zakupKlasa = zakupKlasa;
    }

    public float getZakupKwota() {
        return zakupKwota;
    }

    public void setZakupKwota(float zakupKwota) {
        this.zakupKwota = zakupKwota;
    }
    
    
}
