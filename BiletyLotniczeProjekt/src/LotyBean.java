
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amarylis
 */
public class LotyBean 
{
    Integer lotID;
    String odlotPrzylot;
    float cenaKlasyEkonomicznej;
    float cenaKlasyEkonomicznejPremium;
    float cenaKlasyBiznes;
    float cenaKlasyPierwszej;
    Date dataOdlotu;
    Date dataPrzylotu;
    Integer lotniskoID;
    Integer samolotID;

    public String getOdlotPrzylot() {
        return odlotPrzylot;
    }

    public void setOdlotPrzylot(String odlotPrzylot) {
        this.odlotPrzylot = odlotPrzylot;
    }

    public float getCenaKlasyEkonomicznej() {
        return cenaKlasyEkonomicznej;
    }

    public void setCenaKlasyEkonomicznej(float cenaKlasyEkonomicznej) {
        this.cenaKlasyEkonomicznej = cenaKlasyEkonomicznej;
    }

    public float getCenaKlasyEkonomicznejPremium() {
        return cenaKlasyEkonomicznejPremium;
    }

    public void setCenaKlasyEkonomicznejPremium(float cenaKlasyEkonomicznejPremium) {
        this.cenaKlasyEkonomicznejPremium = cenaKlasyEkonomicznejPremium;
    }

    public float getCenaKlasyBiznes() {
        return cenaKlasyBiznes;
    }

    public void setCenaKlasyBiznes(float cenaKlasyBiznes) {
        this.cenaKlasyBiznes = cenaKlasyBiznes;
    }

    public float getCenaKlasyPierwszej() {
        return cenaKlasyPierwszej;
    }

    public void setCenaKlasyPierwszej(float cenaKlasyPierwszej) {
        this.cenaKlasyPierwszej = cenaKlasyPierwszej;
    }

    public Date getDataOdlotu() {
        return dataOdlotu;
    }

    public void setDataOdlotu(Date dataOdlotu) {
        this.dataOdlotu = dataOdlotu;
    }

    public Date getDataPrzylotu() {
        return dataPrzylotu;
    }

    public void setDataPrzylotu(Date dataPrzylotu) {
        this.dataPrzylotu = dataPrzylotu;
    }

    public Integer getLotniskoID() {
        return lotniskoID;
    }

    public void setLotniskoID(Integer lotniskoID) {
        this.lotniskoID = lotniskoID;
    }

    public Integer getSamolotID() {
        return samolotID;
    }

    public void setSamolotID(Integer samolotID) {
        this.samolotID = samolotID;
    }
    
    public Integer getLotID() 
    {
        return lotID;
    }

    public void setLotID(Integer lotID) 
    {
        this.lotID = lotID;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( "LotyBean: [" );
        sb.append( "lotID: " );
        sb.append( lotID );
        sb.append( "odlotPrzylot: " );
        sb.append( odlotPrzylot );
        sb.append( "cenaKlasyEkonomicznej: " );
        sb.append( cenaKlasyEkonomicznej );
        sb.append( "cenaKlasyEkonomicznejPremium: " );
        sb.append( cenaKlasyEkonomicznejPremium );
        sb.append( "cenaKlasyBiznes: " );
        sb.append( cenaKlasyBiznes );
        sb.append( "cenaKlasyPierwszej: " );
        sb.append( cenaKlasyPierwszej );
        sb.append( "dataOdlotu: " );
        sb.append( dataOdlotu );
        sb.append( "dataOdlotu: " );
        sb.append( dataPrzylotu );
        sb.append( "lotniskoID: " );
        sb.append( lotniskoID );
        sb.append( "samolotID: " );
        sb.append( samolotID );
        
        return sb.toString();
    }
}
