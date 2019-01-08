/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.iterator;

/**
 *
 * @author kile
 */
public class ItemRepository implements Container {

    public String[] ulice = {"Ema", "Kile", "Buda"};

    @Override
    public Iterator getIterator() {

        return new UlicaIterator();

    }

    private class UlicaIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {

            if (index < ulice.length) {
                return true;
            }
            return false;

        }

        @Override
        public Object next() {

            if (index < ulice.length) {
                return true;
            }
            return false;

        }

    }

}
