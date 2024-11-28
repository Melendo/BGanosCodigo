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
	private JTextField _formaPago;
	private JTextField _fecha;

	public GUIAbrirVenta() {
		super("Abrir Venta");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 700;
		int alto = 500;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}

	public void initGUI() {
		
		TVenta tVenta = new TVenta();
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
		textId = new JTextField(20);
		panelCantidad.add(textId);

		JPanel panelAniadirButton = new JPanel();
		panelAniadirButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelAniadirButton.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelAniadirButton);

		JButton botonAnadirEntrada = new JButton("Añadir");
		botonAnadirEntrada.setBounds(75, 50, 100, 100);
//		botonAnadirEntrada.addActionListener(a -> {
//			try {
//
//				if (textId.getText().isEmpty() || textCantidad.getText().isEmpty()) {
//					JOptionPane.showMessageDialog(GUIAbrirVenta.this, "no se han rellenado todos los campos", "Error",
//							JOptionPane.ERROR_MESSAGE);
//				} else {
//					int idProd = Integer.parseInt(textId.getText());
//					int cantidad = Integer.parseInt(textCantidad.getText());
//					TLineaVenta lVenta = new TLineaVenta();
//					lVenta.setIdPoducto(idProd);
//					lVenta.setCantidad(cantidad);
//					boolean modificacionLineaFactura = false;
//
//					for (TLineaFactura linea : lVenta) {
//						if (linea.getidEntrada() == idEntrada) {
//							linea.setCantidad(linea.getCantidad() + cantidad);
//							modificacionLineaFactura = true;
//						}
//					}
//					if (!modificacionLineaFactura)
//						lineaFacturas.add(lineaFactura);
//					setVisible(false);
//					ApplicationController.getInstance()
//							.manageRequest(new Context(Evento.ANIADIR_PRODUCTO, new TCarrito(tVenta, lineaVentas)));
//				}
//
//			} catch (Exception ex) {
//				JOptionPane.showMessageDialog(GUICerrarFactura.this, "Los datos no son correctos", "Error",
//						JOptionPane.ERROR_MESSAGE);
//			}
//
//		});
		panelAniadirButton.add(botonAnadirEntrada);

		JLabel msg = ComponentsBuilder.createLabel("Introduzca el ID de la entrada que desea eliminar de la factura ",
				1, 150, 80, 20, Color.BLACK);
		msg.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msg);

		JPanel panelQuitar = new JPanel();
		panelQuitar.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelQuitar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelQuitar);

		JLabel idEntradaOut = new JLabel("Id Entrada");
		idEntradaOut.setPreferredSize(new Dimension(100, 30));
		panelQuitar.add(idEntradaOut);

		JTextField idOut = new JTextField();
		idOut.setPreferredSize(new Dimension(250, 30));
		idOut.setEditable(true);
		panelQuitar.add(idOut);

		JPanel panelCantidadOut = new JPanel();
		panelCantidadOut.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelCantidadOut.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelCantidadOut);

		JLabel cantidadOut = new JLabel("Cantidad a Quitar:");
		cantidadOut.setPreferredSize(new Dimension(120, 60));
		panelCantidadOut.add(cantidadOut);

		JTextField cantidadOuttxt = new JTextField();
		cantidadOuttxt.setPreferredSize(new Dimension(250, 30));
		cantidadOuttxt.setEditable(true);
		panelCantidadOut.add(cantidadOuttxt);

		JPanel panelQuitarButton = new JPanel();
		panelQuitarButton.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelQuitarButton.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelQuitarButton);

		JButton botonQuitarEntrada = new JButton("Quitar");
		botonQuitarEntrada.setBounds(75, 50, 100, 100);
//		botonQuitarEntrada.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					if(idOut.getText().isEmpty() || cantidadOuttxt.getText().isEmpty()){
//		    			ApplicationController.getInstance().manageRequest(new Context (Evento.CERRAR_FACTURA_KO, -1));
//					}
//					else{
//						int idEntrada = Integer.parseInt(idOut.getText());
//						int cantidad = Integer.parseInt(cantidadOuttxt.getText());
//						boolean correct = true;
//						boolean encontrado = false;
//	                    Set<TLineaFactura> lineaFacturas = tCarrito.getLineasFactura();
//	                    for(TLineaFactura linea: lineaFacturas){
//	                    	if(linea.getidEntrada() == idEntrada){
//	                    		encontrado = true;
//								if(cantidad > linea.getCantidad())
//					        		correct = false; 
//								else{
//									int cantidadTotal = linea.getCantidad() - cantidad;
//									if(cantidadTotal == 0)
//										lineaFacturas.remove(linea);
//									else
//										linea.setCantidad(cantidadTotal);
//								}
//							}
//								
//	                    }
//	                    if(!encontrado)
//	                    {
//			    			ApplicationController.getInstance().manageRequest(new Context (Evento.CERRAR_FACTURA_KO, -1));
//	                    }
//	                    else if(!correct){
//			    			ApplicationController.getInstance().manageRequest(new Context (Evento.CERRAR_FACTURA_KO, -3));
//	                    }
//	                    else{
//	                    	GUICerrarFactura.this.setVisible(false);
//	                        tCarrito.setLineaFactura(lineaFacturas);
//	                        ApplicationController.getInstance().manageRequest(new Context(Evento.CERRAR_FACTURA_VISTA, tCarrito));
//	                    }
//
//					}
//
//				} catch (Exception ex) {
//					JOptionPane.showMessageDialog(GUICerrarFactura.this, "Los datos no son correctos", "Error", JOptionPane.ERROR_MESSAGE);
//				}
//
//			}
//		});

		panelQuitarButton.add(botonQuitarEntrada);

//		Set<TLineaFactura> LineasFactura = tCarrito.getLineasFactura();
//		String[] nombreColumnas = { "Id Entrada", "Cantidad" };
//		List<String[]> datosColumnas = new ArrayList<String[]>();

//		for (TLineaFactura t : LineasFactura) {
//			String[] datos = new String[2];
//			datos[0] = t.getidEntrada().toString();
//			datos[1] = t.getCantidad().toString();
//			datosColumnas.add(datos);
//		}
//		
//		JTable tabla = ComponentsBuilder.createTable(LineasFactura.size(), 2, nombreColumnas, datosColumnas.toArray(new String[][] {}));
//		JScrollPane scroll = new JScrollPane(tabla);
//		scroll.setBounds(50, 115, 900, 288);
//		this.add(scroll);

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.FACTURA_VISTA, null));

			}
		});
		panelBotones.add(botonCancelar);

//		JButton botonCerrar = new JButton("Cerrar Factura");
//		botonCerrar.setBounds(75, 50, 100, 100);
//		botonCerrar.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				tCarrito.setFactura(factura);
//	            ApplicationController.getInstance().manageRequest(new Context(Evento.CERRAR_FACTURA, tCarrito));
//			}
//		});
//		panelBotones.add(botonCerrar);
		this.setVisible(true);
		this.setResizable(true);
	}

	@Override
	public void actualizar(Context context) {
		int resultado = (int) context.getDatos();
		if (context.getEvento() == Evento.ABRIR_VENTA_OK) {
			JOptionPane.showMessageDialog(this, "Venta dada de alta correctamente con id: " + resultado, "Éxito",
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
}
