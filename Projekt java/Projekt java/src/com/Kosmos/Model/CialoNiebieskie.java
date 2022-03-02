package com.Kosmos.Model;

import java.io.Serializable;
import java.util.List;

public abstract class CialoNiebieskie extends ObiektKosmiczny implements Serializable
{
    public static final double StalaGrawitacji = 6.6743015E-11;

    /**
     * W kilometrach
     */
    public float promien;
    public RodzajCialaNiebieskiego rodzajCialaNiebieskiego;
    public float temperaturaKelvin;


    public CialoNiebieskie(String przedrostek,String nazwa, Deklinacja deklinacja, double masa, float promien,
                           RodzajCialaNiebieskiego rodzajCialaNiebieskiego, float temperaturaKelvin)
    {
        super("CN:"+przedrostek, nazwa, deklinacja, masa);
        this.promien = promien;
        this.rodzajCialaNiebieskiego = rodzajCialaNiebieskiego;
        this.temperaturaKelvin = temperaturaKelvin;
    }

    public CialoNiebieskie()
    {
        przedrostek="OK:CN:";

    }

    /**
     * @author Maciej Kuchcik
     * @return przyspieszenie grawitacyjne planety w m/s^2
     */
    public double GetPrzyspieszenieGrawitacyjne(){
        return StalaGrawitacji * masa / Math.pow(promien*1000.0,2.0);
    }


    /**
     * @author Michał Myszor
     * @param cialaNiebieskie lista ciał niebieskich, których deklinację chcemy uśrednić
     * @return średnia deklinacji
     */
    public static Deklinacja SredniaDeklinacji(List<CialoNiebieskie> cialaNiebieskie){

        Deklinacja sredniaDeklinacji = new Deklinacja("0:0:0");

        if(cialaNiebieskie.size() != 0)
        {
            Deklinacja deklinacjaCiala;

            int stopnie = 0;
            float minuty = 0;
            float sekundy = 0;

            for(int i=0; i<cialaNiebieskie.size(); i++)
            {
                deklinacjaCiala = cialaNiebieskie.get(i).deklinacja;

                stopnie += deklinacjaCiala.stopnie;
                minuty += deklinacjaCiala.minuty;
                sekundy += deklinacjaCiala.sekundy;
            }

            stopnie /= cialaNiebieskie.size();
            minuty /= cialaNiebieskie.size();
            sekundy /= cialaNiebieskie.size();

            sredniaDeklinacji = new Deklinacja(stopnie+":"+minuty+":"+sekundy);
        }

        return sredniaDeklinacji;
    }


    /**
     * @param cialaNiebieskie lista ciał niebieskich, których masę chcemy zsumować
     * @return zwraca masę pododanych ciał
     */
    public static float SumaMas(List<CialoNiebieskie> cialaNiebieskie){
        float masa=0;
        for (var x :
                cialaNiebieskie)
        {
            masa+=x.masa;
        }
        return masa;
    }

    @Override
    public String toString()
    {
        String ret = super.toString();

        ret += "\t"+promien+"km\t"+rodzajCialaNiebieskiego+"\t"+temperaturaKelvin+"K";

        return ret;
    }

    @Override
    public String toStringShort()
    {
        String ret = super.toStringShort();

        ret += "\t"+promien+"km\t"+temperaturaKelvin+"K\t"+GetPrzyspieszenieGrawitacyjne()+" m/s^2\t";

        return ret;
    }

    protected String edytowanaWartosc(String edit, String def){
        if(edit.compareTo("")==0){
            return def;
        }else{
            return edit;
        }
    }

    public CialoNiebieskie EdytowanieCialaNiebieskiego(){
        System.out.println("EDYTOWANIE CIAŁA NIEBIESKIEGO\t'"+nazwa+"'\n");
        return edytowanieCiala();
    }

    //metoda ma być zaimplementowana przez klasy dziedziczące
    protected abstract CialoNiebieskie edytowanieCiala();

}
