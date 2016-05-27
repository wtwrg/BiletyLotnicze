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
public class Uzytkownicy {
    
    public static final String UAKTUALNIJ_CZY_BLOKADA = "UPDATE uzytkownicy SET UZT_CZY_BLOKADA=? WHERE UZT_ID=?";
    public static final String UAKTUALNIJ_CZY_ZMINA_HASLA = "UPDATE uzytkownicy SET UZT_CZY_ZMIANA_HASLA=? WHERE UZT_ID=?";
    private final static String POBRANIE_UZYTKOWNIKOW = "SELECT UZT_ID, UZT_LOGIN, UZT_IMIE, UZT_NAZWISKO, UZT_ADRES_EMAIL, UZT_PLEC, UZT_PESEL," +
            "UZT_CZY_ADM, UZT_CZY_BLOKADA, UZT_HASLO, UZT_CZY_ZMIANA_HASLA, UZT_SALDO, UZT_DATA FROM uzytkownicy";
    private final static String POBRANIE_UZYTKOWNIKOW_BEZ_ADMINOW = "SELECT UZT_ID, UZT_LOGIN, UZT_IMIE, UZT_NAZWISKO, UZT_ADRES_EMAIL, UZT_PLEC, UZT_PESEL," +
            "UZT_CZY_ADM, UZT_CZY_BLOKADA, UZT_HASLO, UZT_CZY_ZMIANA_HASLA, UZT_SALDO, UZT_DATA FROM uzytkownicy WHERE UZT_CZY_ADM=0";
     private final static String POBRANIE_ADMINISTRATOROW = "SELECT UZT_ID, UZT_LOGIN, UZT_IMIE, UZT_NAZWISKO, UZT_ADRES_EMAIL, UZT_PLEC, UZT_PESEL," +
            "UZT_CZY_ADM, UZT_CZY_BLOKADA, UZT_HASLO, UZT_CZY_ZMIANA_HASLA, UZT_SALDO, UZT_DATA FROM uzytkownicy WHERE UZT_CZY_ADM=1";
    private final static String POBRANIE_UZYTKOWNIA_DO_LOGOWANIA = "SELECT UZT_HASLO WHERE UZT_LOGIN = ?";
    private final static String MAKSYMALNE_ID_UZYTKOWNIKA = "SELECT max(`UZT_ID`) FROM `uzytkownicy`";
    private final static String DODAJ_UZYTKOWNIKA = "INSERT INTO `uzytkownicy` (`UZT_LOGIN`, `UZT_IMIE`, `UZT_NAZWISKO`,`UZT_ADRES_EMAIL`, `UZT_PLEC`, `UZT_PESEL`, `UZT_CZY_ADM`, `UZT_CZY_BLOKADA`, `UZT_HASLO`, `UZT_CZY_ZMIANA_HASLA`, `UZT_SALDO`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String DODAJ_ADRES = "INSERT INTO `adresy` (`ADR_UZT_ID`, `ADR_ULICA`, `ADR_NR_DOMU`,`ADR_NR_MIESZKANIA`, `ADR_MIASTO`, `ADR_KRAJ`) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String DODAJ_KONTAKT = "INSERT INTO `kontakty` (`KNT_UZT_ID`, `KNT_EMAIL`, `KNT_TELEFON`) VALUES (?, ?, ?)";
    private final static String DODAJ_DOKUMENT = "INSERT INTO `dokumenty` (`DKM_UZT_ID`, `DKM_TYP`, `DKM_NUMER`) VALUES (?, ?, ?)";
    
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
    
    public List<UzytkownikBean> pobierzUzytkownikow() throws SQLException
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
    
