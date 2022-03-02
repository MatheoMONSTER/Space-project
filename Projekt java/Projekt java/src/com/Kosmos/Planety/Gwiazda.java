package com.Kosmos.Planety;

import com.Kosmos.Model.CialoNiebieskie;
import com.Kosmos.Model.Deklinacja;
import com.Kosmos.Model.ObiektKosmiczny;
import com.Kosmos.Model.RodzajCialaNiebieskiego;
import com.Listy.Lista;

import java.util.Scanner;


public class Gwiazda extends CialoNiebieskie
{

    public double jasnosc;


    public static Gwiazda SLONCE = new Gwiazda("Słońce",new Deklinacja("0:0:0"),989E30,696340,5778,1);


    public Gwiazda()
    {
        przedrostek="OK:CN:G:";

    }

    public static Gwiazda NowaGwiazda(){
        Scanner scanner = new Scanner(System.in);
        Gwiazda temp=null;

        String nazwa;
        Deklinacja deklinacja;
        double masa;
        float promien;
        float temperatura;
        double jasnosc;

        boolean loop = true;
        while (loop){
            System.out.print("Wpisz nazwę\n\t->");
            nazwa = scanner.nextLine();

            System.out.print("Wpisz deklinację układu w formacie 'SS:MM.MM:SS.SS' (stopnie:minuty:sekundy)\n\t->");
            deklinacja = new Deklinacja(scanner.nextLine());

            System.out.print("Wpisz masę (w kilogramach)\n\t->");
            masa = ObiektKosmiczny.StringToFloat(scanner.nextLine());


            System.out.print("Wpisz promień (w kilometrach)\n\t->");
            promien = ObiektKosmiczny.StringToFloat(scanner.nextLine());


            System.out.print("Wpisz temperaturę (w kelvinach)\n\t->");
            temperatura = ObiektKosmiczny.StringToFloat(scanner.nextLine());


            System.out.print("Wpisz jasność (w jasności słońca L☉)\n\t->");
            jasnosc = ObiektKosmiczny.StringToFloat(scanner.nextLine());


            temp = new Gwiazda(nazwa,deklinacja,masa,promien,temperatura,jasnosc);
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

    public Gwiazda(String nazwa, Deklinacja deklinacja, double masa, float promien, float temperaturaKelvin, double jasnosc)
    {
        super("G:", nazwa, deklinacja, masa, promien, RodzajCialaNiebieskiego.Gwiazda, temperaturaKelvin);
        this.jasnosc = jasnosc;
    }


    /**
     * @return zwraca typ widmowy gwiazdy
     */
    public TypWidmowy TypWidmowy()
    {
        TypWidmowy typ = null;

        if(temperaturaKelvin < 500){
            typ = TypWidmowy.Y;
        }
        else if(temperaturaKelvin >= 500 && temperaturaKelvin < 1400){
            typ = TypWidmowy.T;
        }
        else if(temperaturaKelvin >= 1400 && temperaturaKelvin < 2400){
            typ = TypWidmowy.L;
        }
        else if(temperaturaKelvin >= 2400 && temperaturaKelvin < 3700){
            typ = TypWidmowy.M;
        }
        else if(temperaturaKelvin >= 3700 && temperaturaKelvin < 5200){
            typ = TypWidmowy.K;
        }
        else if(temperaturaKelvin >= 5200 && temperaturaKelvin < 6000){
            typ = TypWidmowy.G;
        }
        else if(temperaturaKelvin >= 6000 && temperaturaKelvin < 7500){
            typ = TypWidmowy.F;
        }
        else if(temperaturaKelvin >= 7500 && temperaturaKelvin < 10000){
            typ = TypWidmowy.A;
        }
        else if(temperaturaKelvin >= 10000 && temperaturaKelvin < 30000){
            typ = TypWidmowy.B;
        }
        else if(temperaturaKelvin >= 30000){
            typ = TypWidmowy.O;
        }

        return typ;
    }


    @Override
    public String toStringShort() {
        return super.toStringShort() + "\t"+jasnosc+ "L☉\t" + TypWidmowy();
    }

    /**
     * @return
     */
    @Override
    protected CialoNiebieskie edytowanieCiala()
    {
        Scanner scanner = new Scanner(System.in);
        Gwiazda temp=null;

        String nazwa;
        Deklinacja deklinacja;
        double masa;
        float promien;
        float temperatura;
        double jasnosc;

        boolean loop = true;
        while (loop){
            System.out.print("Wpisz nazwę\n\t->");
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


            System.out.print("Wpisz jasność (w jasności słońca L☉)\t(domyślnie '"+this.jasnosc+"')\n(pozostaw puste by zostawić domyślną wartość)\n\t->");
            t=scanner.nextLine();
            if(t.compareTo("")==0){
                jasnosc=this.jasnosc;
            }else{
                jasnosc = ObiektKosmiczny.StringToFloat(t);
            }

            temp = new Gwiazda(nazwa,deklinacja,masa,promien,temperatura,jasnosc);
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
        return super.toString() + "\t"+jasnosc+ "L☉\t" + TypWidmowy();
    }
}
