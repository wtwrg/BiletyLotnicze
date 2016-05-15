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
import java.util.regex.Pattern;

public class Loty 
{
    private final static String POBRANIE_LOTOW = "SELECT LOT_ID, LOT_ODLOT_PRZYLOT, LOT_CENA_KLASA_EKONOMICZNA, " +
            "LOT_CENA_KLASA_EKONOMICZNA_PREMIUM, LOT_CENA_KLASA_BIZNES, LOT_CENA_KLASA_PIERWSZA, LOT_DATA_ODLOTU, " +
            "LOT_DATA_PRZYLOTU, LOT_LOTNISKO_ID, LOT_SAMOLOT_ID FROM LOTY ";
    
    private final static String POBRANIE_LOTNISK = "SELECT LTN_ID, LTN_KRAJ, LTN_MIASTO, LTN_NAZWA FROM LOTNISKO";
    
    private final static String POBRANIE_SAMOLOTOW = "SELECT SML_ID, SML_LINIE_LOTNICZE, SML_ILOSC_RZEDOW, SML_ILOSC_MIEJSC_W_RZEDZIE, " +
            "SML_KLASA_EKONOMICZNA, SML_KLASA_EKONOMICZNA_PREMIUM, SML_KLASA_BIZNES, SML_KLASA_PIERWSZA FROM SAMOLOTY"; 
    
    private final String POBRANIE_RZEDOW = "SELECT SML_ILOSC_RZEDOW FROM SAMOLOTY, LOTY WHERE LOT_ID=? AND LOT_SAMOLOT_ID=SML_ID";
    
    private final String POBRANIE_MIEJSC = "SELECT SML_ILOSC_MIEJSC_W_RZEDZIE FROM SAMOLOTY, LOTY WHERE LOT_ID=? AND LOT_SAMOLOT_ID=SML_ID";
    
    private final String POBRANIE_ZAJETYCH_MIEJSC = "SELECT ZKP_RZAD_MIEJSCE, RZR_RZAD_MIEJSCE FROM ZAKUPY, REZERWACJE WHERE ZKP_LOT_ID=? AND ZKP_LOT_ID=RZR_LOT_ID";
    
    private final String REZERWACJA_LOTU = "INSERT INTO rezerwacje (RZR_UZT_ID, RZR_LOT_ID, RZR_DATA, RZR_RZAD_MIEJSCE, RZR_KLASA) VALUES (?,?,NOW(),?,?)";
    
