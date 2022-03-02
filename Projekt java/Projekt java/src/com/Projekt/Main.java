package com.Projekt;

import com.Kosmos.Model.CialoNiebieskie;
import com.Kosmos.Model.Deklinacja;
import com.Kosmos.Planety.Gwiazda;
import com.Kosmos.Planety.Planeta;
import com.Kosmos.Planety.RodzajPlanety;
import com.Kosmos.Układy.UkladPlanetarny;
import com.Kosmos.Układy.Uklady;
import com.Listy.Lista;
import com.Serializacja.XML;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static String FILENAME = "uklady.xml";

    public static void Przerwa()
    {
        System.out.print("\n----------------------------------------\n\n");
    }


    public static void main(String[] args)
    {
        // nie można używać Arrays.asList by uzupełniać Listy bo wyrzuca błędy
        // trzeba robić przez faktycznie 'add' do list osobno
        XML xml = new XML();





        /*


        INICJACJA PROGRAMU
            czytanie bazy układów z pliku bądź tworzenie nowej


         */


        Uklady U = null;
        try
        {
            U = (Uklady) xml.Czytaj(FILENAME);
        } catch (FileNotFoundException e)
        {
            U = new Uklady();
        }





        /*


        PROGRAM


         */
        Scanner scr = new Scanner(System.in);




        boolean _PROGRAM = true;
        while(_PROGRAM){
            Przerwa();

            switch (Lista.Pojedyncza("Menu główne: ","Układy planetarne","Filtruj ciała niebieskie","Wyłącz")){
                case 2: // WYŁĄCZENIE
                    _PROGRAM=false;
                    break;




                case 0: // Pokazanie układów

                    boolean _EDYCJA = true;
                    while (_EDYCJA)
                    {
                        Przerwa();

                        String[] UkladyOpcje = new String[U.uklady.size() + 2];
                        for (int i  =0; i<U.uklady.size();i++){
                            UkladyOpcje[i] = U.uklady.get(i).nazwa;

                        }
                        UkladyOpcje[UkladyOpcje.length-2]="Dodaj nowy";
                        UkladyOpcje[UkladyOpcje.length-1]="Wyjdź";


                        int w = Lista.Pojedyncza("Opcje układów planetarnych:",UkladyOpcje.length-3, UkladyOpcje);

                        // Wyjdź
                        if(w== UkladyOpcje.length-1){
                            _EDYCJA = false;
                        }else if(w== UkladyOpcje.length-2){ //DODAJ NOWY UKŁAD
                            Przerwa();



                            boolean _DODAWANIE = true;
                            while(_DODAWANIE)
                            {
                                System.out.println("DODAWANIE NOWEGO UKŁADU\n");



                                System.out.print("Wpisz nazwę układu planetarnego\n\t-> ");
                                String nazwa = scr.nextLine();
                                System.out.print("Wpisz deklinację układu w formacie 'SS:MM.MM:SS.SS' (stopnie:minuty:sekundy)\n\t-> ");
                                Deklinacja deklinacja = new Deklinacja(scr.nextLine());

                                UkladPlanetarny temp = new UkladPlanetarny(nazwa, deklinacja);

                                System.out.println(temp.toStringShort());
                                switch (Lista.Pojedyncza("Czy powyższe dane się zgadzają?", "Tak", "Nie","Anuluj"))
                                {
                                    case 0: //tak
                                        _DODAWANIE=false;
                                        U.uklady.add(temp);
                                        try
                                        {
                                            xml.Zapisz(FILENAME,U);
                                        } catch (FileNotFoundException e)
                                        {
                                            System.out.println("Coś poszło nie tak podczas zapisu pliku...");
                                        }
                                        break;
                                    case 1:     //nie
                                        break;
                                    case 2:     // anuluj
                                        _DODAWANIE=false;
                                        break;
                                }



                            }
                        }else{  // edytowanie istniejącego układu
                                //
                                //


                            UkladPlanetarny temp = U.uklady.get(w);
                            //System.out.println(temp);
                            boolean _E = true;
                            while (_E)
                            {
                                Przerwa();

                                System.out.println(temp.toStringShort());

                                switch (Lista.Pojedyncza("Wybierz co chcesz zrobić z tym układem:", 5, "Dodaj nową planetą", "Dodaj nową gwiazdę",
                                        "Edytuj istniejące ciało niebieskie", "Usuń istniejące ciało niebieskie","Edytuj nazwę układu","Usuń układ planetarny", "Wróć"))
                                {
                                    case 0:     //dodaj planetę
                                        Przerwa();
                                        System.out.println("DODAWANIE NOWEJ PLANETY\n");

                                        Planeta p = Planeta.NowaPlaneta();
                                        if(p!=null){
                                            temp.DodajCialoNiebieskie(p);

                                            try
                                            {
                                                xml.Zapisz(FILENAME,U);
                                            } catch (FileNotFoundException e)
                                            {
                                                System.out.println("Coś poszło nie tak podczas zapisu pliku...");
                                            }
                                        }
                                        break;
                                    case 1:     //dodaj gwiazde
                                        Przerwa();
                                        System.out.println("DODAWANIE NOWEJ GWIAZDY\n");

                                        Gwiazda g = Gwiazda.NowaGwiazda();
                                        if(g!=null){
                                            temp.DodajCialoNiebieskie(g);

                                            try
                                            {
                                                xml.Zapisz(FILENAME,U);
                                            } catch (FileNotFoundException e)
                                            {
                                                System.out.println("Coś poszło nie tak podczas zapisu pliku...");
                                            }
                                        }
                                        break;
                                    case 2:     //Edytuj ciało niebieskie
                                        Przerwa();

                                        if(temp.cialaNiebieskie.size()>0)
                                        {
                                            String[] ciala = new String[temp.cialaNiebieskie.size() + 1];
                                            for (int i = 0; i < ciala.length - 1; i++)
                                            {
                                                ciala[i] = temp.cialaNiebieskie.get(i).nazwa;

                                            }

                                            ciala[ciala.length - 1] = "Anuluj";

                                            int wyb = Lista.Pojedyncza("Wybierz ciało do edycji:", ciala.length - 2, ciala);


                                            if(wyb>temp.cialaNiebieskie.size()-1){
                                                System.out.println("WYJŚCIE");
                                            }else{
                                                Przerwa();
                                                CialoNiebieskie edytowane = temp.cialaNiebieskie.get(wyb);
                                                CialoNiebieskie _tempCialo = edytowane.EdytowanieCialaNiebieskiego();
                                                if(_tempCialo!=null){
                                                    temp.EdytujCialoNiebieskie(edytowane,_tempCialo);

                                                    try
                                                    {
                                                        xml.Zapisz(FILENAME,U);
                                                    } catch (FileNotFoundException e)
                                                    {
                                                        System.out.println("Coś poszło nie tak podczas zapisu pliku...");
                                                    }
                                                }
                                            }




                                        }else{
                                            System.out.println("Układ ten nie zawiera żadnych ciał niebieskich do edycji. Spróbuj najpierw dodać jakieś.");
                                        }
                                        break;
                                    case 3:     //usuń ciało niebieskie
                                        Przerwa();

                                        if(temp.cialaNiebieskie.size()>0)
                                        {
                                            String[] ciala = new String[temp.cialaNiebieskie.size() + 1];
                                            for (int i = 0; i < ciala.length - 1; i++)
                                            {
                                                ciala[i] = temp.cialaNiebieskie.get(i).nazwa;

                                            }

                                            ciala[ciala.length - 1] = "Anuluj";

                                            int u = Lista.Pojedyncza("Wybierz ciało do usunięcia:", ciala.length - 2, ciala);

                                            try
                                            {
                                                System.out.println("Czy na pewno chcesz usunąć ciało niebieskie '" + temp.cialaNiebieskie.get(u).nazwa + "'?");
                                                System.out.print("Aby potwierdzić wpisz nazwę ciała:\n\t-> ");


                                                if (temp.cialaNiebieskie.get(u).nazwa.equals(scr.nextLine()))
                                                {
                                                    temp.cialaNiebieskie.remove(temp.cialaNiebieskie.get(u));

                                                    try
                                                    {
                                                        xml.Zapisz(FILENAME, U);
                                                    } catch (FileNotFoundException e)
                                                    {
                                                        System.out.println("Coś poszło nie tak podczas zapisu pliku...");
                                                    }
                                                }

                                            }catch (IndexOutOfBoundsException e){

                                            }


                                        }else{
                                            System.out.println("Układ ten nie zawiera żadnych ciał niebieskich do usunięcia. Spróbuj najpierw dodać jakieś.");
                                        }

                                        break;
                                    case 4:     //zmień nazwę
                                        Przerwa();

                                        System.out.print("Zmień nazwę układu planetarnego '"+temp.nazwa+"'\n\nWprowadź nową nazwę\n\t-> ");
                                        temp.SetNazwa(scr.nextLine());

                                        try
                                        {
                                            xml.Zapisz(FILENAME,U);
                                        } catch (FileNotFoundException e)
                                        {
                                            System.out.println("Coś poszło nie tak podczas zapisu pliku...");
                                        }
                                        break;


                                    case 5:     //usuwanie całego układu
                                        Przerwa();

                                        System.out.println("USUWANIE UKŁADU\n");
                                        System.out.println("Czy na pewno chcesz usunąć układ '"+temp.nazwa+"'?");
                                        System.out.println("Spowoduje to usunięcie jego wszystkich danych: planet i gwiazd\n");
                                        System.out.print("Aby potwierdzić wpisz nazwę układu:\n\t-> ");

                                        String liniaDelete = scr.nextLine();


                                        if(temp.nazwa.equals(liniaDelete)){
                                            U.uklady.remove(temp);
                                            _E=false;
                                            try
                                            {
                                                xml.Zapisz(FILENAME,U);
                                            } catch (FileNotFoundException e)
                                            {
                                                System.out.println("Coś poszło nie tak podczas zapisu pliku...");
                                            }
                                        }

                                        break;


                                    case 6:     //wyjdź
                                        _E=false;
                                        break;
                                }
                            }
                        }
                    }



                    break;



                    //      /\ /\ /\ case 0

                case 1: // Filtrowanie ciał niebieskich we wszystkich układach
                    boolean _E = true;
                    while (_E) {
                        Przerwa();

                        int klucz = Lista.Pojedyncza("Wybierz co chcesz wyszukać:", 12, "Wszystkie układy zawierające planety", "Wszystkie układy zawierające gwiazdy",
                                "Wszystkie planety",  "Wszystkie gwiazdy", "Wszystkie planety ziemiopodobne", "Wszystkie planety z tlenem", "Wszystkie planety z wodą", "Wszystkie planety skaliste",
                                "Wszystkie planety gazowe", "Wszystkie planety karłowate", "Wszystkie gwiazdy danym typie widmowym", "Wyjdź");

                        if(klucz != 11)
                        {
                            Przerwa();

                            if(klucz == 0 || klucz == 1)
                            {
                                System.out.print(U.Wyszukaj(klucz).toString());
                            }
                            else
                            {
                                System.out.print(U.Wyszukaj(klucz).toStringShort());
                            }
                        }
                        else{
                            _E = false;
                        }

                    }
                    break;
            }
        }
    }
}
