package Beany;


import java.sql.Date;


public class RezerwacjaBean 
{
    Integer rezerwacjaID;
    Integer rezerwacjaUzytkownikID;
    Integer rezerwacjaLotID;
    String rezerwacjaData;
    String rezerwacjaRzadMiejsce;
    String rezerwacjaKlasa;

    public Integer getRezerwacjaID() {
        return rezerwacjaID;
    }

    public void setRezerwacjaID(Integer rezerwacjaID) {
        this.rezerwacjaID = rezerwacjaID;
    }

    public Integer getRezerwacjaUzytkownikID() {
        return rezerwacjaUzytkownikID;
    }

    public void setRezerwacjaUzytkownikID(Integer rezerwacjaUzytkownikID) {
        this.rezerwacjaUzytkownikID = rezerwacjaUzytkownikID;
    }

    public Integer getRezerwacjaLotID() {
        return rezerwacjaLotID;
    }

    public void setRezerwacjaLotID(Integer rezerwacjaLotID) {
        this.rezerwacjaLotID = rezerwacjaLotID;
    }

    public String getRezerwacjaData() {
        return rezerwacjaData;
    }

    public void setRezerwacjaData(String rezerwacjaData) {
        this.rezerwacjaData = rezerwacjaData;
    }

    public String getRezerwacjaRzadMiejsce() {
        return rezerwacjaRzadMiejsce;
    }

    public void setRezerwacjaRzadMiejsce(String rezerwacjaRzadMiejsce) {
        this.rezerwacjaRzadMiejsce = rezerwacjaRzadMiejsce;
    }

    public String getRezerwacjaKlasa() {
        return rezerwacjaKlasa;
    }

    public void setRezerwacjaKlasa(String rezerwacjaKlasa) {
        this.rezerwacjaKlasa = rezerwacjaKlasa;
    }
    
    
}
