package sudoku.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sudoku.model.Tablero;


import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PrimeraVista extends JFrame implements PropertyChangeListener{

	private JPanel contentPane;
	private Controlador controlador = null;
	JTextField textPane = new JTextField();
	JButton btnFcil = new JButton("F\u00E1cil");
	JButton btnNormal = new JButton("Normal");
	JButton btnDifcil = new JButton("Dif\u00EDcil");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimeraVista frame = new PrimeraVista();
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
	public PrimeraVista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);
		
		Label label = new Label("Usuario :");
		label.setBounds(160, 20, 57, 22);
		panel_3.add(label);
		
		
		textPane.setBounds(223, 22, 66, 20);
		panel_3.add(textPane);
		
		
		Label label_1 = new Label("Nivel :");
		label_1.setBounds(196, 77, 34, 22);
		panel_3.add(label_1);
		
		btnFcil.setBounds(58, 126, 89, 23);
		panel_3.add(btnFcil);
		btnFcil.addActionListener(getControlador());
		
		
		btnNormal.setBounds(171, 126, 89, 23);
		panel_3.add(btnNormal);
		btnNormal.addActionListener(getControlador());
		
		
		btnDifcil.setBounds(285, 126, 89, 23);
		panel_3.add(btnDifcil);
		btnDifcil.addActionListener(getControlador());
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		this.setVisible(true);
		
			}
	@Override
	public void propertyChange(PropertyChangeEvent pEvt) 
	{
		if(Tablero.getTablero().getfinVista1()==true) 
		{
			this.setVisible(false);
			Vista vs= new Vista();
		}
	}
	
	private void cerrar () 
	{
		if(Tablero.getTablero().getfinVista1()==true) 
		{
			this.setVisible(false);
			Vista vs= new Vista();
			Tablero.getTablero().iniciarTablero();
		}
	}

	private Controlador getControlador() 
	{
		if (controlador==null) 
		{
			controlador= new Controlador();
		}
		return controlador;
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
			//if (e.getSource().equals(textPane)) 
			//{
				Tablero.getTablero().asignarUsuario(textPane.getText());
			//}
			if (e.getSource().equals(btnFcil)) 
			{
				Tablero.getTablero().asignarDificultad(1);
			}
			if (e.getSource().equals(btnNormal)) 
			{
				Tablero.getTablero().asignarDificultad(2);
			}
			if (e.getSource().equals(btnDifcil)) 
			{
				Tablero.getTablero().asignarDificultad(3);
			}
			cerrar ();
		}
	} 
}