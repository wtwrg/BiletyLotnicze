package Narzedzia;


import Beany.SamolotBean;
import Beany.LotniskoBean;
import Beany.LotBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JRadioButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amarylis
 */
public class Loty 
{
    private final static String POBRANIE_LOTOW = "SELECT LOT_ID, LOT_ODLOT_PRZYLOT, LOT_CENA_KLASA_EKONOMICZNA, " +
            "LOT_CENA_KLASA_EKONOMICZNA_PREMIUM, LOT_CENA_KLASA_BIZNES, LOT_CENA_KLASA_PIERWSZA, LOT_DATA_ODLOTU, " +
            "LOT_DATA_PRZYLOTU, LOT_LOTNISKO_ID, LOT_SAMOLOT_ID FROM LOTY ";
    
    private final static String POBRANIE_LOTNISK = "SELECT LTN_ID, LTN_KRAJ, LTN_MIASTO, LTN_NAZWA FROM LOTNISKO";
    
    private final static String POBRANIE_SAMOLOTOW = "SELECT SML_ID, SML_LINIE_LOTNICZE, SML_ILOSC_RZEDOW, SML_ILOSC_MIEJSC_W_RZEDZIE, " +
            "SML_KLASA_EKONOMICZNA, SML_KLASA_EKONOMICZNA_PREMIUM, SML_KLASA_BIZNES, SML_KLASA_PIERWSZA FROM SAMOLOTY"; 
    
    Connection connection = null;
    DBConnector dbConnector = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    NarzedziaBazyDanych narzedziaBazyDanych = null;
    
    public Loty()
    {
        dbConnector = new DBConnector();
        narzedziaBazyDanych = new NarzedziaBazyDanych();
    }
    
    private List<LotBean> pobierzLoty() throws SQLException
    {
        connection = dbConnector.setConnection();
        List<LotBean> listaLotyBean = new ArrayList<LotBean>();
        try
        {
            ps = connection.prepareStatement( POBRANIE_LOTOW );
            rs = ps.executeQuery();
            listaLotyBean = narzedziaBazyDanych.ustawLoty( rs );
            
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            connection.close();
            ps.close();
            rs.close();
        }
        return listaLotyBean;
    }
    
    private List<LotniskoBean> pobierzLotniska() throws SQLException
    {
        connection = dbConnector.setConnection();
        List<LotniskoBean> listaLotniskoBean = new ArrayList<LotniskoBean>();
        try
        {
            ps = connection.prepareStatement( POBRANIE_LOTNISK );
            rs = ps.executeQuery();
            listaLotniskoBean = narzedziaBazyDanych.ustawLotnisko(rs );
            
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            connection.close();
            ps.close();
            rs.close();
        }
        return listaLotniskoBean;
    }
    
    private List<SamolotBean> pobierzSamoloty() throws SQLException
    {
        connection = dbConnector.setConnection();
        List<SamolotBean> listaSamolotBean = new ArrayList<SamolotBean>();
        try
        {
            ps = connection.prepareStatement( POBRANIE_SAMOLOTOW );
            rs = ps.executeQuery();
            listaSamolotBean = narzedziaBazyDanych.ustawSamoloty(rs );
            
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            connection.close();
            ps.close();
            rs.close();
        }
        return listaSamolotBean;
    }
    
    public List<Object[]> pokazLoty( String przylotOdlot, String klasa ) throws SQLException
    {
        List<LotBean> listaLotyBean = pobierzLoty();
        List<LotniskoBean> listaLotniskoBean = pobierzLotniska();
        List<SamolotBean> listaSamolotBean = pobierzSamoloty();
        
        for( int i=0; i<listaLotyBean.size(); i++  )
        {
            LotBean lot = listaLotyBean.get( i );
            if( !przylotOdlot.equals( lot.getLotOdlotPrzylot() ) )
            {
                listaLotyBean.remove( lot );
                i--;
            }
        }
        
        for( LotBean lot : listaLotyBean )
        {
            for( LotniskoBean lotnisko : listaLotniskoBean )
            {
                if( lot.getLotLotniskoID().equals( lotnisko.getLotniskoID() ) )
                {
                    lot.setLotLotniskoBean(lotnisko);
                }
            }
            
            for( SamolotBean samolot : listaSamolotBean )
            {
                if( lot.getLotSamolotID().equals( samolot.getSamolotID()) )
                {
                    lot.setLotSamolotBean(samolot);
                }
            }
        }
        
        List<Object[]> aktualneLoty = new ArrayList<Object[]>();
        
        for( LotBean lot : listaLotyBean )
        {
            Object[] akutalnyLot = null;
            if( przylotOdlot.equals( LotBean.ODLOT ) )
            {
                if( lot.getLotCenaKlasyEkonomicznej() != 0 )
                {
                    akutalnyLot = new Object[]{ lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA, lot.getLotCenaKlasyEkonomicznej(), lot.getLotDataOdlotu() };
                    aktualneLoty.add(akutalnyLot);
                }
                if( lot.getLotCenaKlasyEkonomicznejPremium()!= 0 )
                {
                    akutalnyLot = new Object[]{ lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA_PREMIUM, lot.getLotCenaKlasyEkonomicznejPremium(), lot.getLotDataOdlotu()};
                    aktualneLoty.add(akutalnyLot);
                }
                if( lot.getLotCenaKlasyBiznes()!= 0 )
                {
                    akutalnyLot = new Object[]{ lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_BIZNES, lot.getLotCenaKlasyBiznes(), lot.getLotDataOdlotu()};
                    aktualneLoty.add(akutalnyLot);
                }
                if( lot.getLotCenaKlasyPierwszej()!= 0 )
                {
                    akutalnyLot = new Object[]{ lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_PIERWSZA, lot.getLotCenaKlasyPierwszej(), lot.getLotDataOdlotu()};
                    aktualneLoty.add(akutalnyLot);
                }
            }
            else if( przylotOdlot.equals( LotBean.PRZYLOT ) )
            {
                if( lot.getLotCenaKlasyEkonomicznej() != 0 )
                {
                    akutalnyLot = new Object[]{ lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA, lot.getLotCenaKlasyEkonomicznej(), lot.getLotDataPrzylotu() };
                    aktualneLoty.add(akutalnyLot);
                }
                if( lot.getLotCenaKlasyEkonomicznejPremium()!= 0 )
                {
                    akutalnyLot = new Object[]{ lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA_PREMIUM, lot.getLotCenaKlasyEkonomicznejPremium(), lot.getLotDataPrzylotu()};
                    aktualneLoty.add(akutalnyLot);
                }
                if( lot.getLotCenaKlasyBiznes()!= 0 )
                {
                    akutalnyLot = new Object[]{ lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_BIZNES, lot.getLotCenaKlasyBiznes(), lot.getLotDataPrzylotu()};
                    aktualneLoty.add(akutalnyLot);
                }
                if( lot.getLotCenaKlasyPierwszej()!= 0 )
                {
                    akutalnyLot = new Object[]{ lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_PIERWSZA, lot.getLotCenaKlasyPierwszej(), lot.getLotDataPrzylotu()};
                    aktualneLoty.add(akutalnyLot);
                }
            }
            
        }
        return aktualneLoty;
    }
}
