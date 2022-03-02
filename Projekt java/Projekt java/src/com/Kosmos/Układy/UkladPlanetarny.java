package com.Kosmos.Układy;

import com.Kosmos.Model.CialoNiebieskie;
import com.Kosmos.Model.Deklinacja;
import com.Kosmos.Model.ObiektKosmiczny;
import com.Kosmos.Model.RodzajCialaNiebieskiego;

import java.util.ArrayList;
import java.util.List;

public class UkladPlanetarny extends ObiektKosmiczny
{
    /**
     * NIE UŻYWAĆ - PUBLICZNE TYLKO NA CELE SERIALIZACJI
     */
    public List<CialoNiebieskie> cialaNiebieskie;

    public UkladPlanetarny(String nazwa, List<CialoNiebieskie> cialaNiebieskie)
    {
        super("UP:", nazwa, CialoNiebieskie.SredniaDeklinacji(cialaNiebieskie), CialoNiebieskie.SumaMas(cialaNiebieskie));
        this.cialaNiebieskie = cialaNiebieskie;
    }

    public UkladPlanetarny()
    {
        przedrostek="OK:UP:";
        cialaNiebieskie = new ArrayList<>();
    }

    public int iloscCial(){
        return cialaNiebieskie.size();
    }

    public UkladPlanetarny(String nazwa, Deklinacja deklinacja)
    {
        super("UK:", nazwa, deklinacja, 0);
        this.cialaNiebieskie = new ArrayList<>();
    }

    /**
     * @param newCialo ciało niebieskie które chcemy dodać
     */
    public void DodajCialoNiebieskie(CialoNiebieskie newCialo)
    {
        cialaNiebieskie.add(newCialo);
        masa += newCialo.masa;
        deklinacja = CialoNiebieskie.SredniaDeklinacji(cialaNiebieskie);
    }

    /**
     * @param doUsuniecia ciało niebieskie które chcemy usunąć
     * @return true gdy udało się usunąć  |  false gdy się nie udało (nie ma takiego ciała w tym układzie)
     */
    public boolean UsunCialoNiebieskie(CialoNiebieskie doUsuniecia)
    {
        if (cialaNiebieskie.contains(doUsuniecia))
        {
            cialaNiebieskie.remove(doUsuniecia);
            masa -= doUsuniecia.masa;
            deklinacja = CialoNiebieskie.SredniaDeklinacji(cialaNiebieskie);
            return true;
        } else
        {
            return false;
        }
    }


    /**
     * @param doEdycji ciało które edytujemy
     * @param poEdycji ciało po edycji
     */
    public void EdytujCialoNiebieskie(CialoNiebieskie doEdycji, CialoNiebieskie poEdycji)
    {
        UsunCialoNiebieskie(doEdycji);
        DodajCialoNiebieskie(poEdycji);
    }


    public boolean czyMa(RodzajCialaNiebieskiego warunek)
    {
        boolean planeta = false;

        for (var x :
                cialaNiebieskie)
        {
            if (x.rodzajCialaNiebieskiego == warunek)
            {
                planeta = true;
                break;
            }
        }

        return planeta;
    }


    @Override
    public String toString()
    {
        String pauza = "-----------------------------";
        String ret = super.toString();
        if (czyMa(RodzajCialaNiebieskiego.Gwiazda))
        {
            ret += "\n"+pauza + "GWIAZDY" + pauza+"\n";
            for (CialoNiebieskie x :
                    cialaNiebieskie)
            {
                if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Gwiazda)
                {
                    ret += "\t" + x+"\n";
                }
            }
        } else
        {
            ret += "\nBRAK GWIAZD\n";
        }


        if (czyMa(RodzajCialaNiebieskiego.Planeta))
        {
            ret += "\n"+pauza + "PLANETY" + pauza+"\n";
            for (CialoNiebieskie x :
                    cialaNiebieskie)
            {
                if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Planeta)
                {
                    ret += "\t" + x+"\n";
                }
            }
        } else
        {
            ret += "\nBRAK PLANET\n";
        }

        return ret;
    }

    @Override
    public String toStringShort()
    {
        String pauza = "-----------------------------";
        String ret = super.toStringShort();
        if (czyMa(RodzajCialaNiebieskiego.Gwiazda))
        {
            ret += "\n"+pauza + "GWIAZDY" + pauza+"\n";
            for (CialoNiebieskie x :
                    cialaNiebieskie)
            {
                if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Gwiazda)
                {
                    ret += "\t" + x.toStringShort()+"\n";
                }
            }
        } else
        {
            ret += "\n\t---BRAK GWIAZD---\n";
        }


        if (czyMa(RodzajCialaNiebieskiego.Planeta))
        {
            ret += "\n"+pauza + "PLANETY" + pauza+"\n";
            for (CialoNiebieskie x :
                    cialaNiebieskie)
            {
                if (x.rodzajCialaNiebieskiego == RodzajCialaNiebieskiego.Planeta)
                {
                    ret += "\t" + x.toStringShort()+"\n";
                }
            }
        } else
        {
            ret += "\n\t---BRAK PLANET---\n";
        }

        return ret;
    }
}