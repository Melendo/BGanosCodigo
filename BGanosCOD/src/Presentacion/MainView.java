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

    // Definici�n de los botones
    private JButton buttonEntrada;
    private JButton buttonFabricante;
    private JButton buttonInvernadero;
    private JButton buttonFactura;
    private JButton buttonPlanta;
    private JButton buttonSistRiego;
    private JLabel label;

    public MainView() {
        super("BGanos - Gestión de Módulos");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = 1000;
        int frameHeight = 650;
        int posX = (screenSize.width - frameWidth) / 2;
        int posY = (screenSize.height - frameHeight) / 2;
        this.setBounds(posX, posY, frameWidth, frameHeight);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        this.getContentPane().setBackground(new Color(250, 250, 250)); // Fondo m�s claro
        this.setVisible(true);
    }

    private void initializeComponents() {
        label = ComponentsBuilder.createLabel("Selecciona un módulo para gestionar", 50, 40, 900, 50, Color.DARK_GRAY);
        this.add(label);

        // Bot�n de Entrada
        buttonEntrada = ComponentsBuilder.createButton("Entrada", 100, 120, 185, 90);
        buttonEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.ENTRADA_VISTA, null));
            }
        });
        this.add(buttonEntrada);

        // Bot�n de Fabricante
        buttonFabricante = ComponentsBuilder.createButton("Fabricante", 407, 120, 185, 90);
        buttonFabricante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.FABRICANTE_VISTA, null));
            }
        });
        this.add(buttonFabricante);

        // Bot�n de Invernadero
        buttonInvernadero = ComponentsBuilder.createButton("Invernadero", 715, 120, 185, 90);
        buttonInvernadero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.INVERNADERO_VISTA, null));
            }
        });
        this.add(buttonInvernadero);

        // Bot�n de Factura
        buttonFactura = ComponentsBuilder.createButton("Factura", 100, 290, 185, 90);
        buttonFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.FACTURA_VISTA, null));
            }
        });
        this.add(buttonFactura);

        // Bot�n de Planta
        buttonPlanta = ComponentsBuilder.createButton("Planta", 407, 290, 185, 90);
        buttonPlanta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.PLANTA_VISTA, null));
            }
        });
        this.add(buttonPlanta);

        // Bot�n de Sistema de Riego
        buttonSistRiego = ComponentsBuilder.createButton("Sistema de Riego", 715, 290, 185, 90);
        buttonSistRiego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.SISTEMA_RIEGO_VISTA, null));
            }
        });
        this.add(buttonSistRiego);
    }

    @Override
    public void actualizar(Context res) {
        // Implementar actualizaci�n si es necesario
    }
}
