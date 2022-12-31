package sudoku.model;


import java.util.ArrayList;

public class UniqueCandidate implements Ayuda{
	

	@Override
	public ArrayList<Integer> Action() 
	{
		// TODO Auto-generated method stub
		for (int x=0; x<9; x++) 
		{
			for (int y=0; y<9; y++) 
			{
				if (Tablero.getTablero().getCasillaValor(x, y)==0) 
				{		
					ArrayList<Integer> cotas= Tablero.getTablero().getRegion(x, y);
					ArrayList<Integer> objetivoCandidatos= Tablero.getTablero().getCasillaNoInicialCandidatos(x, y);
					int filaC= Tablero.getTablero().getCasillaFila(x, y);
					int columnaC= Tablero.getTablero().getCasillaColumna(x, y);
					
					for (int i: objetivoCandidatos) 
					{
						boolean correcto=true;
						boolean inF=true;
						
						while (inF==true) 
						{
							for (int f=cotas.get(0); f<cotas.get(1); f++) 
							{
								for (int c=cotas.get(2); c<cotas.get(3); c++) 
								{
									if (Tablero.getTablero().getCasillaValor(f, c)==0 && (c!=columnaC || f!= filaC)) 
									{
										ArrayList<Integer> s= Tablero.getTablero().getCasillaNoInicialCandidatos(f, c);
										for (int j: s) 
										{
											if (i==j) 
											{
												correcto=false;
												inF=false;
											}
										}
									}
								}	
							}
							inF=false;
						}
						
						if (correcto==true) 
						{
							ArrayList<Integer> devolver = new ArrayList<Integer>();
							devolver.add(0, x);
							devolver.add(1, y);							
							int index = objetivoCandidatos.indexOf(i);
							devolver.add(2, Tablero.getTablero().getCasillaNoInicialCandidatos(x, y).get(index));
							return devolver;
						}
					}
				}
			}			
		}
		ArrayList<Integer> devolver = new ArrayList<Integer>();
		devolver.add(0, -1);
		return devolver;
	}

}