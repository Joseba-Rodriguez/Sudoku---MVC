package sudoku.model;

public class Usuario {
	
	private String nombre;
	private int dificultad;
	private int puntos;
	
	public Usuario(String pNombre, int pDif,int pPuntos) {
		// TODO Auto-generated constructor stub
		nombre=pNombre;
		dificultad=pDif;
		puntos=pPuntos;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public int getDificultad() 
	{
		return dificultad;
	}
	
	public int getPuntos() 
	{
		return puntos;
	}

}