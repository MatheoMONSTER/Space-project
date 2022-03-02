package com.Kosmos.Układy;

import com.Kosmos.Model.CialoNiebieskie;
import com.Kosmos.Model.RodzajCialaNiebieskiego;
import com.Kosmos.Planety.Gwiazda;
import com.Kosmos.Planety.Planeta;
import com.Kosmos.Planety.RodzajPlanety;
import com.Kosmos.Planety.TypWidmowy;
import com.Listy.Lista;
import com.Projekt.Main;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Uklady implements Serializable
{
    public List<UkladPlanetarny> uklady;

    public Uklady(List<UkladPlanetarny> uklady)
    {
        this.uklady = uklady;
    }

    public Uklady()
    {
        uklady = new ArrayList<>();
    }

    /**
     * Służy do wyszukiwania Układów planetarnych za pomocą podanego klucza
     * @param klucz 0 - wszystkie układy zawierające planety | 1 - wszystkie układy zawierające gwiazdy  | 2 - wszystkie planety | 3 - wszystkie gwiazdy | 4 - planety ziemiopodobne |
     *             5 - planety z tlenem | 6 - planety z wodą  | 7 - planety skaliste  | 8 - planety gazowe  | 9 - planety karłowate | 10 - gwiazdy  o typie widmowym X
     */
    public Uklady Wyszukaj(int klucz)
    {
        Uklady ret = new Uklady();

        switch (klucz){
            case 0: //wszystkie układy zawierające planety
                for (var u :
                        uklady)
                {
                    if(u.czyMa(RodzajCialaNiebieskiego.Planeta))
                        ret.uklady.add(u);
                }
                break;

            case 1: //wszystkie układy zawierające gwiazdy
                for (var u :
                        uklady)
                {
                    if(u.czyMa(RodzajCialaNiebieskiego.Gwiazda))
                        ret.uklady.add(u);
                }
                break;

            case 2: //wszystkie planety
                for (var u :
                        uklady)
                {
                    for (var x :
                            u.cialaNiebieskie)
                    {
                        if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Planeta)
                        {
                            UkladPlanetarny ukladPlanetarny = null;

                            //czy układ z tą nazwą został już wcześniej zdefiniowany
                            for (var r :
                                    ret.uklady) {
                                if(r.nazwa == u.nazwa)
                                {
                                    //jeśli tak dodaj odpowiadającą planetę do niego
                                    ukladPlanetarny = r;
                                    ukladPlanetarny.cialaNiebieskie.add(x);
                                }
                            }

                            //jeśli nie zdefiniuj nowy pusty układ z tą samą nazwą i dodaj odpowiadającą planetę do niego
                            if(ukladPlanetarny == null)
                            {
                                ukladPlanetarny = new UkladPlanetarny(u.nazwa, new ArrayList<CialoNiebieskie>(){{ add(x); }});

                                ret.uklady.add(ukladPlanetarny);
                            }
                        }
                    }
                }
                break;

            case 3: //wszystkie gwiazdy
                for (var u :
                        uklady)
                {
                    for (var x :
                            u.cialaNiebieskie)
                    {
                        if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Gwiazda)
                        {
                            UkladPlanetarny ukladPlanetarny = null;

                            //czy układ z tą nazwą został już wcześniej zdefiniowany
                            for (var r :
                                    ret.uklady) {
                                if(r.nazwa == u.nazwa)
                                {
                                    //jeśli tak dodaj odpowiadającą gwiazdę do niego
                                    ukladPlanetarny = r;
                                    ukladPlanetarny.cialaNiebieskie.add(x);
                                }
                            }

                            //jeśli nie zdefiniuj nowy pusty układ z tą samą nazwą i dodaj odpowiadającą gwiazdę do niego
                            if(ukladPlanetarny == null)
                            {
                                ukladPlanetarny = new UkladPlanetarny(u.nazwa, new ArrayList<CialoNiebieskie>(){{ add(x); }});

                                ret.uklady.add(ukladPlanetarny);
                            }
                        }
                    }
                }
                break;


            case 4: //planety ziemiopodobne
                for (var u :
                        uklady)
                {
                    for (var x :
                            u.cialaNiebieskie)
                    {
                        if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Planeta)
                        {
                            Planeta p = (Planeta)x;

                            if(p.czyZiemioPodobna())
                            {
                                UkladPlanetarny ukladPlanetarny = null;

                                //czy układ z tą nazwą został już wcześniej zdefiniowany
                                for (var r :
                                        ret.uklady) {
                                    if(r.nazwa == u.nazwa)
                                    {
                                        //jeśli tak dodaj odpowiadającą planetę do niego
                                        ukladPlanetarny = r;
                                        ukladPlanetarny.cialaNiebieskie.add(x);
                                    }
                                }

                                //jeśli nie zdefiniuj nowy pusty układ z tą samą nazwą i dodaj odpowiadającą planetę do niego
                                if(ukladPlanetarny == null)
                                {
                                    ukladPlanetarny = new UkladPlanetarny(u.nazwa, new ArrayList<CialoNiebieskie>(){{ add(x); }});

                                    ret.uklady.add(ukladPlanetarny);
                                }
                            }
                        }
                    }
                }
                break;

            case 5: //wszystkie planety z tlenem
                ret.uklady.add(new UkladPlanetarny());

                for (var u :
                        uklady)
                {
                    for (var x :
                            u.cialaNiebieskie)
                    {
                        if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Planeta)
                        {
                            Planeta p = (Planeta)x;

                            if(p.czyZawieraTlen)
                            {
                                UkladPlanetarny ukladPlanetarny = null;

                                //czy układ z tą nazwą został już wcześniej zdefiniowany
                                for (var r :
                                        ret.uklady) {
                                    if(r.nazwa == u.nazwa)
                                    {
                                        //jeśli tak dodaj odpowiadającą planetę do niego
                                        ukladPlanetarny = r;
                                        ukladPlanetarny.cialaNiebieskie.add(x);
                                    }
                                }

                                //jeśli nie zdefiniuj nowy pusty układ z tą samą nazwą i dodaj odpowiadającą planetę do niego
                                if(ukladPlanetarny == null)
                                {
                                    ukladPlanetarny = new UkladPlanetarny(u.nazwa, new ArrayList<CialoNiebieskie>(){{ add(x); }});

                                    ret.uklady.add(ukladPlanetarny);
                                }
                            }
                        }
                    }
                }
                break;

            case 6: //wszystkie planety z wodą
                ret.uklady.add(new UkladPlanetarny());

                for (var u :
                        uklady)
                {
                    for (var x :
                            u.cialaNiebieskie)
                    {
                        if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Planeta)
                        {
                            Planeta p = (Planeta)x;

                            if(p.czyZawieraWode)
                            {
                                UkladPlanetarny ukladPlanetarny = null;

                                //czy układ z tą nazwą został już wcześniej zdefiniowany
                                for (var r :
                                        ret.uklady) {
                                    if(r.nazwa == u.nazwa)
                                    {
                                        //jeśli tak dodaj odpowiadającą planetę do niego
                                        ukladPlanetarny = r;
                                        ukladPlanetarny.cialaNiebieskie.add(x);
                                    }
                                }

                                //jeśli nie zdefiniuj nowy pusty układ z tą samą nazwą i dodaj odpowiadającą planetę do niego
                                if(ukladPlanetarny == null)
                                {
                                    ukladPlanetarny = new UkladPlanetarny(u.nazwa, new ArrayList<CialoNiebieskie>(){{ add(x); }});

                                    ret.uklady.add(ukladPlanetarny);
                                }
                            }
                        }
                    }
                }
                break;

            case 7: //wszystkie planety skaliste
                ret.uklady.add(new UkladPlanetarny());

                for (var u :
                        uklady)
                {
                    for (var x :
                            u.cialaNiebieskie)
                    {
                        if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Planeta)
                        {
                            Planeta p = (Planeta)x;

                            if(p.rodzajPlanety == RodzajPlanety.Skalista)
                            {
                                UkladPlanetarny ukladPlanetarny = null;

                                //czy układ z tą nazwą został już wcześniej zdefiniowany
                                for (var r :
                                        ret.uklady) {
                                    if(r.nazwa == u.nazwa)
                                    {
                                        //jeśli tak dodaj odpowiadającą planetę do niego
                                        ukladPlanetarny = r;
                                        ukladPlanetarny.cialaNiebieskie.add(x);
                                    }
                                }

                                //jeśli nie zdefiniuj nowy pusty układ z tą samą nazwą i dodaj odpowiadającą planetę do niego
                                if(ukladPlanetarny == null)
                                {
                                    ukladPlanetarny = new UkladPlanetarny(u.nazwa, new ArrayList<CialoNiebieskie>(){{ add(x); }});

                                    ret.uklady.add(ukladPlanetarny);
                                }
                            }
                        }
                    }
                }
                break;

            case 8: //wszystkie planety gazowe
                ret.uklady.add(new UkladPlanetarny());

                for (var u :
                        uklady)
                {
                    for (var x :
                            u.cialaNiebieskie)
                    {
                        if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Planeta)
                        {
                            Planeta p = (Planeta)x;

                            if(p.rodzajPlanety == RodzajPlanety.Gazowa)
                            {
                                UkladPlanetarny ukladPlanetarny = null;

                                //czy układ z tą nazwą został już wcześniej zdefiniowany
                                for (var r :
                                        ret.uklady) {
                                    if(r.nazwa == u.nazwa)
                                    {
                                        //jeśli tak dodaj odpowiadającą planetę do niego
                                        ukladPlanetarny = r;
                                        ukladPlanetarny.cialaNiebieskie.add(x);
                                    }
                                }

                                //jeśli nie zdefiniuj nowy pusty układ z tą samą nazwą i dodaj odpowiadającą planetę do niego
                                if(ukladPlanetarny == null)
                                {
                                    ukladPlanetarny = new UkladPlanetarny(u.nazwa, new ArrayList<CialoNiebieskie>(){{ add(x); }});

                                    ret.uklady.add(ukladPlanetarny);
                                }
                            }
                        }
                    }
                }
                break;

            case 9: //wszystkie planety karłowate
                ret.uklady.add(new UkladPlanetarny());

                for (var u :
                        uklady)
                {
                    for (var x :
                            u.cialaNiebieskie)
                    {
                        if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Planeta)
                        {
                            Planeta p = (Planeta)x;

                            if(p.rodzajPlanety == RodzajPlanety.Karłowata)
                            {
                                UkladPlanetarny ukladPlanetarny = null;

                                //czy układ z tą nazwą został już wcześniej zdefiniowany
                                for (var r :
                                        ret.uklady) {
                                    if(r.nazwa == u.nazwa)
                                    {
                                        //jeśli tak dodaj odpowiadającą planetę do niego
                                        ukladPlanetarny = r;
                                        ukladPlanetarny.cialaNiebieskie.add(x);
                                    }
                                }

                                //jeśli nie zdefiniuj nowy pusty układ z tą samą nazwą i dodaj odpowiadającą planetę do niego
                                if(ukladPlanetarny == null)
                                {
                                    ukladPlanetarny = new UkladPlanetarny(u.nazwa, new ArrayList<CialoNiebieskie>(){{ add(x); }});

                                    ret.uklady.add(ukladPlanetarny);
                                }
                            }
                        }
                    }
                }
                break;

            case 10: //wszystkie gwiazdy o danym typie widmowym
                TypWidmowy[] typyWidmowe = TypWidmowy.values();
                String[] typyWidmoweStr = new String[typyWidmowe.length];
                for (int i=0; i<typyWidmoweStr.length;i++){
                    typyWidmoweStr[i] = typyWidmowe[i].toString();
                }

                int typ = Lista.Pojedyncza("Gwiazdy o jakim typie widmowym chcesz wyświetlić?",typyWidmoweStr);

                Main.Przerwa();

                ret.uklady.add(new UkladPlanetarny());

                for (var u :
                        uklady)
                {
                    for (var x :
                            u.cialaNiebieskie)
                    {
                        if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Gwiazda)
                        {
                            Gwiazda g = (Gwiazda)x;

                            if(g.TypWidmowy() == typyWidmowe[typ])
                            {
                                UkladPlanetarny ukladPlanetarny = null;

                                //czy układ z tą nazwą został już wcześniej zdefiniowany
                                for (var r :
                                        ret.uklady) {
                                    if(r.nazwa == u.nazwa)
                                    {
                                        //jeśli tak dodaj odpowiadającą gwiazdę do niego
                                        ukladPlanetarny = r;
                                        ukladPlanetarny.cialaNiebieskie.add(x);
                                    }
                                }

                                //jeśli nie zdefiniuj nowy pusty układ z tą samą nazwą i dodaj odpowiadającą gwiazdę do niego
                                if(ukladPlanetarny == null)
                                {
                                    ukladPlanetarny = new UkladPlanetarny(u.nazwa, new ArrayList<CialoNiebieskie>(){{ add(x); }});

                                    ret.uklady.add(ukladPlanetarny);
                                }
                            }
                        }
                    }
                }
                break;
        }

        return ret;
    }

    public void PokazUklad()
    {
        String[] uklayStr = new String[uklady.size()];
        for (int i  =0; i<uklayStr.length;i++){
            uklayStr[i] = uklady.get(i).nazwa;

        }

        int s = Lista.Pojedyncza("Który układ chcesz wyświetlić?",uklayStr);

        System.out.println("\n"+uklady.get(s).toStringShort());
    }

    public  String[] UkladyToArray()
    {
        String[] uklayStr = new String[uklady.size()];
        for (int i  =0; i<uklayStr.length;i++){
            uklayStr[i] = uklady.get(i).nazwa;

        }

        return uklayStr;
    }

    @Override
    public String toString() {
        String output = "";

        boolean isEmpty = true;

        for (var u :
                uklady)
        {
            for (var x :
                    u.cialaNiebieskie)
            {
                if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Gwiazda)
                {
                    Gwiazda g = (Gwiazda)x;

                    output += g.toString() + "\t->\t" + u.nazwa + "\n";

                    isEmpty = false;
                }
                else if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Planeta)
                {
                    Planeta p = (Planeta)x;

                    output += p.toString() + "\t->\t" + u.nazwa + "\n";

                    isEmpty = false;
                }
            }
        }

        if(isEmpty)
        {
            output += "BRAK DANYCH\n";
        }

        return output;
    }
    public String toStringShort() {
        String output = "";

        boolean isEmpty = true;

        for (var u :
                uklady)
        {
            for (var x :
                    u.cialaNiebieskie)
            {
                if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Gwiazda)
                {
                    Gwiazda g = (Gwiazda)x;

                    output += g.toStringShort() + "\t->\t" + u.nazwa + "\n";

                    isEmpty = false;
                }
                else if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Planeta)
                {
                    Planeta p = (Planeta)x;

                    output += p.toStringShort() + "\t->\t" + u.nazwa + "\n";

                    isEmpty = false;
                }
            }
        }

        if(isEmpty)
        {
            output += "BRAK DANYCH\n";
        }

        return output;
    }
}
