package com.Kosmos.Planety;

import com.Kosmos.Model.CialoNiebieskie;
import com.Kosmos.Model.Deklinacja;
import com.Kosmos.Model.ObiektKosmiczny;
import com.Kosmos.Model.RodzajCialaNiebieskiego;
import com.Listy.Lista;

import java.util.Scanner;


public class Planeta extends CialoNiebieskie
{

    public RodzajPlanety rodzajPlanety;
    public boolean czyZawieraTlen;
    public boolean czyZawieraWode;

    
    public static Planeta ZIEMIA = new Planeta("Ziemia",new Deklinacja("0:0:0"),5.972E24,6371,288, RodzajPlanety.Skalista,true,true);
    public static Planeta MARS = new Planeta("Mars",new Deklinacja("0:0:0"),6.39E23,3389.5f,210.372222f, RodzajPlanety.Skalista,false,true);


    public Planeta()
    {
        przedrostek="OK:CN:P:";

    }

    public static Planeta NowaPlaneta(){
        Scanner scanner = new Scanner(System.in);
        Planeta temp=null;

        String nazwa;
        Deklinacja deklinacja;
        double masa;
        float promien;
        float temperatura;
        RodzajPlanety rodzaj = null;
        boolean czyAtmosferaTlenowa;
        boolean czyZawieraWode;

        boolean loop = true;
        while (loop){
            System.out.print("Wpisz nazwę\n\t-> ");
            nazwa = scanner.nextLine();

            System.out.print("Wpisz deklinację układu w formacie 'SS:MM.MM:SS.SS' (stopnie:minuty:sekundy)\n\t->");
            deklinacja = new Deklinacja(scanner.nextLine());

            System.out.print("Wpisz masę (w kilogramach)\n\t-> ");
            masa = ObiektKosmiczny.StringToFloat(scanner.nextLine());

            System.out.print("Wpisz promień (w kilometrach)\n\t-> ");
            promien = ObiektKosmiczny.StringToFloat(scanner.nextLine());


            System.out.print("Wpisz temperaturę (w kelvinach)\n\t-> ");
            temperatura = ObiektKosmiczny.StringToFloat(scanner.nextLine());

            switch (Lista.Pojedyncza("Wybierz rodzaj planety:","Skalista","Gazowa")){
                case 0:
                    rodzaj=RodzajPlanety.Skalista;
                    break;
                case 1:
                    rodzaj=RodzajPlanety.Gazowa;
                    break;
            }

            System.out.print("Czy atmosfera zawiera tlen?\n(TAK/NIE)\t-> ");
            czyAtmosferaTlenowa = scanner.nextLine().toUpperCase().compareTo("TAK")==0;

            System.out.print("Czy na planecie występuje woda?\n(TAK/NIE)\t-> ");
            czyZawieraWode = scanner.nextLine().toUpperCase().compareTo("TAK")==0;

            temp = new Planeta(nazwa,deklinacja,masa,promien,temperatura,rodzaj,czyAtmosferaTlenowa,czyZawieraWode);
            System.out.println("\n"+temp.toStringShort());

            switch (Lista.Pojedyncza("Czy powyższe dane się zgadzają?","Tak","Nie","Anuluj dodawanie")){
                case 0:
                    loop=false;
                    break;
                case 2:
                    return null;
            }
        }
    return temp;
    }

    public Planeta(String nazwa, Deklinacja deklinacja, double masa, float promien, float temperaturaKelvin, RodzajPlanety rodzajPlanety,
                   boolean czyZawieraTlen, boolean czyZawieraWode)
    {
        super("P:", nazwa, deklinacja, masa, promien, RodzajCialaNiebieskiego.Planeta, temperaturaKelvin);
        this.rodzajPlanety = rodzajPlanety;
        this.czyZawieraTlen = czyZawieraTlen;
        this.czyZawieraWode = czyZawieraWode;
    }


    /**
     * @return true, gdy planeta ma warunki podobne do ziemii na tyle, by była na niej możliwa jakaś forma życia
     */
    public boolean czyZiemioPodobna() {
        return (rodzajPlanety==RodzajPlanety.Skalista && (czyZawieraTlen||czyZawieraWode) && temperaturaKelvin>183 && temperaturaKelvin<330
        && GetPrzyspieszenieGrawitacyjne()>=1.0 && GetPrzyspieszenieGrawitacyjne()<=25.0);
    }


    @Override
    public String toStringShort() {
        String rodzaj ="";
        switch (rodzajPlanety){
            case Skalista -> rodzaj = "Planeta skalista";
            case Gazowa -> rodzaj = "Planeta gazowa";
            case Karłowata -> rodzaj = "Planeta karłowata";
        }

        return super.toStringShort() + "\t"+rodzaj+ "\t" +(czyZawieraTlen?"Atmosfera zawiera tlen":"Atmosfera beztlenowa")+"\t"
                +(czyZawieraWode?"Zawiera wodę":"Brak wody");
    }

