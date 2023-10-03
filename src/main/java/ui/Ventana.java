package ui;

import primos.PrimeCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Ventana extends JFrame {
    private JPanel VentanaMain;
    private JTextField txtini;
    private JTextField txtfin;
    private JButton calcularButton;
    private JList lista;
    private JSpinner spinnerini;
    private JSpinner spinnerfin;

    private DefaultListModel<String> datos;
    public Ventana(){
        this.setContentPane(VentanaMain);
        this.pack();
        this.setTitle("Calculadora de primos");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setIconImage();
        datos = new DefaultListModel<>();
        lista.setModel(datos);
        calcularButton.addActionListener( (ActionEvent e) -> rellenarPrimos2());

        spinnerini.setModel(new SpinnerNumberModel(1,1,1000,1));
        spinnerfin.setModel(new SpinnerNumberModel(3,3,1000,1));
    }

    private void rellenarPrimos2(){
        Integer numero1 = (Integer) spinnerini.getValue();
        Integer numero2 = (Integer) spinnerfin.getValue();
        if (numero1 >= numero2) {
            JOptionPane.showMessageDialog(null, "El primer numero no puede ser mayor o igual al segundo");
        } else {
            ArrayList<Integer> numeros = PrimeCalculator.inRange(numero1, numero2);
            if(numeros.isEmpty()) datos.addElement("No hay primos");;
            datos.clear();
            for (Integer n:numeros){
                datos.addElement(n.toString());
            };
        }
    }
    private void rellenarPrimos(){

        Integer numero1 = null;
        Integer numero2 = null;
        try{
            numero1 = Integer.valueOf(txtini.getText());
            numero2 = Integer.valueOf(txtfin.getText());
        }
            catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "No se permite texto");
                }

        if (numero1 != null && numero2 != null && numero1>=0 && numero2>=0 ) {


            if (numero1 >= numero2) {
                JOptionPane.showMessageDialog(null, "El primer numero no puede ser mayor o igual al segundo");
            } else {
                ArrayList<Integer> numeros = PrimeCalculator.inRange(numero1, numero2);
                if(numeros.isEmpty()) datos.addElement("No hay primos");;
                datos.clear();
                for (Integer n:numeros){
                    datos.addElement(n.toString());
                };
            }
        }
    }
    public void mostrar(){
        this.setVisible(true);
    }
}
