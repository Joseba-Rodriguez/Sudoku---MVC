package sudoku.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sudoku.model.Tablero;
import sudoku.model.Usuario;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JScrollBar;

public class Puntuaciones extends JFrame implements PropertyChangeListener {

	private JPanel contentPane;
	private JPanel panel;
	private JButton usuario;
	private JButton puntuacion;
	private JPanel posiciones;
	private JTextField[][] texto;
	private JButton btnNewButton;
	private Controlador controlador = null;
	private JScrollBar scrollBar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Puntuaciones frame = new Puntuaciones();
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
	public Puntuaciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getScrollBar(), BorderLayout.EAST);
		
		//JScrollPane sp= new JScrollPane()
		
		
		this.setVisible(true);
		Tablero.getTablero().addObserver(this);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			panel.add(getButton_1());
			panel.add(getBtnNewButton());
			panel.add(getButton_2());
		}
		return panel;
	}
	private JButton getButton_1() {
		if (usuario == null) {
			usuario = new JButton("Usuario");
			usuario.addActionListener(getControlador());

			usuario.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return usuario;
	}
	private JButton getButton_2() {
		if (puntuacion == null) {
			puntuacion = new JButton("Puntuaci\u00F3n");
			puntuacion.addActionListener(getControlador());
			puntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return puntuacion;
	}
	
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Dificultad");
			btnNewButton.addActionListener(getControlador());
		}
		return btnNewButton;
	}
	
	private JPanel getPosiciones(int i) {
		if (posiciones == null) {
			posiciones = new JPanel(new GridLayout(i, 3));
			texto = new  JTextField[i][3];
			
			for(int k=0; k<i;k++) 
			{
	            for(int j=0;j<3;j++) 
	            {
	            	texto [k][j]=new JTextField();
	            	texto [k][j].setHorizontalAlignment(JTextField.CENTER);
	            	texto [k][j].setFont(new Font("Tahoma", Font.PLAIN, 20));
	                contentPane.add(texto [k][j]);
	                posiciones.add(texto [k][j]);      
	            }
	        }
		}
		return posiciones;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt!=null) 
		{
			if (evt.getPropertyName().equals("puntuacion")) 
			{
				@SuppressWarnings("unchecked")
				ArrayList<Usuario> listaPuntuaciones= (ArrayList<Usuario>) evt.getNewValue();
				int i= listaPuntuaciones.size();
				contentPane.add(getPosiciones(i), BorderLayout.CENTER);
				ArrayList<String> usuario= new ArrayList<String>();
				ArrayList<Integer> dif= new ArrayList<Integer>();
				ArrayList<Integer> puntu= new ArrayList<Integer>();
				
				for (Usuario us:listaPuntuaciones) 
				{
					usuario.add(us.getNombre());
					dif.add(us.getDificultad());
					puntu.add(us.getPuntos());
				}
				
				for (int h=0; h<usuario.size(); h++) 
				{
					texto[h][0].setText(usuario.get(h));
					texto[h][1].setText(Integer.toString(dif.get(h)));
					texto[h][2].setText(Integer.toString(puntu.get(h)));
				}
			}
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
			if (usuario.equals(e.getSource())) 
			{
				Tablero.getTablero().ordenarPuntuaciones(0);
			}
			if (btnNewButton.equals(e.getSource())) 
			{
				Tablero.getTablero().ordenarPuntuaciones(1);
			}
			if (puntuacion.equals(e.getSource())) 
			{
				Tablero.getTablero().ordenarPuntuaciones(2);
			}
		}
	}
	
	private JScrollBar getScrollBar() {
		if (scrollBar == null) {
			scrollBar = new JScrollBar();
		}
		return scrollBar;
	}
}