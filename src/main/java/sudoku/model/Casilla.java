package sudoku.model;

public class Casilla 
{

	
	private int valor=0;
	private int fila;
	private int columna;

	
	public Casilla (int pValor, int f, int c) //constructor
	{
		valor=pValor ;
		fila= f;
		columna=c;
	}
	
	public int getValor() 
	{
		return valor;
	}
	
	public void asignarValor (int pValor) 
	{
		if (pValor>=0 && pValor<10) 
		{
			valor=pValor;
		}	
	}

	
	public int getFila() 
	{
		return fila;
	}
	
	public int getColumna() 
	{
		return columna;
	}
}