package Beany;


import java.sql.Date;
public class LotBean 
{
    public static final String ODLOT = "O";
    public static final String PRZYLOT = "P";
    
    Integer lotID;
    String lotOdlotPrzylot;
    float lotCenaKlasyEkonomicznej;
    float lotCenaKlasyEkonomicznejPremium;
    float lotCenaKlasyBiznes;
    float lotCenaKlasyPierwszej;
    String lotDataOdlotu;
    String lotDataPrzylotu;
    Integer lotLotniskoID;
    Integer lotSamolotID;
    LotniskoBean lotLotniskoBean;
    SamolotBean lotSamolotBean;

    public Integer getLotID() {
        return lotID;
    }

    public void setLotID(Integer lotID) {
        this.lotID = lotID;
    }

    public String getLotOdlotPrzylot() {
        return lotOdlotPrzylot;
    }

    public void setLotOdlotPrzylot(String lotOdlotPrzylot) {
        this.lotOdlotPrzylot = lotOdlotPrzylot;
    }

    public float getLotCenaKlasyEkonomicznej() {
        return lotCenaKlasyEkonomicznej;
    }

    public void setLotCenaKlasyEkonomicznej(float lotCenaKlasyEkonomicznej) {
        this.lotCenaKlasyEkonomicznej = lotCenaKlasyEkonomicznej;
    }

    public float getLotCenaKlasyEkonomicznejPremium() {
        return lotCenaKlasyEkonomicznejPremium;
    }

    public void setLotCenaKlasyEkonomicznejPremium(float lotCenaKlasyEkonomicznejPremium) {
        this.lotCenaKlasyEkonomicznejPremium = lotCenaKlasyEkonomicznejPremium;
    }

    public float getLotCenaKlasyBiznes() {
        return lotCenaKlasyBiznes;
    }

    public void setLotCenaKlasyBiznes(float lotCenaKlasyBiznes) {
        this.lotCenaKlasyBiznes = lotCenaKlasyBiznes;
    }

    public float getLotCenaKlasyPierwszej() {
        return lotCenaKlasyPierwszej;
    }

    public void setLotCenaKlasyPierwszej(float lotCenaKlasyPierwszej) {
        this.lotCenaKlasyPierwszej = lotCenaKlasyPierwszej;
    }

    public String getLotDataOdlotu() {
        return lotDataOdlotu;
    }

    public void setLotDataOdlotu(String lotDataOdlotu) {
        this.lotDataOdlotu = lotDataOdlotu;
    }

    public String getLotDataPrzylotu() {
        return lotDataPrzylotu;
    }

    public void setLotDataPrzylotu(String lotDataPrzylotu) {
        this.lotDataPrzylotu = lotDataPrzylotu;
    }

    public Integer getLotLotniskoID() {
        return lotLotniskoID;
    }

    public void setLotLotniskoID(Integer lotLotniskoID) {
        this.lotLotniskoID = lotLotniskoID;
    }

    public Integer getLotSamolotID() {
        return lotSamolotID;
    }

    public void setLotSamolotID(Integer lotSamolotID) {
        this.lotSamolotID = lotSamolotID;
    }

    public LotniskoBean getLotLotniskoBean() {
        return lotLotniskoBean;
    }

    public void setLotLotniskoBean(LotniskoBean lotLotniskoBean) {
        this.lotLotniskoBean = lotLotniskoBean;
    }

    public SamolotBean getLotSamolotBean() {
        return lotSamolotBean;
    }

    public void setLotSamolotBean(SamolotBean lotSamolotBean) {
        this.lotSamolotBean = lotSamolotBean;
    }


}
