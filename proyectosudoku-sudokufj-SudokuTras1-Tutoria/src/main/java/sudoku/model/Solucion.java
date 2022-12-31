package sudoku.model;

import java.util.ArrayList;
import sudoku.model.Casilla;
import sudoku.model.Tablero;

public class Solucion
{
	
	private ArrayList<Casilla> conseguirCasillasNoIniciales (Casilla [][] casillas) 
	{
		ArrayList<Casilla> noIniciales = new ArrayList<Casilla>();
		
		for (int i=0; i<casillas.length; i++)
		{
			for (int j=0; j< casillas[i].length; j++)
			{
			//	if (casillas[i][j].getNoInicial()) 
				//{
					//noIniciales.add(casillas[i][j]);
				//}
			}
		}
		return noIniciales;
	}
	/*
	 * Este método es para recoger todas las casillas que no sean las que hemos puesto como iniciales y meterlas en una lista
	 */
	
	private ArrayList<ArrayList<Casilla>> conseguirCasillasComparar (Tablero pTablero, Casilla pCasilla)
	{
		ArrayList<Casilla> fila = new ArrayList<Casilla>();
		ArrayList<Casilla> columna = new ArrayList<Casilla>();
		ArrayList<Casilla> region = new ArrayList<Casilla>();
		
		for (int i=0; i<pTablero.getCasillas().length; i++) 
		{
			for (int j=0; j<pTablero.getCasillas()[i].length; j++) 
			{
				if (pTablero.getCasillas()[i][j].getFila() == pCasilla.getFila()) 
				{
					fila.add(pTablero.getCasillas()[i][j]);
				}
				
				if (pTablero.getCasillas()[i][j].getColumna() == pCasilla.getColumna()) 
				{
					columna.add(pTablero.getCasillas()[i][j]);
				}
				
				//if (pTablero.getCasillas()[i][j].getRegion() == pCasilla.getRegion()) 
				//{
					//region.add(pTablero.getCasillas()[i][j]);
				//}
			}
		}
		
		ArrayList<ArrayList<Casilla>> total = new ArrayList<ArrayList<Casilla>>();
		
		total.add(fila);
		total.add(columna);
		total.add(region);
		
		return total;
	}
	
	/*
	 * Este método es para recoger todas las casillas con las que se debe comparar una casilla que nosotros hayamos seleccionado.
	 * Guarda en distintas colecciones las que estén en la misma fila, región o columna.
	 * Finalmente, mete estas 3 colecciones en una lista, que es la que nos devuelve al final.
	 */
	
	private boolean comprobarCorrecto (ArrayList<ArrayList<Casilla>> pListas) 
	{
		for (ArrayList<Casilla> lista : pListas) //sacamos las listas de dentro de la lista par ir mirandolas una por una.
		{
			ArrayList<Integer> valores= null;
			valores = new ArrayList<Integer>();
			
			for (Casilla pCasilla:lista)
			{
				if (pCasilla.getValor() !=0) 	//nos quitamos las casillas que aún puedan estar vacias
				{
					if (valores.contains(pCasilla.getValor())) //Si hay alguna otra casilla en la misma region/columna/fila, nos devuelve false
					{											//oséa, es incorrecto.
						return false;
					}
					else 
					{
						valores.add(pCasilla.getValor());	//Si no hay ninguna otra casilla igual, la añade a la lista y la usará para compararla
					}										//con el resto de casillas que nos quedan por comprobar.
				}
			}
		}
		
		return true;
	}
	/*
	 * La idea de este método es extraer las colecciones del método anterior (fila, columna, region) e ir comparando uno a uno los valores que 
	 * ya están puestos en las casillas con el de un valor, para comprobar que no está repetido.
	 */
	
	
	public Tablero solucionFinal (Tablero pTablero) 
	{
		ArrayList<Casilla> noIniciales = this.conseguirCasillasNoIniciales(pTablero.getCasillas());
		int i=0;
		
		while (i< noIniciales.size()) 
		{
			Casilla pCasilla = noIniciales.get(i);
			
			if (pCasilla.getValor()>9) 
			{
				pTablero.reiniciarCasilla(pCasilla.getFila(), pCasilla.getColumna());
				i--;
				
				if (i<0) 
				{
					return null;
				}
				
				else 
				{
					pCasilla = noIniciales.get(i);
					pCasilla.asignarValor(pCasilla.getValor() + 1);
					//pTablero.editarCasilla(pCasilla);
				}
			}
			
			else
			{
				if (pCasilla.getValor()==0) 
				{
					pCasilla.asignarValor(1);
				}
			}
			
			ArrayList<ArrayList<Casilla>> totalComparar = this.conseguirCasillasComparar(pTablero, pCasilla);
			
			if (comprobarCorrecto(totalComparar)== true) 
			{
				
				//pTablero.editarCasilla(pCasilla);
				i++;			
			}
			
			else
			{
				pCasilla.asignarValor(pCasilla.getValor() +1 );
				
				if (pCasilla.getValor()>9) 
				{
					pTablero.reiniciarCasilla(pCasilla.getFila(), pCasilla.getColumna());
					
					if (i !=0) 
					{
						i--;
						pCasilla = noIniciales.get(i);
						pCasilla.asignarValor(pCasilla.getValor() +1);
						//pTablero.editarCasilla(pCasilla);
					}
					
					else 
					{
						return null;
					}
				}
				
			}
		}
		
		return pTablero;
	}
	
	
}