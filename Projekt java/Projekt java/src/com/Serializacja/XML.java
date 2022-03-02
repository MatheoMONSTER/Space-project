package com.Serializacja;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class XML<T> implements Zapisywany<T>
{
    @Override
    public void Zapisz(String filename, T obj) throws FileNotFoundException
    {
        XMLEncoder xml = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
        xml.writeObject(obj);
        xml.close();
    }

    @Override
    public T Czytaj(String filename) throws FileNotFoundException
    {
        XMLDecoder xml = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
        T obj = (T)xml.readObject();

        return  obj;
    }
}
