package org.example;

import primos.PrimeCalculator;
import ui.Ventana;

public class Main {
public static void main(String[] args){
    Ventana v = new Ventana();
    v.mostrar();
    System.out.println(PrimeCalculator.inRange(0,0));
}
}
