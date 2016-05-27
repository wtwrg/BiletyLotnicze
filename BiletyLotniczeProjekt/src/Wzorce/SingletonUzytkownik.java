/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wzorce;

import Beany.UzytkownikBean;

/**
 *
 * @author Przemysław
 */
public class SingletonUzytkownik {
    private static SingletonUzytkownik instancja;
    private UzytkownikBean uzytkownik;
    private SingletonUzytkownik() {
    }
    
    public static SingletonUzytkownik pobierzInstancje() {
        if (instancja == null) {
            instancja = new SingletonUzytkownik();
        }
        return instancja;
    }

    public void ustawUzytkownik(UzytkownikBean uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public UzytkownikBean pobierzUzytkownik() {
        return uzytkownik;
    }
}
