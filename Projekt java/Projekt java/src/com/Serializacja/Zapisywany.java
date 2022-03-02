package com.Serializacja;

import java.io.FileNotFoundException;

public interface Zapisywany<T>
{
    void  Zapisz(String filename,T obj) throws FileNotFoundException;

    T Czytaj(String filename) throws FileNotFoundException;
}
