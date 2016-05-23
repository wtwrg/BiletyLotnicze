package Narzedzia;


import Beany.SamolotBean;
import Beany.LotniskoBean;
import Beany.LotBean;
import Beany.RezerwacjaBean;
import Beany.UzytkownikBean;
import Beany.ZakupBean;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amarylis
 */
public class NarzedziaBazyDanych 
{
    public List<LotBean> ustawLoty( ResultSet rs )
    {
        List<LotBean> listaLotyBean = new ArrayList<LotBean>();
        try
        {
            while( rs.next() )
            {
                LotBean lotyBean = new LotBean();
                
                lotyBean.setLotID( rs.getInt( 1 ) );
                lotyBean.setLotOdlotPrzylot( rs.getString( 2 ) );
                lotyBean.setLotCenaKlasyEkonomicznej( rs.getFloat( 3 ) );
                lotyBean.setLotCenaKlasyEkonomicznejPremium( rs.getFloat( 4 ) );
                lotyBean.setLotCenaKlasyBiznes( rs.getFloat( 5 ) );
                lotyBean.setLotCenaKlasyPierwszej( rs.getFloat( 6 ) );
                lotyBean.setLotDataOdlotu( rs.getString( 7 ) );
                lotyBean.setLotDataPrzylotu( rs.getString( 8 ) );
                lotyBean.setLotLotniskoID( rs.getInt( 9 ) );
                lotyBean.setLotSamolotID( rs.getInt( 10 ) );
                
                listaLotyBean.add( lotyBean );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        return listaLotyBean;
    }
    
    public List<SamolotBean> ustawSamoloty( ResultSet rs )
    {
        List<SamolotBean> listaSamolotyBean = new ArrayList<SamolotBean>();
        try
        {
            while( rs.next() )
            {
                SamolotBean samolotBean = new SamolotBean();
                
                samolotBean.setSamolotID( rs.getInt( 1 ) );
                samolotBean.setLinieLotnicze( rs.getString( 2 ) );
                samolotBean.setIloscRzedow( rs.getInt( 3 ) );
                samolotBean.setIloscMiejscWRzedzie( rs.getInt( 4 ) );
                samolotBean.setKlasaEkonomiczna( rs.getBoolean( 5 ) );
                samolotBean.setKlasaEkonomicznaPremium( rs.getBoolean( 6 ) );
                samolotBean.setKlasaBiznes( rs.getBoolean( 7 ) );
                samolotBean.setKlasaPierwsza( rs.getBoolean( 8 ) );
                
                listaSamolotyBean.add( samolotBean );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        return listaSamolotyBean;
    }
    
    public List<LotniskoBean> ustawLotnisko( ResultSet rs )
    {
        List<LotniskoBean> listaLotniskoBean = new ArrayList<LotniskoBean>();
        try
        {
            while( rs.next() )
            {
                LotniskoBean lotniskoBean = new LotniskoBean();
                
                lotniskoBean.setLotniskoID( rs.getInt( 1 ) );
                lotniskoBean.setLotniskoKraj( rs.getString( 2 ) );
                lotniskoBean.setLotniskoMiasto( rs.getString( 3 ) );
                lotniskoBean.setLotniskoNazwa( rs.getString( 4 ) );
                
                listaLotniskoBean.add( lotniskoBean );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        return listaLotniskoBean;
    }
    
    public List<ZakupBean> ustawZakup( ResultSet rs )
    {
        List<ZakupBean> listaZakupowBean = new ArrayList<ZakupBean>();
        try
        {
            while( rs.next() )
            {
                ZakupBean zakupBean = new ZakupBean();
                
                zakupBean.setZakupID( rs.getInt( 1 ) );
                zakupBean.setZakupUzytkownikID( rs.getInt( 2 ) );
                zakupBean.setZakupLotID( rs.getInt( 3 ) );
                zakupBean.setZakupData( rs.getString( 4 ) );
                zakupBean.setZakupRzadMejsce( rs.getString( 5 ) );
                zakupBean.setZakupKlasa( rs.getString( 6 ) );
                zakupBean.setZakupKwota( rs.getFloat( 7 ) );
                
                listaZakupowBean.add( zakupBean );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        return listaZakupowBean;
    }
    
    public List<RezerwacjaBean> ustawRezerwacje( ResultSet rs )
    {
        List<RezerwacjaBean> listaRezerwacjiBean = new ArrayList<RezerwacjaBean>();
        try
        {
            while( rs.next() )
            {
                RezerwacjaBean rezerwacjaBean = new RezerwacjaBean();
                
                rezerwacjaBean.setRezerwacjaID( rs.getInt( 1 ) );
                rezerwacjaBean.setRezerwacjaUzytkownikID( rs.getInt( 2 ) );
                rezerwacjaBean.setRezerwacjaLotID( rs.getInt( 3 ) );
                rezerwacjaBean.setRezerwacjaData( rs.getString( 4 ) );
                rezerwacjaBean.setRezerwacjaRzadMiejsce( rs.getString( 5 ) );
                rezerwacjaBean.setRezerwacjaKlasa( rs.getString( 6 ) );
                rezerwacjaBean.setRezerwacjaKwota( rs.getFloat( 7 ) );
                
                listaRezerwacjiBean.add( rezerwacjaBean );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        return listaRezerwacjiBean;
    }
    
    public List<UzytkownikBean> ustawUzytkownikow( ResultSet rs )
    {
        List<UzytkownikBean> listaUzytkownikowBean = new ArrayList<UzytkownikBean>();
        try
        {
            while( rs.next() )
            {
                UzytkownikBean uzytkownikBean = new UzytkownikBean();
                
                uzytkownikBean.setUzytkownikID(rs.getInt( 1 ));
                uzytkownikBean.setUzytkownikImie(rs.getString( 2 ));
                uzytkownikBean.setUzytkownikNazwisko(rs.getString( 3 ));
                uzytkownikBean.setUzytkownikPlec(rs.getString(4));
                uzytkownikBean.setUzytkownikPESEL(rs.getString(5));
                uzytkownikBean.setUzytkownikCzyAdministrator(rs.getBoolean(6));
                uzytkownikBean.setUzytkownikCzyZablokowany(rs.getBoolean(7));
                uzytkownikBean.setUzytkownikHaslo(rs.getString(8));
                uzytkownikBean.setUzytkownikCzyZmianaHasla(rs.getBoolean(9));
                uzytkownikBean.setUzytkownikSaldo(rs.getFloat(10));
                uzytkownikBean.setUzytkownikData(rs.getDate(11));
                
                listaUzytkownikowBean.add( uzytkownikBean );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        return listaUzytkownikowBean;
    }
}
