package sudoku.model;

import java.util.ArrayList;
import java.util.Arrays;

public class CasillaNoInicial extends Casilla 
{
	private ArrayList<Integer> candidatos = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
	
	
	public CasillaNoInicial(int x, int i, int j) 
	{
		super(x, i, j);
	}
	
	public ArrayList<Integer> getCandidatos () 
	{
		return candidatos;
	}
	
	public void eliminarCandidato (int x) 
	{
		candidatos.remove(x);
	}
	
	public void añadirCandidato (int x) 
	{
		candidatos.add(x);
	}
	
	
}