    private final String KUPNO_LOTU = "INSERT INTO zakupy (ZKP_UZT_ID, ZKP_LOT_ID, ZKP_DATA, ZKP_RZAD_MIEJSCE, ZKP_KLASA, ZKP_KWOTA) VALUES (?,?,NOW(),?,?,?)";

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
                if( klasa.equals("Wszystkie") )
                {
                    if( lot.getLotCenaKlasyEkonomicznej() != 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA, lot.getLotCenaKlasyEkonomicznej(), lot.getLotDataOdlotu() };
                        aktualneLoty.add(akutalnyLot);
                    }
                    if( lot.getLotCenaKlasyEkonomicznejPremium()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA_PREMIUM, lot.getLotCenaKlasyEkonomicznejPremium(), lot.getLotDataOdlotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                    if( lot.getLotCenaKlasyBiznes()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_BIZNES, lot.getLotCenaKlasyBiznes(), lot.getLotDataOdlotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                    if( lot.getLotCenaKlasyPierwszej()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_PIERWSZA, lot.getLotCenaKlasyPierwszej(), lot.getLotDataOdlotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                }
                if( klasa.equals(SamolotBean.KLASA_EKONOMICZNA) )
                {
                    if( lot.getLotCenaKlasyEkonomicznej() != 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA, lot.getLotCenaKlasyEkonomicznej(), lot.getLotDataOdlotu() };
                        aktualneLoty.add(akutalnyLot);
                    }
                }
                if( klasa.equals(SamolotBean.KLASA_EKONOMICZNA_PREMIUM) )
                {
                    if( lot.getLotCenaKlasyEkonomicznejPremium()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA_PREMIUM, lot.getLotCenaKlasyEkonomicznejPremium(), lot.getLotDataOdlotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                }
                if( klasa.equals(SamolotBean.KLASA_BIZNES) )
                {
                    if( lot.getLotCenaKlasyBiznes()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_BIZNES, lot.getLotCenaKlasyBiznes(), lot.getLotDataOdlotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                }
                if( klasa.equals(SamolotBean.KLASA_PIERWSZA) )
                {
                    if( lot.getLotCenaKlasyPierwszej()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_PIERWSZA, lot.getLotCenaKlasyPierwszej(), lot.getLotDataOdlotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                }
            }
            else if( przylotOdlot.equals( LotBean.PRZYLOT ) )
            {
                if( klasa.equals("Wszystkie") )
                {
                    if( lot.getLotCenaKlasyEkonomicznej() != 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA, lot.getLotCenaKlasyEkonomicznej(), lot.getLotDataPrzylotu() };
                        aktualneLoty.add(akutalnyLot);
                    }
                    if( lot.getLotCenaKlasyEkonomicznejPremium()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA_PREMIUM, lot.getLotCenaKlasyEkonomicznejPremium(), lot.getLotDataPrzylotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                    if( lot.getLotCenaKlasyBiznes()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_BIZNES, lot.getLotCenaKlasyBiznes(), lot.getLotDataPrzylotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                    if( lot.getLotCenaKlasyPierwszej()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_PIERWSZA, lot.getLotCenaKlasyPierwszej(), lot.getLotDataPrzylotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                }
                if( klasa.equals(SamolotBean.KLASA_EKONOMICZNA) )
                {
                    if( lot.getLotCenaKlasyEkonomicznej() != 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA, lot.getLotCenaKlasyEkonomicznej(), lot.getLotDataPrzylotu() };
                        aktualneLoty.add(akutalnyLot);
                    }
                }
                if( klasa.equals(SamolotBean.KLASA_EKONOMICZNA_PREMIUM) )
                {
                    if( lot.getLotCenaKlasyEkonomicznejPremium()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_EKONOMICZNA_PREMIUM, lot.getLotCenaKlasyEkonomicznejPremium(), lot.getLotDataPrzylotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                }
                if( klasa.equals(SamolotBean.KLASA_BIZNES) )
                {
                    if( lot.getLotCenaKlasyBiznes()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_BIZNES, lot.getLotCenaKlasyBiznes(), lot.getLotDataPrzylotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                }
                if( klasa.equals(SamolotBean.KLASA_PIERWSZA) )
                {
                    if( lot.getLotCenaKlasyPierwszej()!= 0 )
                    {
                        akutalnyLot = new Object[]{ lot.getLotID(), lot.getLotLotniskoBean().getLotniskoMiasto(), lot.getLotLotniskoBean().getLotniskoNazwa(), lot.getLotSamolotBean().getLinieLotnicze(), SamolotBean.KLASA_PIERWSZA, lot.getLotCenaKlasyPierwszej(), lot.getLotDataPrzylotu()};
                        aktualneLoty.add(akutalnyLot);
                    }
                }
            } 
        }
        
        return aktualneLoty;
    }
    
    public Object[] pobierzDostepneRzedy( Object IDLotu ) throws SQLException
    {
        connection = dbConnector.setConnection();
        Object[] rzedy = null;
        int iloscRzedow = 0;
        try
        {
            ps = connection.prepareStatement( POBRANIE_RZEDOW );
            ps.setObject(1, IDLotu);
            rs = ps.executeQuery();
            while(rs.next())
            {
                iloscRzedow = rs.getInt(1);
            }
            rzedy = new Object[iloscRzedow];
            
            for( int i=1; i<=iloscRzedow; i++ )
            {
                rzedy[i-1] = i;
            }
            
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
        return rzedy;
    }
    
    public Object[] pobierzDostepneMiejsca( Object IDLotu, Object rzad ) throws SQLException
    {
        connection = dbConnector.setConnection();
        String iloscMiejscPS = POBRANIE_MIEJSC.replaceFirst("\\?", String.valueOf(IDLotu));
        Object[] miejsca = null;
        int iloscMiejsc = 0;
        List<String> rzedyMiejsca = new ArrayList<String>();
        List<String> zajeteMiejsca = new ArrayList<String>();
        try
        {
            ps = connection.prepareStatement( iloscMiejscPS );
            rs = ps.executeQuery();
            while(rs.next())
            {
                iloscMiejsc = rs.getInt(1);
            }
            
            String zajeteMiejscaPS = POBRANIE_ZAJETYCH_MIEJSC.replaceFirst("\\?", String.valueOf(IDLotu));
            ps = connection.prepareStatement( zajeteMiejscaPS );
            rs = ps.executeQuery();
            while(rs.next())
            {
                rzedyMiejsca.add( rs.getString(1));
                rzedyMiejsca.add( rs.getString(2));
            }
            for( String rzadMiejsce : rzedyMiejsca )
            {
                String[] tmp = rzadMiejsce.split(Pattern.quote("|"));
                if( tmp[0].equals(String.valueOf(rzad)) && !zajeteMiejsca.contains(String.valueOf(tmp[1])) )
                {
                    zajeteMiejsca.add(tmp[1]);
                }
            }
            
            miejsca = new Object[iloscMiejsc-zajeteMiejsca.size()];
            
            int j = 0;
            for( int i=1; i<=miejsca.length+zajeteMiejsca.size(); i++ )
            {
                if( !zajeteMiejsca.contains(String.valueOf(i)) )
                {
                    miejsca[j] = i;
                    j++;
                }
            }
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
        return miejsca;
    }
    
    public int rezerwujLubKupLot( String wybranaOpcja, Integer IDUzytkownika, String IDLotu, String wybraneMiejsce, String wybranaKlasa, String cena ) throws SQLException
    {
        int isInserted = 0;
        try
        {
            connection = dbConnector.setConnection();
            String lot = null;
            String klasa = null;
            if( wybranaKlasa.equals( SamolotBean.KLASA_EKONOMICZNA ) )
            {
                klasa = "E";
            }
            else if( wybranaKlasa.equals( SamolotBean.KLASA_EKONOMICZNA_PREMIUM ) )
            {
                klasa = "EP";
            }
            else if( wybranaKlasa.equals( SamolotBean.KLASA_BIZNES ) )
            {
                klasa = "B";
            }
            else if( wybranaKlasa.equals( SamolotBean.KLASA_PIERWSZA ) )
            {
                klasa = "P";
            }
            if( wybranaOpcja.contains("rezerwuj") )
            {
                lot = REZERWACJA_LOTU;
                ps = connection.prepareStatement( lot );
                ps.setObject(1, IDUzytkownika);
                ps.setObject(2, IDLotu);
                ps.setObject(3, wybraneMiejsce);
                ps.setObject(4, klasa);
                isInserted = ps.executeUpdate();
            }
            else if( wybranaOpcja.contains("kup") )
            {
                lot = REZERWACJA_LOTU;
                ps = connection.prepareStatement( lot );
                ps.setObject(1, IDUzytkownika);
                ps.setObject(2, IDLotu);
                ps.setObject(3, wybraneMiejsce);
                ps.setObject(4, klasa);
                ps.setObject(5, cena);
                isInserted = ps.executeUpdate();
            }
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
        return isInserted;
    }
}
