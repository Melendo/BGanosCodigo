package Presentacion.VentaJPA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.VentaJPA.TVenta;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class GUIVentaJPA extends JFrame implements IGUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton buttonAltaVenta;
    private JButton buttonBajaVenta;
    private JButton buttonModificarVenta;
    private JButton buttonMostrarVenta;
    private JButton buttonListarVentas;
    private JButton buttonAnyadirProducto;
    private JButton buttonDevolverVenta;
    private JButton buttonProcesarVenta;
    private JButton buttonQuitarProducto;
    private JButton buttonVentasPorEmpleadoDeCaja;
    private JButton backButton;
    private JPanel panel;
    private TVenta tVenta;
    
    public GUIVentaJPA(){
    	super("Ventas");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 1000;
        int alto = 525;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(null);
        panel = new JPanel();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
        this.setVisible(true);
    }
    
    public void initGUI() {
    	tVenta = new TVenta();
        JLabel label = ComponentsBuilder.createLabel("Venta", 250, 30, 500, 50, Color.BLACK);
        this.add(label);

        // ALTA VENTA
        buttonAltaVenta = ComponentsBuilder.createButton("Alta", 100, 100, 185, 100);
        buttonAltaVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIVentaJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_VENTA_VISTA, tVenta));
            }
        });
        buttonAltaVenta.setVisible(true);
        this.add(buttonAltaVenta);

        // BAJA VENTA
        buttonBajaVenta = ComponentsBuilder.createButton("Baja", 300, 100, 185, 100);
        buttonBajaVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUIVentaJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.BAJA_VENTA_VISTA, tVenta));
            }
        });
        buttonBajaVenta.setVisible(true);
        this.add(buttonBajaVenta);

        // MODIFICAR VENTA
        buttonModificarVenta = ComponentsBuilder.createButton("Modificar", 500, 100, 185, 100);
        buttonModificarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUIVentaJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_VENTAS_VISTA, tVenta));
            }
        });
        buttonModificarVenta.setVisible(true);
        this.add(buttonModificarVenta);

        // MOSTRAR VENTA
        buttonMostrarVenta = ComponentsBuilder.createButton("Mostrar por ID", 700, 100, 185, 100);
        buttonMostrarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUIVentaJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.MOSTRAR_VENTA_POR_ID_VISTA, tVenta));
            }
        });
        buttonMostrarVenta.setVisible(true);
        this.add(buttonMostrarVenta);

        // LISTAR VENTAS
        buttonListarVentas = ComponentsBuilder.createButton("Listar", 100, 250, 185, 100);
        buttonListarVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUIVentaJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_VENTAS, tVenta));
            }
        });
        buttonListarVentas.setVisible(true);
        this.add(buttonListarVentas);

       

        // LISTAR VENTAS POR EMPLEADO DE CAJA
        buttonVentasPorEmpleadoDeCaja = ComponentsBuilder.createButton("Ventas por Empleado", 700, 250, 185, 100);
        buttonVentasPorEmpleadoDeCaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUIVentaJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VENTAS_POR_EMPLEADO_DE_CAJA_VISTA, tVenta));
            }
        });
        buttonVentasPorEmpleadoDeCaja.setVisible(true);
        this.add(buttonVentasPorEmpleadoDeCaja);

        // AÑADIR PRODUCTO A VENTA
        buttonAnyadirProducto = ComponentsBuilder.createButton("Añadir Producto", 100, 375, 185, 100);
        buttonAnyadirProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUIVentaJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.ANIADIR_PRODUCTO_VISTA, tVenta));
            }
        });
        buttonAnyadirProducto.setVisible(true);
        this.add(buttonAnyadirProducto);

        // QUITAR PRODUCTO DE VENTA
        buttonQuitarProducto = ComponentsBuilder.createButton("Quitar Producto", 300, 375, 185, 100);
        buttonQuitarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUIVentaJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.QUITAR_PRODUCTO_VISTA, tVenta));
            }
        });
        buttonQuitarProducto.setVisible(true);
        this.add(buttonQuitarProducto);

        // DEVOLVER VENTA
        buttonDevolverVenta = ComponentsBuilder.createButton("Devolver Venta", 500, 375, 185, 100);
        buttonDevolverVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUIVentaJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.DEVOLVER_VENTA, tVenta));
            }
        });
        buttonDevolverVenta.setVisible(true);
        this.add(buttonDevolverVenta);

        // PROCESAR VENTA
        buttonProcesarVenta = ComponentsBuilder.createButton("Procesar Venta", 700, 375, 185, 100);
        buttonProcesarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUIVentaJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.PROCESAR_VENTA, tVenta));
            }
        });
        buttonProcesarVenta.setVisible(true);
        this.add(buttonProcesarVenta);

        // BOTON DE VOLVER
        backButton = ComponentsBuilder.createBackButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUIVentaJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VISTA_PRINCIPAL, null));
                dispose();
            }
        });
        backButton.setVisible(true);
        this.add(backButton);

        getContentPane().add(panel);
    }

	@Override
	public void actualizar(Context context) {
		// TODO Auto-generated method stub
		
	}

}
