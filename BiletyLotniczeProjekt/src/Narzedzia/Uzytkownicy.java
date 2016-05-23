/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Narzedzia;

import Beany.UzytkownikBean;
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
public class Uzytkownicy {
    
    public static final String UAKTUALNIJ_CZY_BLOKADA = "UPDATE uzytkownicy SET UZT_CZY_BLOKADA=? WHERE UZT_ID=?";
    public static final String UAKTUALNIJ_CZY_ZMINA_HASLA = "UPDATE uzytkownicy SET UZT_CZY_ZMIANA_HASLA=? WHERE UZT_ID=?";
    private final static String POBRANIE_UZYTKOWNIKOW = "SELECT UZT_ID, UZT_IMIE, UZT_NAZWISKO, UZT_PLEC, UZT_PESEL," +
            "UZT_CZY_ADM, UZT_CZY_BLOKADA, UZT_HASLO, UZT_CZY_ZMIANA_HASLA, UZT_SALDO, UZT_DATA FROM UZYTKOWNICY ";
    
    Connection connection = null;
    DBConnector dbConnector = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    NarzedziaBazyDanych narzedziaBazyDanych = null;
    
    public Uzytkownicy()
    {
        dbConnector = new DBConnector();
        narzedziaBazyDanych = new NarzedziaBazyDanych();
    }
    
    public int uaktualnijCzyBlokada( Integer IDUzytkownika, Boolean czyBlokada) throws SQLException
    {
        int isInserted = 0;
        connection = dbConnector.setConnection();
        try 
        {
            ps = connection.prepareStatement( UAKTUALNIJ_CZY_BLOKADA );
            ps.setObject(1, czyBlokada);
            ps.setObject(2, IDUzytkownika);
            isInserted = ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Uzytkownicy.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            connection.close();
            ps.close();
        }
        return isInserted;
    }
    
    public int uaktualnijCzyZmianaHasla( Integer IDUzytkownika, Boolean czyZmianaHasla) throws SQLException
    {
        int isInserted = 0;
        connection = dbConnector.setConnection();
        try 
        {
            ps = connection.prepareStatement( UAKTUALNIJ_CZY_ZMINA_HASLA );
            ps.setObject(1, czyZmianaHasla);
            ps.setObject(2, IDUzytkownika);
            isInserted = ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Uzytkownicy.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            connection.close();
            ps.close();
        }
        return isInserted;
    }
    
    private List<UzytkownikBean> pobierzUzytkownikow() throws SQLException
    {
        connection = dbConnector.setConnection();
        List<UzytkownikBean> listaUzytkownikBean = new ArrayList<UzytkownikBean>();
        try
        {
            ps = connection.prepareStatement( POBRANIE_UZYTKOWNIKOW );
            rs = ps.executeQuery();
            listaUzytkownikBean = narzedziaBazyDanych.ustawUzytkownikow( rs );
            
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
        return listaUzytkownikBean;
    }
    
    
    public List<Object[]> pokazUzytkownikowAdmin() throws SQLException
    {
         List<UzytkownikBean> listaUzytkownikowBean = pobierzUzytkownikow();
         
          List<Object[]> uzytkownicy = new ArrayList<Object[]>();
        
        for( UzytkownikBean user : listaUzytkownikowBean )
        {
            Object[] uzytkownik = null;
            uzytkownik = new Object[]{ user.getUzytkownikID(), user.getUzytkownikImie(), user.getUzytkownikNazwisko(), user.isUzytkownikCzyZablokowany(), user.isUzytkownikCzyZmianaHasla()};
            uzytkownicy.add(uzytkownik);
        }
        return  uzytkownicy;
    }
}
