package Beany;


import java.sql.Date;


public class HistoriaPlatnosciBean 
{
    Integer historiaPlatnosciID;
    Integer historiaPlatnosciUzytkownikID;
    Integer historiaPlatnosciZakupID;
    float historiaPlatnosciKwota;
    Date historiaPlatnosciData;

    public Integer getHistoriaPlatnosciID() {
        return historiaPlatnosciID;
    }

    public void setHistoriaPlatnosciID(Integer historiaPlatnosciID) {
        this.historiaPlatnosciID = historiaPlatnosciID;
    }

    public Integer getHistoriaPlatnosciUzytkownikID() {
        return historiaPlatnosciUzytkownikID;
    }

    public void setHistoriaPlatnosciUzytkownikID(Integer historiaPlatnosciUzytkownikID) {
        this.historiaPlatnosciUzytkownikID = historiaPlatnosciUzytkownikID;
    }

    public Integer getHistoriaPlatnosciZakupID() {
        return historiaPlatnosciZakupID;
    }

    public void setHistoriaPlatnosciZakupID(Integer historiaPlatnosciZakupID) {
        this.historiaPlatnosciZakupID = historiaPlatnosciZakupID;
    }

    public float getHistoriaPlatnosciKwota() {
        return historiaPlatnosciKwota;
    }

    public void setHistoriaPlatnosciKwota(float historiaPlatnosciKwota) {
        this.historiaPlatnosciKwota = historiaPlatnosciKwota;
    }

    public Date getHistoriaPlatnosciData() {
        return historiaPlatnosciData;
    }

    public void setHistoriaPlatnosciData(Date historiaPlatnosciData) {
        this.historiaPlatnosciData = historiaPlatnosciData;
    }
    
    
}
