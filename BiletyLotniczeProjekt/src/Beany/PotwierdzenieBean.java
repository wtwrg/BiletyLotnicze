package Beany;


public class PotwierdzenieBean 
{
    Integer potwierdzenieID;
    Integer potwierdzenieZakupID;
    String potwierdzeniePDF;

    public Integer getPotwierdzenieID() {
        return potwierdzenieID;
    }

    public void setPotwierdzenieID(Integer potwierdzenieID) {
        this.potwierdzenieID = potwierdzenieID;
    }

    public Integer getPotwierdzenieUzytkownikID() {
        return potwierdzenieZakupID;
    }

    public void setPotwierdzenieUzytkownikID(Integer potwierdzenieUzytkownikID) {
        this.potwierdzenieZakupID = potwierdzenieUzytkownikID;
    }

    public String getPotwierdzeniePDF() {
        return potwierdzeniePDF;
    }

    public void setPotwierdzeniePDF(String potwierdzeniePDF) {
        this.potwierdzeniePDF = potwierdzeniePDF;
    }
    
    
}
