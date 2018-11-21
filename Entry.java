package com.example.netactivity;

public class Entry {
    public final String kod;
    public final String mena;
    public final int mnozstvi;
    public final double kurz;
    public final String zeme;
 
    //TODO  rozsirit dalsi udaje, ktere se budou vest pro kazdou menu
    //TODO  upravit konstruktor
            
    Entry(String kod, String mena, int mnozstvi, double kurz, String zeme) {
        this.kod = kod;
        this.mena = mena;
        this.mnozstvi = mnozstvi;
        this.kurz = kurz;
        this.zeme = zeme;
    }
}
