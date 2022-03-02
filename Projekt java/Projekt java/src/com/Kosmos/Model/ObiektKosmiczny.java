package com.Kosmos.Model;

import java.io.Serializable;

public abstract class ObiektKosmiczny implements Serializable
{
    public String nazwaKatalogowa;
    public String nazwa;
    public Deklinacja deklinacja;
    /**
     * W kilogramach
     */
    public double masa;

    protected String przedrostek;


    public ObiektKosmiczny(String przedrostek,String nazwa, Deklinacja deklinacja, double masa)
    {
        this.przedrostek = "OK:"+przedrostek;
        this.nazwaKatalogowa = this.przedrostek+nazwa;
        this.nazwa = nazwa;
        this.deklinacja =  deklinacja;
        this.masa = masa;
    }

    /**
     * @param nowaNazwa nowa nazwa dla obiektu
     */
    public void SetNazwa(String nowaNazwa)
    {
        nazwaKatalogowa=przedrostek+nowaNazwa;
        nazwa=nowaNazwa;
    }


    public ObiektKosmiczny(){przedrostek="OK:";}



    public String toStringShort()
    {
        return nazwa+"\t"+masa+" Kg\t"+deklinacja;
    }

    @Override
    public String toString()
    {
        return nazwaKatalogowa+"\t"+masa+" Kg\t"+deklinacja;
    }

    public static float StringToFloat(String str){
        float x;
        try{
            x = Float.parseFloat(str);
        }catch (NumberFormatException e){
            x=0.0f;
            System.out.println("...= 0");
        }
        return x;
    }

}
