
package Narzedzia;

import Beany.RezerwacjaBean;
import Beany.SamolotBean;
import Beany.ZakupBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Zakupy 
{
    public static final String POBIERZ_DOSTEPNE_SRODKI = "SELECT UZT_SALDO FROM UZYTKOWNICY WHERE UZT_ID=?";
    public static final String UAKTUALNIJ_SRODKI = "UPDATE uzytkownicy SET UZT_SALDO=? WHERE UZT_ID=?";
    private final String REZERWACJA_LOTU = "INSERT INTO rezerwacje (RZR_UZT_ID, RZR_LOT_ID, RZR_DATA, RZR_RZAD_MIEJSCE, RZR_KLASA, RZR_KWOTA) VALUES (?,?,NOW(),?,?,?)";
    private final String KUPNO_LOTU = "INSERT INTO zakupy (ZKP_UZT_ID, ZKP_LOT_ID, ZKP_DATA, ZKP_RZAD_MIEJSCE, ZKP_KLASA, ZKP_KWOTA) VALUES (?,?,NOW(),?,?,?)";
    private final String POBRANIE_ZAKUPOW = "SELECT * FROM ZAKUPY WHERE ZKP_UZT_ID=?";
    private final String POBRANIE_REZERWACJI = "SELECT * FROM REZERWACJE WHERE RZR_UZT_ID=?";
    private final String POBIERZ_LOTNISKO_DLA_REZERWACJI = "SELECT LTN_NAZWA FROM LOTNISKO, LOTY, REZERWACJE WHERE LTN_ID=LOT_LOTNISKO_ID AND LOT_ID=RZR_LOT_ID AND RZR_ID=?";
    private final String POBIERZ_LOTNISKO_DLA_ZAKUPU = "SELECT LTN_NAZWA FROM LOTNISKO, LOTY, ZAKUPY WHERE LTN_ID=LOT_LOTNISKO_ID AND LOT_ID=ZKP_LOT_ID AND ZKP_ID=?";
    private final String USUN_REZERWACJE = "DELETE FROM REZERWACJE WHERE RZR_ID=?";
    private final String USUN_ZAKUP = "DELETE FROM ZAKUPY WHERE ZKP_ID=?";
    private final String USUN_PDF = "DELETE FROM POTWIERDZENIA_PDF WHERE PTW_ZKP_ID=?";

    public final static String ZAKUP = "Zakup";
    public final static String REZERWACJA = "Rezerwacja";
    
    Connection connection = null;
    DBConnector dbConnector = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    NarzedziaBazyDanych narzedziaBazyDanych = null;
    public static int last_inserted_id = 0;
    Loty loty;
    
    public Zakupy()
    {
        dbConnector = new DBConnector();
        narzedziaBazyDanych = new NarzedziaBazyDanych();
        loty = new Loty();
    }
    
    public float pobierzDostepneSrodki( Integer IDUzytkownika ) throws SQLException
    {
        connection = dbConnector.setConnection();
        float kwota = 0;
        try 
        {
            ps = connection.prepareStatement( POBIERZ_DOSTEPNE_SRODKI );
            ps.setObject(1, IDUzytkownika);
            rs = ps.executeQuery();
            while(rs.next())
            {
                kwota = rs.getFloat(1);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Zakupy.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            connection.close();
            ps.close();
            rs.close();
        }
        return kwota;
    }
    
    private void uaktualnijSrodki( Integer IDUzytkownika, float kwotaDoUaktualnienia, float saldo, boolean czyDodac ) throws SQLException
    {
        connection = dbConnector.setConnection();
        try 
        {
            ps = connection.prepareStatement( UAKTUALNIJ_SRODKI );
            if( czyDodac )
            {
                ps.setObject(1, saldo+kwotaDoUaktualnienia);
            }
            else
            {
                ps.setObject(1, saldo-kwotaDoUaktualnienia);
            }
            ps.setObject(2, IDUzytkownika);
            ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Zakupy.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            connection.close();
            ps.close();
        }
    }
    
    public int rezerwujLubKupLot( String wybranaOpcja, Integer IDUzytkownika, String IDLotu, String wybraneMiejsce, String wybranaKlasa, String cena ) throws SQLException
    {
        int isInserted = 0;
        try
        {
            float srodki = pobierzDostepneSrodki(IDUzytkownika);
            if( srodki < Float.valueOf(cena) )
            {
                return 3;
            }
            
            connection = dbConnector.setConnection();
            String klasa = wybranaKlasa;
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
                ps = connection.prepareStatement( REZERWACJA_LOTU );
                ps.setObject(1, IDUzytkownika);
                ps.setObject(2, IDLotu);
                ps.setObject(3, wybraneMiejsce);
                ps.setObject(4, klasa);
                ps.setObject(5, cena);
                isInserted = ps.executeUpdate();
            }
            else if( wybranaOpcja.contains("kup") )
            {
                ps = connection.prepareStatement( KUPNO_LOTU, Statement.RETURN_GENERATED_KEYS );
                ps.setObject(1, IDUzytkownika);
                ps.setObject(2, IDLotu);
                ps.setObject(3, wybraneMiejsce);
                ps.setObject(4, klasa);
                ps.setObject(5, cena);
                isInserted = ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if(rs.next())
                {
                    last_inserted_id = rs.getInt(1);
                }
                uaktualnijSrodki(IDUzytkownika, Float.valueOf(cena), srodki, false);
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
        }
        return isInserted;
    }
    
    public List<Object[]> pokazZakupy( Integer IDuzytkownika ) throws SQLException
    {
        List<ZakupBean> listaZakupow = pobierzZakupy( IDuzytkownika );
        List<RezerwacjaBean> listaRezerwacji = pobierzRezerwacje(IDuzytkownika );
        
        List<Object[]> zakupyIRezerwacje = new ArrayList<Object[]>();
        
        for( ZakupBean zakupBean : listaZakupow )
        {
            String dataLotu = loty.pobierzDateLotu(zakupBean.getZakupLotID());
            Object[] zakup = null;
            zakup = new Object[]{ ZAKUP, zakupBean.getZakupData(), dataLotu, pobierzLotniskoZakupu(zakupBean.getZakupID()), zakupBean.getZakupKwota(), zakupBean.getZakupLotID() };
            zakupyIRezerwacje.add(zakup);
        }
        
        for( RezerwacjaBean rezerwacjaBean : listaRezerwacji )
        {
            String dataLotu = loty.pobierzDateLotu(rezerwacjaBean.getRezerwacjaLotID());
            Object[] zakup = null;
            zakup = new Object[]{ REZERWACJA, rezerwacjaBean.getRezerwacjaData(), dataLotu,  pobierzLotniskoDlaRezerwacji(rezerwacjaBean.getRezerwacjaID()), rezerwacjaBean.getRezerwacjaKwota(), rezerwacjaBean.getRezerwacjaLotID() };
            zakupyIRezerwacje.add(zakup);
        }
        
        return zakupyIRezerwacje;
    }
    
    public List<ZakupBean> pobierzZakupy( Integer IDuzytkownika ) throws SQLException
    {
        connection = dbConnector.setConnection();
        List<ZakupBean> listaLotyBean = new ArrayList<ZakupBean>();
        try
        {
            ps = connection.prepareStatement( POBRANIE_ZAKUPOW );
            ps.setObject(1, IDuzytkownika);
            rs = ps.executeQuery();
            listaLotyBean = narzedziaBazyDanych.ustawZakup( rs );
            
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
    
    public List<RezerwacjaBean> pobierzRezerwacje( Integer IDuzytkownika ) throws SQLException
    {
        connection = dbConnector.setConnection();
        List<RezerwacjaBean> listaRezerwacjaBean = new ArrayList<RezerwacjaBean>();
        try
        {
            ps = connection.prepareStatement( POBRANIE_REZERWACJI );
            ps.setObject(1, IDuzytkownika);
            rs = ps.executeQuery();
            listaRezerwacjaBean = narzedziaBazyDanych.ustawRezerwacje(rs );
            
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
        return listaRezerwacjaBean;
    }
    
    private String pobierzLotniskoZakupu( Integer IDZakupu ) throws SQLException
    {
        connection = dbConnector.setConnection();
        String lotnisko = null;
        try
        {
            ps = connection.prepareStatement( POBIERZ_LOTNISKO_DLA_ZAKUPU );
            ps.setObject(1, IDZakupu);
            rs = ps.executeQuery();
            while( rs.next() )
            {
                lotnisko = rs.getString( 1 );
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
        return lotnisko;
    }
    
    private String pobierzLotniskoDlaRezerwacji( Integer IDRezerwacji ) throws SQLException
    {
        connection = dbConnector.setConnection();
        String lotnisko = null;
        try
        {
            ps = connection.prepareStatement( POBIERZ_LOTNISKO_DLA_REZERWACJI );
            ps.setObject(1, IDRezerwacji);
            rs = ps.executeQuery();
            while( rs.next() )
            {
                lotnisko = rs.getString( 1 );
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
        return lotnisko;
    }
    
    public void usunRezerwacje( Integer IDRezerwacji ) throws SQLException
    {
        connection = dbConnector.setConnection();
        try
        {
            ps = connection.prepareStatement( USUN_REZERWACJE );
            ps.setObject(1, IDRezerwacji);
            ps.executeUpdate();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            connection.close();
            ps.close();
        }
    }
    public void usunZakup( Integer IDZakupu, Integer IDUzytkownika, float kwotaDoUaktualnienia ) throws SQLException
    {
        connection = dbConnector.setConnection();
        try
        {
            ps = connection.prepareStatement( USUN_PDF );
            ps.setObject(1, IDZakupu);
            ps.executeUpdate();
            
            ps = connection.prepareStatement( USUN_ZAKUP );
            ps.setObject(1, IDZakupu);
            ps.executeUpdate();
            
             float srodki = pobierzDostepneSrodki(IDUzytkownika);
             uaktualnijSrodki( IDUzytkownika, kwotaDoUaktualnienia, srodki, true );
;
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            connection.close();
            ps.close();
        }
    }
}