    /**
     * @return
     */
    @Override
    protected CialoNiebieskie edytowanieCiala()
    {
        Scanner scanner = new Scanner(System.in);
        Planeta temp=null;

        String nazwa;
        Deklinacja deklinacja;
        double masa;
        float promien;
        float temperatura;
        RodzajPlanety rodzaj = null;
        boolean czyAtmosferaTlenowa;
        boolean czyZawieraWode;

        boolean loop = true;
        while (loop){
            temp=null;
            System.out.print("Wpisz nazwę\t(domyślnie '"+this.nazwa+"')\n(pozostaw puste by zostawić domyślną wartość)\n\t-> ");
            nazwa = edytowanaWartosc(scanner.nextLine(),this.nazwa);

            System.out.print("Wpisz deklinację układu w formacie 'SS:MM.MM:SS.SS' (stopnie:minuty:sekundy)\t(domyślnie '"+this.deklinacja+"')\n(pozostaw puste by zostawić domyślną wartość)\n\t-> ");
            String t=scanner.nextLine();
            if(t.compareTo("")==0){
                deklinacja=this.deklinacja;
            }else{
                deklinacja = new Deklinacja(t);
            }

            System.out.print("Wpisz masę (w kilogramach)\t(domyślnie '"+this.masa+"')\n(pozostaw puste by zostawić domyślną wartość)\n\t-> ");
            t=scanner.nextLine();
            if(t.compareTo("")==0){
                masa=this.masa;
            }else{
                masa = ObiektKosmiczny.StringToFloat(t);
            }

            System.out.print("Wpisz promień (w kilometrach)\t(domyślnie '"+this.promien+"')\n(pozostaw puste by zostawić domyślną wartość)\n\t-> ");
            t=scanner.nextLine();
            if(t.compareTo("")==0){
                promien=this.promien;
            }else{
                promien = ObiektKosmiczny.StringToFloat(t);
            }


            System.out.print("Wpisz temperaturę (w kelvinach)\t(domyślnie '"+this.temperaturaKelvin+"')\n(pozostaw puste by zostawić domyślną wartość)\n\t-> ");
            t=scanner.nextLine();
            if(t.compareTo("")==0){
                temperatura=this.temperaturaKelvin;
            }else{
                temperatura = ObiektKosmiczny.StringToFloat(t);
            }


            switch (Lista.Pojedyncza("Wybierz rodzaj planety\t(domyślnie '"+this.rodzajPlanety+"'):","Skalista","Gazowa")){
                case 0:
                    rodzaj=RodzajPlanety.Skalista;
                    break;
                case 1:
                    rodzaj=RodzajPlanety.Gazowa;
                    break;
            }

            System.out.print("Czy atmosfera zawiera tlen?");
            if(this.czyZawieraTlen){
                System.out.print("\t(domyślnie 'TAK')");
            }else{
                System.out.print("\t(domyślnie 'NIE')");
            }
            System.out.print("\n(TAK/NIE)\t-> ");
            czyAtmosferaTlenowa = scanner.nextLine().toUpperCase().compareTo("TAK")==0;

            System.out.print("Czy na planecie występuje woda?");
            if(this.czyZawieraWode){
                System.out.print("\t(domyślnie 'TAK')");
            }else{
                System.out.print("\t(domyślnie 'NIE')");
            }
            System.out.print("\n(TAK/NIE)\t-> ");
            czyZawieraWode = scanner.nextLine().toUpperCase().compareTo("TAK")==0;

            temp = new Planeta(nazwa,deklinacja,masa,promien,temperatura,rodzaj,czyAtmosferaTlenowa,czyZawieraWode);
            System.out.println("\n"+temp.toStringShort());

            switch (Lista.Pojedyncza("Czy powyższe dane się zgadzają?","Tak","Nie","Anuluj dodawanie")){
                case 0:
                    loop=false;
                    break;
                case 2:
                    return null;
            }
        }
        return temp;
    }

    @Override
    public String toString() {
        String rodzaj ="";
        switch (rodzajPlanety){
            case Skalista -> rodzaj = "Planeta skalista";
            case Gazowa -> rodzaj = "Planeta gazowa";
            case Karłowata -> rodzaj = "Planeta karłowata";
        }

        return super.toString() + "\t"+rodzaj+"\t" + (czyZawieraWode?"Zawiera wodę":"Nie zawiera wody") + "\t" +(czyZawieraTlen?"Atmosfera zawiera tlen":"Atmosfera beztlenowa");
    }
}