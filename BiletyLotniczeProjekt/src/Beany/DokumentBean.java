package Beany;


import java.sql.Date;

public class DokumentBean 
{
    public static final String DOKUMENT_TYP_DOWOD = "D";
    public static final String DOKUMENT_TYP_PASZPORT = "P";
    
    Integer dokumentID;
    Integer dokumentUzytkownikID;
    String dokumentTyp;
    String dokumentNumer;
    Date dokumentDataWaznosci;

    public Integer getDokumentID() {
        return dokumentID;
    }

    public void setDokumentID(Integer dokumentID) {
        this.dokumentID = dokumentID;
    }

    public Integer getDokumentUzytkownikID() {
        return dokumentUzytkownikID;
    }

    public void setDokumentUzytkownikID(Integer dokumentUzytkownikID) {
        this.dokumentUzytkownikID = dokumentUzytkownikID;
    }

    public String getDokumentTyp() {
        return dokumentTyp;
    }

    public void setDokumentTyp(String dokumentTyp) {
        this.dokumentTyp = dokumentTyp;
    }

    public String getDokumentNumer() {
        return dokumentNumer;
    }

    public void setDokumentNumer(String dokumentNumer) {
        this.dokumentNumer = dokumentNumer;
    }

    public Date getDokumentDataWaznosci() {
        return dokumentDataWaznosci;
    }

    public void setDokumentDataWaznosci(Date dokumentDataWaznosci) {
        this.dokumentDataWaznosci = dokumentDataWaznosci;
    }
    
    
}
