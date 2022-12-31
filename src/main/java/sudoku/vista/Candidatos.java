package sudoku.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sudoku.model.CasillaNoInicial;
import sudoku.model.Tablero;


import javax.swing.JButton;
import javax.swing.JList;

public class Candidatos extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton uno;
	private JButton dos;
	private JButton tres;
	private JButton cuatro;
	private JButton cinco;
	private JButton seis;
	private JButton siete;
	private JButton ocho;
	private JButton nueve;
	private Controlador controlador = null;
	private int fila;
	private int columna;
	private boolean tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Candidatos frame = new Candidatos();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Candidatos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 231, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getUno());
			panel.add(getDos());
			panel.add(getTres());
			panel.add(getCuatro());
			panel.add(getCinco());
			panel.add(getSeis());
			panel.add(getSiete());
			panel.add(getOcho());
			panel.add(getNueve());
			this.setVisible(true);
		}
		return panel;
	}
	private JButton getUno() {
		if (uno == null) {
			uno = new JButton("1");
			uno.setBounds(10, 0, 89, 23);
			uno.addActionListener(getControlador());
		}
		return uno;
	}
	private JButton getDos() {
		if (dos == null) {
			dos = new JButton("2");
			dos.setBounds(109, 0, 89, 23);
			dos.addActionListener(getControlador());
		}
		return dos;
	}
	private JButton getTres() {
		if (tres == null) {
			tres = new JButton("3");
			tres.setBounds(10, 34, 89, 23);
			tres.addActionListener(getControlador());
		}
		return tres;
	}
	private JButton getCuatro() {
		if (cuatro == null) {
			cuatro = new JButton("4");
			cuatro.setBounds(109, 34, 89, 23);
			cuatro.addActionListener(getControlador());
		}
		return cuatro;
	}
	private JButton getCinco() {
		if (cinco == null) {
			cinco = new JButton("5");
			cinco.setBounds(10, 67, 89, 23);
			cinco.addActionListener(getControlador());
		}
		return cinco;
	}
	private JButton getSeis() {
		if (seis == null) {
			seis = new JButton("6");
			seis.setBounds(109, 68, 89, 23);
			seis.addActionListener(getControlador());
		}
		return seis;
	}
	private JButton getSiete() {
		if (siete == null) {
			siete = new JButton("7");
			siete.setBounds(10, 97, 89, 23);
			siete.addActionListener(getControlador());
		}
		return siete;
	}
	private JButton getOcho() {
		if (ocho == null) {
			ocho = new JButton("8");
			ocho.setBounds(109, 97, 89, 23);
			ocho.addActionListener(getControlador());
		}
		return ocho;
	}
	private JButton getNueve() {
		if (nueve == null) {
			nueve = new JButton("9");
			nueve.setBounds(66, 131, 89, 23);
			nueve.addActionListener(getControlador());
		}
		return nueve;
	}
	protected void asignarContexto(boolean pTipo, int f, int c) 
	{
		tipo=pTipo;
		fila=f;
		columna=c;
	}
	
	private Controlador getControlador() 
	{
		if (controlador==null) 
		{
			controlador= new Controlador();
		}
		return controlador;
	}
	private void cerrar() 
	{
		this.setVisible(false);
	}

	private class Controlador extends WindowAdapter implements ActionListener 
	{
		@Override
		public void windowClosing(WindowEvent e) 
		{
			System.exit(0);
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{	
			if (tipo==true) 
			{
				if (uno.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).agregarCandidato(1);
				}
				else if (dos.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).agregarCandidato(2);
				}
				else if (tres.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).agregarCandidato(3);
				}
				else if (cuatro.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).agregarCandidato(4);
				}
				else if (cinco.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).agregarCandidato(5);
				}
				else if (seis.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).agregarCandidato(6);
				}
				else if (siete.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).agregarCandidato(7);
				}
				else if (ocho.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).agregarCandidato(8);
				}
				else if (nueve.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).agregarCandidato(9);
				}
			}
			
			else 
			{
				if (uno.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).eliminarCandidato(1);
				}
				else if (dos.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).eliminarCandidato(2);
				}
				else if (tres.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).eliminarCandidato(3);
				}
				else if (cuatro.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).eliminarCandidato(4);
				}
				else if (cinco.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).eliminarCandidato(5);
				}
				else if (seis.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).eliminarCandidato(6);
				}
				else if (siete.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).eliminarCandidato(7);
				}
				else if (ocho.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).eliminarCandidato(8);
				}
				else if (nueve.equals(e.getSource())) 
				{	
					((CasillaNoInicial) Tablero.getTablero().getCasilla(fila, columna)).eliminarCandidato(9);
				}
			}
			cerrar ();
					
		}
	}
}