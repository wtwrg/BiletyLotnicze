/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Narzedzia;

import Beany.AdresBean;
import Beany.KontaktBean;
import Beany.UzytkownikBean;
import Beany.DokumentBean;
import Beany.WiadomoscBean;
import Wzorce.SingletonUzytkownik;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrycja
 */
public class Wiadomosci {
    
    private final static String DODAJ_WIADOMOSC = "INSERT INTO `wiadomosci` (`WDM_UZT_ID_NADAWCY`, `WDM_UZT_ID_ODBIOCY`, `WDM_TEMAT`, `WDM_TRESC`, `WDM_TYP`) VALUES (?, ?, ?, ?, ?)";
    private final static String POBRANIE_WIADOMOSCI = "SELECT `WDM_ID`, `WDM_UZT_ID_ODBIOCY`, `WDM_UZT_ID_NADAWCY`, `WDM_TEMAT`, `WDM_TRESC`, `WDM_DATA`, `WDM_TYP` FROM `wiadomosci` WHERE `WDM_UZT_ID_ODBIOCY`=?";
    Connection connection = null;
    DBConnector dbConnector = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    NarzedziaBazyDanych narzedziaBazyDanych = null;
    
    public Wiadomosci()
    {
        dbConnector = new DBConnector();
        narzedziaBazyDanych = new NarzedziaBazyDanych();
    }
    
    public int wyslijWiadomosc(int nadawca, int odbiorca, String temat, String tresc, String typ) throws SQLException {
        int isInserted = 0;
        connection = dbConnector.setConnection();
        try 
        {
            ps = connection.prepareStatement( DODAJ_WIADOMOSC );
            ps.setObject(1, nadawca);
            ps.setObject(2, odbiorca);
            ps.setObject(3, temat);
            ps.setObject(4, tresc);
            ps.setObject(5, typ);
            isInserted = ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Wiadomosci.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            connection.close();
            ps.close();
        }
        return isInserted;
    } 
    
    public List<WiadomoscBean> pobierzWiadomosci(int idUzytkownika) throws SQLException
    {
        connection = dbConnector.setConnection();
        List<WiadomoscBean> listaWiadomosciBean = new ArrayList<WiadomoscBean>();
        try
        {
            ps = connection.prepareStatement( POBRANIE_WIADOMOSCI );
            ps.setObject(1, idUzytkownika);
            rs = ps.executeQuery();
            listaWiadomosciBean = narzedziaBazyDanych.ustawWiadomosci( rs );
            
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
        return listaWiadomosciBean;
    }
    
    public String pobierzWiadomoscPoTypie(String typ) {
        String wiadomosc = "";
        switch(typ) {
            case "1":
                wiadomosc = "Nowa rezerwacja/wykupienie lotu.";
                break;
            case "2":
                wiadomosc = "Anulowanie rezerwacja/wykupienie lotu.";
                break;   
            case "3":
                wiadomosc = "Wygenerowanie nowego potwierdzenia pdf.";
                break;
            case "4":
                wiadomosc = "Anulacja lotu.";
                break;
            case "5":
                wiadomosc = "Edycja lotu.";
                break;
        }
        return wiadomosc;
    }
}
