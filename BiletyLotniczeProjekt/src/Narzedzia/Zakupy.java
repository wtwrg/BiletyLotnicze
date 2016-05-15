
package Narzedzia;

import Beany.SamolotBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Zakupy 
{
    public static final String POBIERZ_DOSTEPNE_SRODKI = "SELECT UZT_SALDO FROM UZYTKOWNICY WHERE UZT_ID=?";
    public static final String UAKTUALNIJ_SRODKI = "UPDATE uzytkownicy SET UZT_SALDO=? WHERE UZT_ID=?";
    private final String REZERWACJA_LOTU = "INSERT INTO rezerwacje (RZR_UZT_ID, RZR_LOT_ID, RZR_DATA, RZR_RZAD_MIEJSCE, RZR_KLASA) VALUES (?,?,NOW(),?,?)";
    private final String KUPNO_LOTU = "INSERT INTO zakupy (ZKP_UZT_ID, ZKP_LOT_ID, ZKP_DATA, ZKP_RZAD_MIEJSCE, ZKP_KLASA, ZKP_KWOTA) VALUES (?,?,NOW(),?,?,?)";

    
    Connection connection = null;
    DBConnector dbConnector = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public Zakupy()
    {
        dbConnector = new DBConnector();
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
                ps = connection.prepareStatement( REZERWACJA_LOTU );
                ps.setObject(1, IDUzytkownika);
                ps.setObject(2, IDLotu);
                ps.setObject(3, wybraneMiejsce);
                ps.setObject(4, klasa);
                isInserted = ps.executeUpdate();
            }
            else if( wybranaOpcja.contains("kup") )
            {
                ps = connection.prepareStatement( KUPNO_LOTU );
                ps.setObject(1, IDUzytkownika);
                ps.setObject(2, IDLotu);
                ps.setObject(3, wybraneMiejsce);
                ps.setObject(4, klasa);
                ps.setObject(5, cena);
                isInserted = ps.executeUpdate();
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
}
