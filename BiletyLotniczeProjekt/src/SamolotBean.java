/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amarylis
 */
public class SamolotBean 
{
    Integer samolotID;
    String linieLotnicze;
    Integer iloscRzedow;
    Integer iloscMiejscWRzedzie;
    boolean klasaEkonomiczna;
    boolean klasaEkonomicznaPremium;
    boolean klasaBiznes;
    boolean klasaPierwsza;

    public Integer getSamolotID() {
        return samolotID;
    }

    public void setSamolotID(Integer samolotID) {
        this.samolotID = samolotID;
    }

    public String getLinieLotnicze() {
        return linieLotnicze;
    }

    public void setLinieLotnicze(String linieLotnicze) {
        this.linieLotnicze = linieLotnicze;
    }

    public Integer getIloscRzedow() {
        return iloscRzedow;
    }

    public void setIloscRzedow(Integer iloscRzedow) {
        this.iloscRzedow = iloscRzedow;
    }

    public Integer getIloscMiejscWRzedzie() {
        return iloscMiejscWRzedzie;
    }

    public void setIloscMiejscWRzedzie(Integer iloscMiejscWRzedzie) {
        this.iloscMiejscWRzedzie = iloscMiejscWRzedzie;
    }

    public boolean isKlasaEkonomiczna() {
        return klasaEkonomiczna;
    }

    public void setKlasaEkonomiczna(boolean klasaEkonomiczna) {
        this.klasaEkonomiczna = klasaEkonomiczna;
    }

    public boolean isKlasaEkonomicznaPremium() {
        return klasaEkonomicznaPremium;
    }

    public void setKlasaEkonomicznaPremium(boolean klasaEkonomicznaPremium) {
        this.klasaEkonomicznaPremium = klasaEkonomicznaPremium;
    }

    public boolean isKlasaBiznes() {
        return klasaBiznes;
    }

    public void setKlasaBiznes(boolean klasaBiznes) {
        this.klasaBiznes = klasaBiznes;
    }

    public boolean isKlasaPierwsza() {
        return klasaPierwsza;
    }

    public void setKlasaPierwsza(boolean klasaPierwsza) {
        this.klasaPierwsza = klasaPierwsza;
    }
}
