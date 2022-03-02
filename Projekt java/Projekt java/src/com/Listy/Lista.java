package com.Listy;

import java.util.Scanner;

/**
 * Zawiera metody ukazujące tabele stringów jako numerowane tablice
 */
public abstract class Lista {
    /**
     * @param tytul tytul listy pokazywany przed tablicą
     * @param opcje opcje listy, które ukażą się jako numerowana lista od 1 do ilości elementów
     * @return index wybranego elementu
     */
    public static int Pojedyncza(String tytul, String... opcje) {
        return Pojedyncza(tytul,-1,opcje);
    }

    /**
     * @param tytul tytul listy pokazywany przed tablicą
     * @param indexPrzerwy po którym wierszu robić przerwę
     * @param opcje opcje listy, które ukażą się jako numerowana lista od 1 do ilości elementów
     * @return index wybranego elementu
     */
    public static int Pojedyncza(String tytul,int indexPrzerwy, String... opcje) {
        System.out.println(tytul + "\n");
        for (int i = 0; i < opcje.length; i++) {
            System.out.println((i + 1) + ".\t" + opcje[i]);
            if(i==indexPrzerwy)
                System.out.println("---------------");
        }
        Scanner scr = new Scanner(System.in);
        boolean petla = true;
        while(petla) {
            System.out.print("\n\t-> ");
            String linia = scr.nextLine();
            try {
                int wybor = Integer.parseInt(linia) -1;

                if(wybor>=0 && wybor< opcje.length)
                {
                    petla = false;
                    return wybor;
                }
                else
                {
                    System.out.println("\n\tBŁĄD (podana liczba wykracza poza zakres opcji!)");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n\tBŁĄD (należy wpisać liczbę!)");
            }
        }
        return -1;
    }
}

