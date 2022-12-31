package sudoku.vista;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import sudoku.model.Casilla;
import sudoku.model.CasillaInicial;
import sudoku.model.CasillaNoInicial;
import sudoku.model.Tablero;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JButton;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.Font;

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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

public class Vista extends JFrame implements PropertyChangeListener
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField[][] casillas = new  JTextField[9][9];
	private JPanel panel_1;
	private Controlador controlador = null;
	private PopClickListener popClick=null;
	private JButton juego;
	private JButton candidatos;
	private boolean escribir= true;
	private JPanel panel_2;
	private JButton comprobar;
	private JButton btnAyuda_1;
	private JMenuItem anadir;
    private JMenuItem quitar;
    private int fila;
    private int columna;
    private boolean ana=false;
    private boolean qui=false;
    private JButton finalizar;

	/**
	 * Launch the application.
	 * 
	 * 
				
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
	{//
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(crearCasillas(), BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		btnAyuda_1 = new JButton("Ayuda");
		btnAyuda_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(btnAyuda_1);
		btnAyuda_1.setForeground(Color.DARK_GRAY);
		btnAyuda_1.setBackground(Color.WHITE);
		btnAyuda_1.addActionListener(getControlador());
		
		candidatos = new JButton("Candidatos");
		panel_1.add(candidatos);
		candidatos.addActionListener(getControlador());
		
		juego = new JButton("Juego");
		panel_1.add(juego);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		comprobar = new JButton("Comprobar");
		comprobar.addActionListener(getControlador());
		panel_2.add(comprobar);
		
		finalizar = new JButton("Finalizar");
		panel_2.add(finalizar);
		juego.addActionListener(getControlador());
		finalizar.addActionListener(getControlador());
		
		
		this.setVisible(true);
		
		// Registrar la ventana como observador
		Tablero.getTablero().addObserver(this);	
		
		
	}
	
	private void ponerColor(int inFi,int finFi, int inCo, int finCo, int R, int G, int B) 
	{
		for (int i=inFi; i<finFi; i++) 
		{
			for (int j=inCo; j<finCo; j++) 
			{				
				casillas[i][j].setBackground(new java.awt.Color(R,G,B));					
			}
		}
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
                casillas [i][j].setDocument(new JTextFieldCharLimit(1));
                casillas [i][j].setHorizontalAlignment(JTextField.CENTER);
              //  casillas[i][j].setForeground(Color.BLUE);
                casillas[i][j].setFont(new Font("Tahoma", Font.PLAIN, 20));
                contentPane.add(casillas[i][j]);
                panel.add(casillas[i][j]);
                casillas[i][j].addActionListener(getControlador());
                final int pFila = i;
                final int pCol =j;
                if (Tablero.getTablero().getCasilla(i, j) instanceof CasillaNoInicial==true) {
                casillas[i][j].addFocusListener(new FocusListener() {
                	
					@Override
					public void focusGained(FocusEvent arg0) {
						
						fila=pFila;
						columna=pCol;
						System.out.println(fila);
						System.out.println(columna);
						
					}

					@Override
					public void focusLost(FocusEvent arg0) {
												
					}
                	
                });}
                
            }
        }
		
		ponerColor(0, 3, 0, 3, 214, 253, 253);
		ponerColor(6, 9, 0, 3, 214, 253, 253);
		ponerColor(0, 3, 6, 9, 214, 253, 253);
		ponerColor(6, 9, 6, 9, 214, 253, 253);
		ponerColor(3, 6, 3, 6, 214, 253, 253);
		
		ponerColor(0, 3, 3, 6, 255, 255, 255);
		ponerColor(3, 6, 0, 3, 255, 255, 255);
		ponerColor(3, 6, 6, 9, 255, 255, 255);
		ponerColor(6, 9, 3, 6, 255, 255, 255);
		return panel;
	}
	
	 
	@Override
	public void propertyChange(PropertyChangeEvent evt) 
	{
		if(evt!=null) 
		{
			if (evt.getPropertyName().equals("escribe")) 
			{
				for (int i = 0; i < casillas.length; i++) 
				{
					for (int j = 0; j < casillas[i].length; j++) 
					{
						
						if (Tablero.getTablero().getCasillaValor(i, j)!=0) 
						{
							casillas[i][j].setText(String.valueOf(Tablero.getTablero().getCasilla(i, j).getValor()));
							if(Tablero.getTablero().getCasilla(i, j) instanceof CasillaInicial) 
							{
								casillas[i][j].setEditable(false);
								casillas[i][j].setForeground(new java.awt.Color(80,93,93));
								casillas[i][j].setFont(new Font("Tahoma", Font.BOLD, 20));					
								casillas[i][j].removeActionListener(getControlador());
									
							}
							else
							{
								casillas[i][j].setForeground(Color.blue);
								casillas[i][j].setDocument(new JTextFieldCharLimit(1));
							}
						}
						
					}
				}
			}
			
			if (evt.getPropertyName().equals("ayuda"))
			{
				@SuppressWarnings("unchecked")
				ArrayList<Integer> datos = (ArrayList<Integer>) evt.getNewValue();
				 
			 	casillas[datos.get(0)][datos.get(1)].setDocument(new JTextFieldCharLimit(1));
				casillas[datos.get(0)][datos.get(1)].setText(String.valueOf(datos.get(2)));
				casillas[datos.get(0)][datos.get(1)].setBackground(Color.orange);
				casillas[datos.get(0)][datos.get(1)].setFont(new Font("Tahoma", Font.ITALIC, 20));
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
			ponerColor(0, 3, 0, 3, 214, 253, 253);
			ponerColor(6, 9, 0, 3, 214, 253, 253);
			ponerColor(0, 3, 6, 9, 214, 253, 253);
			ponerColor(6, 9, 6, 9, 214, 253, 253);
			ponerColor(3, 6, 3, 6, 214, 253, 253);
			
			ponerColor(0, 3, 3, 6, 255, 255, 255);
			ponerColor(3, 6, 0, 3, 255, 255, 255);
			ponerColor(3, 6, 6, 9, 255, 255, 255);
			ponerColor(6, 9, 3, 6, 255, 255, 255);
			
			
			for (int i=0; i<casillas.length; i++) 
			{
				for (int j=0; j<casillas.length; j++) 
				{
					if (Tablero.getTablero().getCasilla(i, j) instanceof CasillaNoInicial==true && Tablero.getTablero().getCasillaValor(i, j)==0 ) 
					{
						casillas[i][j].setFont(new Font("Tahoma", Font.PLAIN, 20));
						casillas[i][j].setForeground(Color.black);
					}
				}
			}
			
			if (candidatos.equals(e.getSource())) 
			{
				actionCandidatos();			
			}
			
			else if (juego.equals(e.getSource()))
			{	
				ocultarMenuDespl();
				actionJuego();
			}
			else if (btnAyuda_1.equals(e.getSource()))
			{	
				actionJuego(); 
				
				ocultarMenuDespl(); 
				Tablero.getTablero().Actionayudas();

			}
			
			else if (comprobar.equals(e.getSource())) 
			{	
				actionJuego();
				ocultarMenuDespl(); 
				actionComprobar();							
			}
			
			else if (finalizar.equals(e.getSource())) 
			{
				actionJuego();
				actionFinalizar();
			}
			
			else if (ana==true && anadir.equals(e.getSource())) 
			{				
				Candidatos cd= new Candidatos();
				cd.asignarContexto(true,fila, columna);
				actionCandidatos();	
				ana=false;
			}
			
			else if (qui==true && quitar.equals(e.getSource())) 
			{
				Candidatos cd= new Candidatos();
				cd.asignarContexto(false,fila, columna);
				actionCandidatos();	
				qui=false;
			}
			
			else if (escribir==true) 
			{					
				ocultarMenuDespl(); 
				actionAsignarValor();	
			}
					
		}
	}

	private void actionCandidatos() 
	{
		for (int x=0; x<casillas.length; x++) 
		{
			for (int y=0; y<casillas.length; y++) 
			{
				
				if (Tablero.getTablero().getCasillaValor(x, y)==0) 
				{	
					casillas[x][y].addMouseListener(getPopClickListener());
					String salida= Tablero.getTablero().transformarStringCandidatos(x, y);
					casillas [x][y].setDocument(new JTextFieldCharLimit(18));
					casillas[x][y].setText(salida);
					casillas[x][y].setForeground(Color.red);
					casillas[x][y].setFont(new Font("Tahoma", Font.PLAIN, 15));
					escribir=false;		
				}
			}
		}
	}
	private void actionJuego() 
	{
		for (int i = 0; i < casillas.length; i++) 
		{
			for (int j = 0; j < casillas[i].length; j++) 
			{
				casillas [i][j].setDocument(new JTextFieldCharLimit(1));
				if (Tablero.getTablero().getCasillaValor(i, j)!=0) 
				{
					casillas[i][j].setText(String.valueOf(Tablero.getTablero().getCasillaValor(i, j)));							
					
						
				}
				else 
				{
					casillas[i][j].setText("");
					
					
				}
				escribir=true;
			}
		}
	}
	private void actionComprobar() 
	{
		for (int i=0; i<9; i++) 
		{
			for (int j=0; j<9; j++) 
			{
				int valor= Tablero.getTablero().getCasillaValor(i, j);
				ArrayList<Integer> casillaFila=Tablero.getTablero().conseguirCasillasFila(Tablero.getTablero().getCasilla(i, j));
				
				int index = casillaFila.indexOf(valor);
				if (casillaFila.contains(valor)) 
				{
					casillaFila.remove(index);
				
				}
				for (int f: casillaFila) 
				{
					if (valor==f) 
					{
						casillas[i][j].setBackground(new java.awt.Color(255,201, 201));
					}
				}
				
				ArrayList<Integer> casillaColumna=Tablero.getTablero().conseguirCasillasColumna(Tablero.getTablero().getCasilla(i, j));
				
				index = casillaColumna.indexOf(valor);
				if (casillaColumna.contains(valor)) 
				{
					casillaColumna.remove(index);
				
				}
				for (int f: casillaColumna) 
				{
					if (valor==f) 
					{
						casillas[i][j].setBackground(new java.awt.Color(255,201, 201));
					}
				}
				ArrayList<Integer> casillaRegion=Tablero.getTablero().conseguirCasillasRegion(Tablero.getTablero().getCasilla(i, j));
				
				index = casillaRegion.indexOf(valor);
				if (casillaRegion.contains(valor)) 
				{
					casillaRegion.remove(index);
				
				}
				for (int f: casillaRegion) 
				{
					if (valor==f) 
					{
						casillas[i][j].setBackground(new java.awt.Color(255,201, 201));
					}
				}
				
			}
		}
	}
	
	private void actionFinalizar() 
	{
		int n=0;

		int correcto []=Tablero.getTablero().convertirANumero(n);
	
		int u=0;
		for (int i=0; i<9; i++) 
		{
			for (int j=0; j<9; j++) 
			{
				if (Tablero.getTablero().getCasillaValor(i, j)!=correcto[u]) 
				{
					Incorrecto in = new Incorrecto();
					actionJuego();
					return; 
				}
				u++;
			}
		}
		actionJuego();
		Correcto cor= new Correcto();
		Tablero.getTablero().finalizar();	
	}
	
	private void actionAsignarValor() 
	{
		for (int i=0; i<casillas.length;i++) 
		{
			for(int j=0; j<casillas.length;j++) 
			{						
				if (casillas[i][j].getText().isBlank()!=true && Tablero.getTablero().getCasilla(i, j) instanceof CasillaNoInicial==true)  
				{													
					int n= Integer.parseInt(casillas[i][j].getText());
					Tablero.getTablero().asignarValor(i,j,n);	
					casillas[i][j].setForeground(Color.blue);
					casillas[i][j].setDocument(new JTextFieldCharLimit(1));
					casillas[i][j].setText(String.valueOf(Tablero.getTablero().getCasillaValor(i, j)));
				}													
			}
		}
	}
	private void ocultarMenuDespl() 
	{
		for (int i=0; i<casillas.length; i++) 
		{
			for (int j=0; j<casillas.length; j++) 
			{
				if(Tablero.getTablero().getCasilla(i, j) instanceof CasillaNoInicial==true) 
				{
					casillas[i][j].removeMouseListener(getPopClickListener());
				}
			}
		}
	}
	
	private PopClickListener getPopClickListener() 
	{
		if (popClick==null) 
		{
			popClick= new PopClickListener();
		}
		return popClick;
	}

	
	@SuppressWarnings("serial")
	class PopUpDemo extends JPopupMenu {
	    
	    public PopUpDemo() {
	    	anadir = new JMenuItem("Añadir candidato");
	        add(anadir);
	        anadir.addActionListener(getControlador());
	        
	        quitar = new JMenuItem("Eliminar candidato");
	        add(quitar);
	        quitar.addActionListener(getControlador());
	    }
	}
	
	class PopClickListener extends MouseAdapter {
		
		
		
	    public void mousePressed(MouseEvent e) {
	        if (e.isPopupTrigger())
	            doPop(e);
	        ana=true;
	        qui=true;
	        //requestFocusInWindow();

	    }

	    public void mouseReleased(MouseEvent e) {
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    private void doPop(MouseEvent e) {
	        PopUpDemo menu = new PopUpDemo();
	        menu.show(e.getComponent(), e.getX(), e.getY());
	    }
	}
	
}