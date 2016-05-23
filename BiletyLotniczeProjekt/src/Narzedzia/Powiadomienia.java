
package Narzedzia;

import Beany.WiadomoscBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Powiadomienia 
{
    private final String POWIADOMIENIE = "INSERT INTO wiadomosci (WDM_UZT_ID_ODBIOCY, WDM_UZT_ID_NADAWCY, WDM_TEMAT, WDM_TRESC, WDM_DATA, WDM_TYP) VALUES (?,?,?,?,NOW(),?)";
    
    Connection connection = null;
    DBConnector dbConnector = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public Powiadomienia()
    {
        dbConnector = new DBConnector();
    }
    
    public void potwierdzenieRezerwacjiKupna( String wybranaOpcja, String IDUzytkownika ) throws SQLException
    {
        String temat = null;
        String tresc = WiadomoscBean.TRESC_NOWA_AKCJA_UZYTKOWNIK;
        tresc = tresc.replaceFirst("\\?", IDUzytkownika);
        
        if( wybranaOpcja.contains("rezerwuj") )
        {
            temat = WiadomoscBean.TEMAT_NOWA_REZERWACJA;
            tresc = tresc.replaceFirst("\\?", WiadomoscBean.TRESC_NOWA_REZERWACJA);
        }
        else if( wybranaOpcja.contains("kup") )
        {
            temat = WiadomoscBean.TEMAT_NOWY_ZAKUP;
            tresc = tresc.replaceFirst("\\?", WiadomoscBean.TRESC_NOWY_ZAKUP);
        }
        try
        {
            connection = dbConnector.setConnection();
            ps = connection.prepareStatement( POWIADOMIENIE );
            ps.setObject(1, WiadomoscBean.WIADOMOSC_ODBIORCA_WSZYSCY_ADMINISTRATORZY);
            ps.setObject(2, WiadomoscBean.WIADOMOSC_NADAWCA_SYSTEM);
            ps.setObject(3, temat);
            ps.setObject(4, tresc);
            ps.setObject(5, WiadomoscBean.WIADOMOSC_TYP_1);
            
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
    public void anulowanieLotuPrzezUzytkownika( Integer IDLotu, Integer IDUzytkownika ) throws SQLException
    {
        String temat = WiadomoscBean.TEMAT_ANULOWANIE;
        String tresc = WiadomoscBean.TRESC_NOWA_AKCJA_UZYTKOWNIK;
        tresc = tresc.replaceFirst("\\?", String.valueOf(IDUzytkownika));
        tresc = tresc.replaceFirst("\\?", WiadomoscBean.TRESC_ANULOWANIE);
        tresc = tresc.replaceFirst("\\?", String.valueOf(IDLotu));
        
        try
        {
            connection = dbConnector.setConnection();
            ps = connection.prepareStatement( POWIADOMIENIE );
            ps.setObject(1, WiadomoscBean.WIADOMOSC_ODBIORCA_WSZYSCY_ADMINISTRATORZY);
            ps.setObject(2, WiadomoscBean.WIADOMOSC_NADAWCA_SYSTEM);
            ps.setObject(3, temat);
            ps.setObject(4, tresc);
            ps.setObject(5, WiadomoscBean.WIADOMOSC_TYP_2);
            
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
    
    public void wygenerowanieNowegoPotwierdzeniaPDF( Integer IDUzytkownika ) throws SQLException
    {
        String temat = WiadomoscBean.TEMAT_PDF;
        String tresc = WiadomoscBean.TRESC_PDF;
        tresc = tresc.replaceFirst("\\?", String.valueOf(IDUzytkownika));
        
        try
        {
            connection = dbConnector.setConnection();
            ps = connection.prepareStatement( POWIADOMIENIE );
            ps.setObject(1, WiadomoscBean.WIADOMOSC_ODBIORCA_WSZYSCY_ADMINISTRATORZY);
            ps.setObject(2, WiadomoscBean.WIADOMOSC_NADAWCA_SYSTEM);
            ps.setObject(3, temat);
            ps.setObject(4, tresc);
            ps.setObject(5, WiadomoscBean.WIADOMOSC_TYP_3);
            
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
}
