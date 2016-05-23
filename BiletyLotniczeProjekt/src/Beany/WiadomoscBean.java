package Beany;


import java.sql.Date;

public class WiadomoscBean 
{
    /*
    0 - wiadomość tworzona przed administratora
    1 - nowa rezerwacja/wykupienie lotu
    2 - anulowanie rezerwacji/wykupienia lotu
    3 - wygenerowanie nowego potwierdzenia PDF
    4 - anunlowanie lotu
    5 - edycja lotu
    */
    public static final String WIADOMOSC_TYP_0 = "0";
    public static final String WIADOMOSC_TYP_1 = "1";
    public static final String WIADOMOSC_TYP_2 = "2";
    public static final String WIADOMOSC_TYP_3 = "3";
    public static final String WIADOMOSC_TYP_4 = "4";
    public static final String WIADOMOSC_TYP_5 = "5";
    
    public static final String WIADOMOSC_ODBIORCA_WSZYSCY_ADMINISTRATORZY = "99";
    public static final String WIADOMOSC_NADAWCA_SYSTEM = "999";
    
    public static final String TEMAT_NOWY_ZAKUP = "Nowy zakup";
    public static final String TEMAT_NOWA_REZERWACJA = "Nowa rezerwacja";
    public static final String TEMAT_ANULOWANIE = "Anulowanie lotu przez użytkownika";
    public static final String TEMAT_PDF = "Potwierdzenie PDF";
    
    public static final String TRESC_NOWA_AKCJA_UZYTKOWNIK = "Uzytkownik ID ? dokonał ?.";
    public static final String TRESC_NOWA_REZERWACJA = "nowej rezerwacji";
    public static final String TRESC_NOWY_ZAKUP = "nowego zakupu";
    public static final String TRESC_ANULOWANIE = "anulowania lotu ID ?";
    public static final String TRESC_PDF = "Wygenerowano nowe potwierdzenie PDF dla uzytkownika ID ?.";
    
    Integer wiadomoscID;
    Integer wiadomoscIDOdbiorcy;
    Integer wiadomoscIDNadawcy;
    String wiadomoscTemat;
    String wiadomoscTresc;
    Date wiadomoscData;
    String wiadomoscTyp;

    public Integer getWiadomoscID() {
        return wiadomoscID;
    }

    public void setWiadomoscID(Integer wiadomoscID) {
        this.wiadomoscID = wiadomoscID;
    }

    public Integer getWiadomoscIDOdbiorcy() {
        return wiadomoscIDOdbiorcy;
    }

    public void setWiadomoscIDOdbiorcy(Integer wiadomoscIDOdbiorcy) {
        this.wiadomoscIDOdbiorcy = wiadomoscIDOdbiorcy;
    }

    public Integer getWiadomoscIDNadawcy() {
        return wiadomoscIDNadawcy;
    }

    public void setWiadomoscIDNadawcy(Integer wiadomoscIDNadawcy) {
        this.wiadomoscIDNadawcy = wiadomoscIDNadawcy;
    }

    public String getWiadomoscTemat() {
        return wiadomoscTemat;
    }

    public void setWiadomoscTemat(String wiadomoscTemat) {
        this.wiadomoscTemat = wiadomoscTemat;
    }

    public String getWiadomoscTresc() {
        return wiadomoscTresc;
    }

    public void setWiadomoscTresc(String wiadomoscTresc) {
        this.wiadomoscTresc = wiadomoscTresc;
    }

    public Date getWiadomoscData() {
        return wiadomoscData;
    }

    public void setWiadomoscData(Date wiadomoscData) {
        this.wiadomoscData = wiadomoscData;
    }

    public String getWiadomoscTyp() {
        return wiadomoscTyp;
    }

    public void setWiadomoscTyp(String wiadomoscTyp) {
        this.wiadomoscTyp = wiadomoscTyp;
    }
    
    
}
