package sudoku.model;

import java.util.ArrayList;

public class ListaAyudas {
	
	private ArrayList<Ayuda> ayudas = new ArrayList<Ayuda>();
	
	ListaAyudas()
	{
		ayudas.add(new SoleCandidate());
		ayudas.add(new UniqueCandidate());
	}
	
	public ArrayList<Ayuda> getAyudas ()
	{
		return new ArrayList<> (ayudas);
	}

}
