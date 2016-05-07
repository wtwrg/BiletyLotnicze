package Narzedzia;


import Beany.SamolotBean;
import Beany.LotniskoBean;
import Beany.LotBean;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                lotyBean.setOdlotPrzylot( rs.getString( 2 ) );
                lotyBean.setCenaKlasyEkonomicznej( rs.getFloat( 3 ) );
                lotyBean.setCenaKlasyEkonomicznejPremium( rs.getFloat( 4 ) );
                lotyBean.setCenaKlasyBiznes( rs.getFloat( 5 ) );
                lotyBean.setCenaKlasyPierwszej( rs.getFloat( 6 ) );
                lotyBean.setDataOdlotu( rs.getDate( 7 ) );
                lotyBean.setDataPrzylotu( rs.getDate( 8 ) );
                lotyBean.setLotniskoID( rs.getInt( 9 ) );
                lotyBean.setSamolotID( rs.getInt( 10 ) );
                
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
}
