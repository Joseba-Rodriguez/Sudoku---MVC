package sudoku.vista;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import sudoku.model.Casilla;
import sudoku.model.Tablero;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.DropMode;

public class Vista extends JFrame implements PropertyChangeListener
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField[][] casillas = new  JTextField[9][9];
	private JTextField textField;
	private JPanel panel_1;
	private JLabel lblCadidatos;
		private Controlador controlador = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
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
	public Vista() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(crearCasillas(), BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JButton btnAyuda_1 = new JButton("Ayuda");
		btnAyuda_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(btnAyuda_1);
		btnAyuda_1.setForeground(Color.DARK_GRAY);
		btnAyuda_1.setBackground(Color.WHITE);
		
		lblCadidatos = new JLabel("Cadidatos: ");
		panel_1.add(lblCadidatos);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		this.setVisible(true);
		
	}
	
	private JPanel crearCasillas() 
	{
		JPanel panel= new JPanel (new GridLayout(9,9));
		for(int i=0; i<casillas.length;i++) 
		{
            for(int j=0;j<casillas.length;j++) 
            {

                casillas [i][j]=new JTextField();
                casillas [i][j].setColumns(0);
                //casillas [i][j].setLocation(i, j);
                contentPane.add(casillas[i][j]);
                panel.add(casillas[i][j]);
                casillas[i][j].addActionListener(getControlador());


            }
        }
		return panel;
	}
	
	
	
	@Override
	public void propertyChange(PropertyChangeEvent pEvt) {}

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
			for (int i=0; i<casillas.length;i++) 
			{
				for(int j=0; j<casillas.length;j++) 
				{
					Tablero.getTablero().asignarValor(j, i, java.lang.Integer.parseInt(casillas[i][j].getText()));
				}
			}
		}
	} 
}
