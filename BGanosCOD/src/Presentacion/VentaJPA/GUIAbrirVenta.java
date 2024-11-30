package Presentacion.VentaJPA;

import javax.swing.JFrame;
import Presentacion.Controller.Command.Context;
import Presentacion.Controller.Command.CommandFactura.AbrirFacturaCommand;
import Presentacion.FactoriaVistas.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Negocio.Factura.TLineaFactura;
import Negocio.VentaJPA.TCarrito;
import Negocio.VentaJPA.TLineaVenta;
import Negocio.VentaJPA.TVenta;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.sql.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUIAbrirVenta extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JTextField textId;
	private JTextField textCantidad;
	private JTextField textPago;
	private JTextField textQuitar;
	private JTable tabla;
	private TCarrito tCarrito;
	String[] nombreColumnas = { "Id Producto", "Cantidad" };

	public GUIAbrirVenta() {
		super("Abrir Venta");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 700;
		int alto = 700;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}

	public void initGUI() {

		TVenta tVenta = new TVenta();
		tCarrito = new TCarrito(tVenta, new LinkedHashSet<>());
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		JPanel CabeceraID = new JPanel();
		CabeceraID.setBorder(new EmptyBorder(5, 5, 5, 5));
		CabeceraID.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(CabeceraID);
		JLabel CabeceraProd = ComponentsBuilder
				.createLabel("Introduzca el ID del producto que desee agregar a la venta ", 1, 10, 75, 20, Color.BLACK);
		CabeceraProd.setAlignmentX(CENTER_ALIGNMENT);
		CabeceraID.add(CabeceraProd);

		// id Prod
		JPanel panelID = new JPanel();
		panelID.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelID.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelID);

		panelID.add(new JLabel("Id del Producto: "));
		textId = new JTextField(20);
		panelID.add(textId);

		// cantidad Prod
		JPanel panelCantidad = new JPanel();
		panelCantidad.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelCantidad.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelCantidad);

		panelCantidad.add(new JLabel("Cantidad: "));
		textCantidad = new JTextField(20);
		panelCantidad.add(textCantidad);

		// aniadir boton
		JPanel panelAniadirButton = new JPanel();
		panelAniadirButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelAniadirButton.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelAniadirButton);

		JButton botonAnadirEntrada = new JButton("Añadir");
		botonAnadirEntrada.setBounds(75, 50, 100, 100);
		botonAnadirEntrada.addActionListener(a -> {
			try {

				if (textId.getText().isEmpty() || textCantidad.getText().isEmpty()) {
					JOptionPane.showMessageDialog(GUIAbrirVenta.this, "no se han rellenado todos los campos", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int idProd = Integer.parseInt(textId.getText());
					int cantidad = Integer.parseInt(textCantidad.getText());
					TLineaVenta lVenta = new TLineaVenta();
					lVenta.setIdPoducto(idProd);
					lVenta.setCantidad(cantidad);
					boolean modificacionLineaFactura = false;

					for (TLineaVenta linea : tCarrito.getLineaVenta()) {
						if (linea.getIdProducto() == idProd) {
							linea.setCantidad(linea.getCantidad() + cantidad);
							modificacionLineaFactura = true;
						}
					}
					if (!modificacionLineaFactura)
						tCarrito.getLineaVenta().add(lVenta);
					update();
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(GUIAbrirVenta.this, "Los datos no son correctos", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		});
		panelAniadirButton.add(botonAnadirEntrada);

		// forma de pago
		JPanel panelPago = new JPanel();
		panelPago.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPago.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelPago);

		panelPago.add(new JLabel("Forma de pago: "));
		textPago = new JTextField(20);
		panelPago.add(textPago);

		JLabel msgEliminar = ComponentsBuilder.createLabel(
				"Introduzca el ID del Producto que desea eliminar de la Venta ", 1, 150, 75, 20, Color.BLACK);
		msgEliminar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgEliminar);

		// id quitar
		JPanel panelQuitar = new JPanel();
		panelQuitar.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelQuitar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelQuitar);

		panelQuitar.add(new JLabel("Id Entrada: "));
		textQuitar = new JTextField(20);
		panelQuitar.add(textQuitar);

		// quitar cantidad
		JPanel panelCantidadOut = new JPanel();
		panelCantidadOut.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelCantidadOut.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelCantidadOut);

		panelCantidadOut.add(new JLabel("Cantidad a Quitar:"));
		JTextField cantidadOuttxt = new JTextField(20);
		panelCantidadOut.add(cantidadOuttxt);

		// quitar boton
		JPanel panelQuitarButton = new JPanel();
		panelQuitarButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelQuitarButton.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelQuitarButton);

		JButton botonQuitarEntrada = new JButton("Quitar");
		botonQuitarEntrada.setBounds(75, 50, 100, 100);
		botonQuitarEntrada.addActionListener(a -> {
			try {
				if (textId.getText().isEmpty() || cantidadOuttxt.getText().isEmpty()) {
					ApplicationController.getInstance().manageRequest(new Context(Evento.ABRIR_VENTA_KO, -1));
				} else {
					int idProd = Integer.parseInt(textQuitar.getText());
					int cantidad = Integer.parseInt(cantidadOuttxt.getText());
					boolean correct = true;
					boolean encontrado = false;
					Set <TLineaVenta> lVentas = tCarrito.getLineaVenta();
					for (TLineaVenta lVenta : lVentas) {
						if (lVenta.getIdProducto() == idProd) {
							encontrado = true;
							if (cantidad > lVenta.getCantidad())
								correct = false;
							else {
								int cantidadTotal = lVenta.getCantidad() - cantidad;
								if (cantidadTotal == 0)
									lVentas.remove(lVenta);
								else
									lVenta.setCantidad(cantidadTotal);
							}
						}

					}
					if(!correct)
						JOptionPane.showMessageDialog(this, "No hay entradas suficientes para quitar", "Error",
								JOptionPane.ERROR_MESSAGE);
					if (!encontrado)
						ApplicationController.getInstance().manageRequest(new Context(Evento.CERRAR_VENTA_KO, -1));
					else {
						tCarrito.setLineaVenta(lVentas);
						update();
					}

				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(GUIAbrirVenta.this, "Los datos no son correctos", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		panelQuitarButton.add(botonQuitarEntrada);

		tabla = ComponentsBuilder.createTable(0, nombreColumnas.length, nombreColumnas, null);
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(750, 250));
		add(scroll);

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VENTA_VISTA, null));

			}
		});
		panelBotones.add(botonCancelar);

		JButton botonCerrar = new JButton("Cerrar Venta");
		botonCerrar.setBounds(75, 50, 100, 100);
		botonCerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tVenta.setFormaDePago(textPago.getText());
				tCarrito.setVenta(tVenta);;
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CERRAR_VENTA, tCarrito));
			}
		});
		panelBotones.add(botonCerrar);
		this.setVisible(true);
		this.setResizable(true);
	}

	@Override
	public void actualizar(Context context) {
		int resultado = (int) context.getDatos();
		if (context.getEvento() == Evento.ABRIR_VENTA_OK) {
			JOptionPane.showMessageDialog(this, "Venta cerrada con id " + resultado +" cerrada correctamente", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (context.getEvento() == Evento.ABRIR_VENTA_KO) {
			switch (resultado) {
			case -3:
				JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos requeridos.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			case -2:
				JOptionPane.showMessageDialog(this, "Error: Ya existe una venta con el mismo ID.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			default:
				JOptionPane.showMessageDialog(this, "Error desconocido al modificar la venta.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
	}

	private void update() {
		String[][] datos = new String[tCarrito.getLineaVenta().size()][nombreColumnas.length];
		int i = 0;
		for (TLineaVenta v : tCarrito.getLineaVenta()) {
			datos[i][0] = v.getIdProducto().toString();
			datos[i][1] = v.getCantidad().toString();
			i++;
		}
		tabla.setModel(new DefaultTableModel(datos, nombreColumnas));
	}
}
