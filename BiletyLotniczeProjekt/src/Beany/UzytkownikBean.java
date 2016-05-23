package Beany;


import java.sql.Date;


public class UzytkownikBean 
{
    Integer uzytkownikID;
    String uzytkownikImie;
    String uzytkownikNazwisko;
    String uzytkownikPlec;
    String uzytkownikPESEL;
    boolean uzytkownikCzyAdministrator;
    boolean uzytkownikCzyZablokowany;
    boolean uzytkownikCzyZmianaHasla;
    float uzytkownikSaldo;
    Date uzytkownikData;
    String uzytkownikHaslo;

    public Integer getUzytkownikID() {
        return uzytkownikID;
    }

    public void setUzytkownikID(Integer uzytkownikID) {
        this.uzytkownikID = uzytkownikID;
    }

    public String getUzytkownikImie() {
        return uzytkownikImie;
    }

    public void setUzytkownikImie(String uzytkownikImie) {
        this.uzytkownikImie = uzytkownikImie;
    }

    public String getUzytkownikNazwisko() {
        return uzytkownikNazwisko;
    }

    public void setUzytkownikNazwisko(String uzytkownikNazwisko) {
        this.uzytkownikNazwisko = uzytkownikNazwisko;
    }

    public String getUzytkownikPlec() {
        return uzytkownikPlec;
    }

    public void setUzytkownikPlec(String uzytkownikPlec) {
        this.uzytkownikPlec = uzytkownikPlec;
    }

    public String getUzytkownikPESEL() {
        return uzytkownikPESEL;
    }

    public void setUzytkownikPESEL(String uzytkownikPESEL) {
        this.uzytkownikPESEL = uzytkownikPESEL;
    }

    public boolean isUzytkownikCzyAdministrator() {
        return uzytkownikCzyAdministrator;
    }

    public void setUzytkownikCzyAdministrator(boolean uzytkownikCzyAdministrator) {
        this.uzytkownikCzyAdministrator = uzytkownikCzyAdministrator;
    }

    public boolean isUzytkownikCzyZablokowany() {
        return uzytkownikCzyZablokowany;
    }

    public void setUzytkownikCzyZablokowany(boolean uzytkownikCzyZablokowany) {
        this.uzytkownikCzyZablokowany = uzytkownikCzyZablokowany;
    }

    public boolean isUzytkownikCzyZmianaHasla() {
        return uzytkownikCzyZmianaHasla;
    }

    public void setUzytkownikCzyZmianaHasla(boolean uzytkownikCzyZmianaHasla) {
        this.uzytkownikCzyZmianaHasla = uzytkownikCzyZmianaHasla;
    }

    public float getUzytkownikSaldo() {
        return uzytkownikSaldo;
    }

    public void setUzytkownikSaldo(float uzytkownikSaldo) {
        this.uzytkownikSaldo = uzytkownikSaldo;
    }

    public Date getUzytkownikData() {
        return uzytkownikData;
    }

    public void setUzytkownikData(Date uzytkownikData) {
        this.uzytkownikData = uzytkownikData;
    }
    
    public String getUzytkownikHaslo() {
        return uzytkownikHaslo;
    }

    public void setUzytkownikHaslo(String uzytkownikHaslo) {
        this.uzytkownikHaslo = uzytkownikHaslo;
    }
}