    public List<UzytkownikBean> pobierzUzytkownikowBezAdminow() throws SQLException
    {
        connection = dbConnector.setConnection();
        List<UzytkownikBean> listaUzytkownikBean = new ArrayList<UzytkownikBean>();
        try
        {
            ps = connection.prepareStatement( POBRANIE_UZYTKOWNIKOW_BEZ_ADMINOW );
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
    
    public List<UzytkownikBean> pobierzAdministratorow() throws SQLException
    {
        connection = dbConnector.setConnection();
        List<UzytkownikBean> listaUzytkownikBean = new ArrayList<UzytkownikBean>();
        try
        {
            ps = connection.prepareStatement( POBRANIE_ADMINISTRATOROW );
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
    
    public boolean logowanie (String login, String haslo) throws SQLException
    {
        boolean zalogowany = false;
        List<UzytkownikBean> listaUzytkownikowBean = pobierzUzytkownikow();

        for( UzytkownikBean user : listaUzytkownikowBean )
        {
            if(((user.getUzytkownikLogin().equals(login) ) || user.getUzytkownikAdresEmail().equals(login)) && (user.getUzytkownikHaslo().equals(haslo) ) ) {
                zalogowany = true;
                SingletonUzytkownik.pobierzInstancje().ustawUzytkownik(user);
            }
        }
        return zalogowany;
    }
    public int rejestracja (UzytkownikBean uzytkownikBean, AdresBean adresBean, KontaktBean kontaktBean, DokumentBean dokumentBean) throws SQLException 
    {
        connection = dbConnector.setConnection();
        int isInserted = 0;
        try
        {
            ps = connection.prepareStatement( DODAJ_UZYTKOWNIKA );
            ps.setObject(1, uzytkownikBean.getUzytkownikLogin());
            ps.setObject(2, uzytkownikBean.getUzytkownikImie());
            ps.setObject(3, uzytkownikBean.getUzytkownikNazwisko());
            ps.setObject(4, uzytkownikBean.getUzytkownikAdresEmail());
            ps.setObject(5, uzytkownikBean.getUzytkownikPlec());
            ps.setObject(6, uzytkownikBean.getUzytkownikPESEL());
            ps.setObject(7, uzytkownikBean.isUzytkownikCzyAdministrator());
            ps.setObject(8, uzytkownikBean.isUzytkownikCzyZablokowany());
            ps.setObject(9, uzytkownikBean.getUzytkownikHaslo());
            ps.setObject(10, uzytkownikBean.isUzytkownikCzyZmianaHasla());
            ps.setObject(11, uzytkownikBean.getUzytkownikSaldo());
            isInserted = ps.executeUpdate();
            if(isInserted > 0) {
                ps = connection.prepareStatement( MAKSYMALNE_ID_UZYTKOWNIKA );
                rs = ps.executeQuery();
                if(rs.next()) {
                    int idUzytkownika = rs.getInt(1);
                    ps = connection.prepareStatement( DODAJ_ADRES);
                    ps.setObject(1, idUzytkownika);
                    ps.setObject(2, adresBean.getAdresUlica());
                    ps.setObject(3, adresBean.getAdresNrDomu());
                    ps.setObject(4, adresBean.getAdresNrMieszkania());
                    ps.setObject(5, adresBean.getAdresMiasto());
                    ps.setObject(6, adresBean.getAdresKraj());
                    isInserted = ps.executeUpdate();
                    
                    ps = connection.prepareStatement( DODAJ_KONTAKT);
                    ps.setObject(1, idUzytkownika);
                    ps.setObject(2, kontaktBean.getKontaktEmail());
                    ps.setObject(3, kontaktBean.getKontaktTelefon());
                    isInserted = ps.executeUpdate();
                    
                    ps = connection.prepareStatement( DODAJ_DOKUMENT);
                    ps.setObject(1, idUzytkownika);
                    ps.setObject(2, dokumentBean.getDokumentTyp());
                    ps.setObject(3, dokumentBean.getDokumentNumer());
                    isInserted = ps.executeUpdate();
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
        return isInserted;
    }
    public boolean czyIstniejeUzytkownik (String login, String adresEmail) throws SQLException
    {
        boolean istnieje = false;
        List<UzytkownikBean> listaUzytkownikowBean = pobierzUzytkownikow();

        for( UzytkownikBean user : listaUzytkownikowBean )
        {
            if((user.getUzytkownikLogin().equals(login) ) && (user.getUzytkownikAdresEmail().equals(adresEmail) ) ) {
                istnieje = true;
            }
        }
        return istnieje;
    }
}
