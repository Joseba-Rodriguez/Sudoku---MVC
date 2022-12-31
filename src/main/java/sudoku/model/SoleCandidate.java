package sudoku.model;

import java.util.ArrayList;

public class SoleCandidate implements Ayuda {
	
	@Override
	public ArrayList<Integer> Action() 
	{
		for (int x=0; x<9; x++) 
		{
			for (int y=0; y<9; y++) 
			{				
				if (Tablero.getTablero().getCasillaValor(x, y)==0 && Tablero.getTablero().getCasillaNoInicialCandidatos(x, y).size()==1) 
				{	
					ArrayList<Integer> devolver = new ArrayList<Integer>();
					devolver.add(0, x);
					devolver.add(1, y);
					devolver.add(2, Tablero.getTablero().getCasillaNoInicialCandidatos(x, y).get(0));
					return devolver;																														
				}
			
			}
		}
		ArrayList<Integer> devolver = new ArrayList<Integer>();
		devolver.add(0, -1);
		return devolver;
	}

}