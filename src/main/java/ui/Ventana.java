package ui;

import org.example.Main;
import primos.PrimeCalculator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ventana extends JFrame {
    private JPanel VentanaMain;
    private JTextField txtini;
    private JTextField txtfin;
    private JButton calcularButton;
    private  JList lista;
    private JSpinner spinnerini;
    private JSpinner spinnerfin;
    private JLabel info;
    private JButton guardar;

    private DefaultListModel<String> datos;
    private static ArrayList<Integer> primos = new ArrayList<>();
    public Ventana(){
        this.setContentPane(VentanaMain);
        this.pack();
        this.setTitle("Calculadora de primos");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(Main.class.getClassLoader().getResource("SoyProgramador.png")).getImage());
        //setIconImage();
        datos = new DefaultListModel<>();
        lista.setModel(datos);
        calcularButton.addActionListener( (ActionEvent e) -> rellenarPrimos2());

        spinnerini.setModel(new SpinnerNumberModel(1,1,1000,1));
        spinnerfin.setModel(new SpinnerNumberModel(3,3,1000,1));
        lista.addListSelectionListener((ListSelectionEvent e) -> mostrarPrimo(e));
        guardar.addActionListener(e -> guardarPrimos());
    }

    private static void guardarPrimos() {
        System.out.println("Boton guardar");
        var dialogoGuardar = new JFileChooser();
        if (dialogoGuardar.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File f = dialogoGuardar.getSelectedFile();

            try(
                var bw = new BufferedWriter(new FileWriter(f))){
                    for(Integer primo:primos){
                        bw.write(primo+",");
                    }
                }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Se ha guardado el archivo");
        };

    }

    private  void mostrarPrimo(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()){

            System.out.println("Evento de lista");
            String primo = (String) lista.getSelectedValue();
            info.setText("Numero primo: "+primo);
        }
    }


    private void rellenarPrimos2(){
        Integer numero1 = (Integer) spinnerini.getValue();
        Integer numero2 = (Integer) spinnerfin.getValue();
        if (numero1 >= numero2) {
            JOptionPane.showMessageDialog(null, "El primer numero no puede ser mayor o igual al segundo");
        } else {
            primos = PrimeCalculator.inRange(numero1, numero2);
            if(primos.isEmpty()) datos.addElement("No hay primos");;
            datos.clear();
            for (Integer n:primos){
                datos.addElement(n.toString());
            };
            info.setText("Total: "+ datos.size());

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
                primos = PrimeCalculator.inRange(numero1, numero2);
                if(primos.isEmpty()) datos.addElement("No hay primos");;
                datos.clear();

                for (Integer n:primos){
                    datos.addElement(n.toString());
                }
            }
        }
    }
    public void mostrar(){
        this.setVisible(true);
    }
}
