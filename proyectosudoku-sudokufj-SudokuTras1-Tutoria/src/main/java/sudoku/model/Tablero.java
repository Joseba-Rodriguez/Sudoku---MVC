package sudoku.model;

import java.util.ArrayList;



import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Tablero 
{
	private Casilla [][] casillas;
	private static Tablero mTablero = new Tablero();
	private PropertyChangeSupport support;
	private String usuario=null;
	private int dificultad=0;
	private boolean finVista1=false;
	
	
	private Tablero ()		//constructor
	{
		support = new PropertyChangeSupport(this);
		this.casillas = new Casilla [9][9];	
		
	}
	
	public void addObserver(PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(listener);
	}
	
	public void iniciarTablero() 
	{
		for (int i=0; i<casillas.length; i++) 
		{
			for (int j=0; j<casillas.length; j++) 
			{
				Casilla pCasilla = new Casilla (0,i,j);
			}
		}
	}
	
	public Casilla[][] getCasillas ()
	{
		return this.casillas;
	}
	
	public static Tablero getTablero() {
		return mTablero;
	}
	
	public void asignarUsuario(String pUsuario) 
	{
		usuario=pUsuario;
		System.out.println(usuario);
		if(usuario!=null && dificultad!=0) 
		{
			finVista1=true;
			System.out.println(finVista1);	
			//support.firePropertyChange(pUsuario, true, finVista1);
		}
	}
	
	public void asignarDificultad(int pDificultad) 
	{
		dificultad=pDificultad;
		System.out.println(dificultad);
		System.out.println(usuario);
		if(usuario!=null && dificultad!=0 && usuario!="") 
		{
			if (usuario!=null) 
			{
			finVista1=true;
			System.out.println(finVista1);
			//support.firePropertyChange(null);
			}
		}
	}
	
	
	public boolean getfinVista1() 
	{
		return finVista1;
	}
	
	public void reiniciarCasilla (int pFila, int pColumna) 
	{
		this.casillas[pFila][pColumna].asignarValor(0);
		actualizarCandidatos(casillas[pFila][pColumna]);
	}
	
	public void asignarValor(int x, int y, int valor) 
	{
		casillas[x][y].asignarValor(valor);
		actualizarCandidatos(casillas[x][y]);
		support.firePropertyChange(null);
	}
	
	private ArrayList<Casilla>  conseguirCasillasFila(Casilla casilla)
	{	
		ArrayList<Casilla> casillaFila = new ArrayList<Casilla>();
		int x=casilla.getFila();
		for (int i=0;i<this.casillas.length;i++) 
		{
			casillaFila.add(casillas[x][i]);
		}
		
		return casillaFila;
		
	}
	
	private ArrayList<Casilla>  conseguirCasillasColumna(Casilla casilla)
	{	
		ArrayList<Casilla> casillaColumna = new ArrayList<Casilla>();
		int x=casilla.getColumna();
		for (int i=0;i<this.casillas.length;i++) 
		{
			casillaColumna.add(casillas[i][x]);
		}
		
		return casillaColumna;
		
	}
	
	private ArrayList<Casilla>  conseguirCasillasRegion(Casilla casilla)
	{	
		ArrayList<Casilla> casillaRegion = new ArrayList<Casilla>();
		int x=casilla.getColumna();
		int y=casilla.getFila();
		for (int i=0;i<this.casillas.length;i++) 
		{
			casillaRegion.add(casillas[i][x]);
		}
		
		return casillaRegion;
		
	}
	
	private void actualizarCandidatos(Casilla casilla) 
	{
		ArrayList<ArrayList<Casilla>> total = new ArrayList<ArrayList<Casilla>>();
		total.add(conseguirCasillasFila(casilla));
		total.add(conseguirCasillasColumna(casilla));
		
		ArrayList<Integer> valores= new ArrayList<Integer>();
		
		for (ArrayList<Casilla> lista : total)	//sacamos las listas de dentro de la lista par ir mirandolas una por una.
		{	
			for (Casilla pCasilla:lista)	//sacamos las casillas de la lista una por una
			{	
				if (pCasilla.getValor()!=0)
				{
					valores.add(pCasilla.getValor());
				}
				
			}
		}
		
		for (ArrayList<Casilla> lista : total)	//sacamos las listas de dentro de la lista par ir mirandolas una por una.
		{	
			for (Casilla pCasilla:lista)	//sacamos las casillas de la lista una por una.
			{
				if(pCasilla instanceof CasillaNoInicial)	//Nos quedamos con las casillas que no sean iniciales.
				{
					for (int nValor: valores) 	//vamos sacando todos los valores de las casillas uno a uno
					{
						for (int nCandidato: ((CasillaNoInicial) pCasilla).getCandidatos()) //sacamos todos los candidatos que tengan las casillas uno a uno
						{
							if (nValor== nCandidato) 
							{
								((CasillaNoInicial) pCasilla).eliminarCandidato(nCandidato); //si son iguales, eliminamos ese valor como posible candidato de esa casilla
								support.firePropertyChange(null);
							}
						}
					}
				}
			}
		}
		
		
		

	}
	
	
}
