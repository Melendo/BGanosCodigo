package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class MainView extends JFrame implements IGUI {

    // BOTONES
    JButton buttonEntrada;
    JButton buttonFabricante;
    JButton buttonInvernadero;
    JButton buttonFactura;
    JButton buttonPlanta;
    JButton buttonSistRiego;
    JLabel label;

    public MainView() {
        super("Animalia");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 1000;
        int alto = 650;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
        this.setVisible(true);
    }

    private void initGUI() {
        label = ComponentsBuilder.createLabel("Selecciona el modulo sobre el cual quieres trabajar", 50, 30, 900, 50, Color.BLACK);
        this.add(label);

        // ENTRADA
        buttonEntrada = ComponentsBuilder.createButton("Entrada", 100, 120, 185, 100);
        buttonEntrada.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.ENTRADA_VISTA, null));
            }
        });
        this.add(buttonEntrada);

        // FABRICANTE
        buttonFabricante = ComponentsBuilder.createButton("Fabricante", 407, 120, 185, 100);
        buttonFabricante.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.FABRICANTE_VISTA, null));
            }
        });
        this.add(buttonFabricante);

        // INVERNADERO
        buttonInvernadero = ComponentsBuilder.createButton("Invernadero", 715, 120, 185, 100);
        buttonInvernadero.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.INVERNADERO_VISTA, null));
            }
        });
        this.add(buttonInvernadero);

        // FACTURA
        buttonFactura = ComponentsBuilder.createButton("Factura", 100, 290, 185, 100);
        buttonFactura.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.FACTURA_VISTA, null));
            }
        });
        this.add(buttonFactura);

        // PLANTA
        buttonPlanta = ComponentsBuilder.createButton("Planta", 407, 290, 185, 100);
        buttonPlanta.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.PLANTA_VISTA, null));
            }
        });
        this.add(buttonPlanta);

        // SISTEMA DE RIEGO
        buttonSistRiego = ComponentsBuilder.createButton("Sistema de Riego", 715, 290, 185, 100);
        buttonSistRiego.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.SISTEMA_RIEGO_VISTA, null));
            }
        });
        this.add(buttonSistRiego);
    }

    public void actualizar(Context res) {
        // TODO Auto-generated method stub
    }
}
