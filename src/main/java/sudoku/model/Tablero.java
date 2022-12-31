package sudoku.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import sudoku.vista.PrimeraVista;
import sudoku.vista.Puntuaciones;
import sudoku.vista.Vista;

import static java.util.stream.Collectors.toList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Object;


public class Tablero
{
        private Casilla [][] casillas;
        private static Tablero mTablero = new Tablero();
        private PropertyChangeSupport support;
        private String usuario=null;
        private int dificultad=0;
        private boolean finVista1=false;
        private int segundos=0;
        private Date startDate ;
        private Date endDate;
        private int numAyudas=0;
        ArrayList<Usuario> listaPuntuaciones=new ArrayList<Usuario>();

       
       
        private Tablero ()                //constructor
        {
                support = new PropertyChangeSupport(this);
                this.casillas = new Casilla [9][9];       
               
        }
       
       
        public void addObserver(PropertyChangeListener pList) 
        {
    		support.addPropertyChangeListener(pList);
    	}
       
        public void iniciarTablero()
        {
        	
               
                int ch2[]= convertirANumero(1);
                int u=0;
                //System.out.println("");
                //System.out.println("");
               
                for (int i=0; i<casillas.length; i++)
                {
                        for (int j=0; j<casillas.length; j++)
                        {
                        	if (ch2[u]==0)
                            {
                        		casillas [i][j]= new CasillaNoInicial(0,i,j);    

                            }
                               
                            else if(ch2[u]!=0)
                            {
                            	casillas [i][j]= new CasillaInicial(ch2[u],i,j);   

                            }                                                    
                        	u++;                 
                        }
                }
               
                for (int i=0; i<casillas.length;i++)
                {
                        for (int j=0; j<casillas.length;j++)
                        {
                                if (getCasilla(i, j) instanceof CasillaNoInicial && getCasillaValor(i, j)==0)
                                {
                                        //System.out.print("Casilla "+i+j+": ");
                                        actualizarCandidatos((CasillaNoInicial) getCasilla(i, j));
                                }
                        }
                }
                Vista vs= new Vista();
                startDate= new Date();
                System.out.println("manda");
                support.firePropertyChange("escribe", null, null); 
        }
               
        public static Tablero getTablero() {
                return mTablero;
        }
    
        public void asignarUsuario(String pUsuario)
        {
                usuario=pUsuario;
        }
       
        public void asignarDificultad(int pDificultad)
        {
                dificultad=pDificultad;

                if(usuario!=null && dificultad!=0)
                {
                        finVista1=true;
                        iniciarTablero();
                }
        }
        
        public void finalizar() 
        {
    		endDate= new Date();
    		int numSeconds = (int)((endDate.getTime() - startDate.getTime()) / 1000);
    		
    		int puntuacion = ((30000*dificultad)/(numSeconds+(30*numAyudas)));

    		
    		String usuarios="";
    		String dif="";
			String puntua="";
			int tamano=0;
    		
    		File puntuaciones = new File("puntuaciones.txt");
    		Scanner scam;
			try {
				scam = new Scanner(puntuaciones);
				
				String total="";

				while (scam.hasNextLine()) 
				{
					 total=  total.concat(scam.nextLine() + "\n");
					 tamano++;

				}
				
				//System.out.println(total);
				scam.close();
				total= total + usuario+ "\n";
				total= total + dificultad+ "\n";
				total= total + puntuacion + "\n";
				tamano= tamano +3;
				tamano= tamano/3;
				
				try {
					FileWriter writer = new FileWriter("puntuaciones.txt");
					writer.write(total);
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			File mostrar = new File("puntuaciones.txt");
			Scanner scan;
    		
			
			try {
				scan = new Scanner(mostrar);
				
				
				while (scan.hasNextLine()) 
				{
					usuarios=  scan.nextLine();
					dif=  scan.nextLine();
					int dif2= Integer.parseInt(dif);
					puntua=  scan.nextLine();
					int puntua2= Integer.parseInt(puntua);
					
					listaPuntuaciones.add(new Usuario(usuarios, dif2, puntua2));
				}
				
				}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Puntuaciones puntuacionVista= new Puntuaciones();
			
			support.firePropertyChange("puntuacion", null, listaPuntuaciones);

			for (Usuario i:listaPuntuaciones) 
			{
				System.out.println(i.getNombre());
				System.out.println(i.getDificultad());
				System.out.println(i.getPuntos());
			}

			
    		
        }
        
        public void ordenarPuntuaciones(int x) 
        {
        	if (x==0) 
        	{
        		ArrayList<Usuario> devolver=(ArrayList<Usuario>) listaPuntuaciones.stream().sorted(Comparator.comparing (Usuario::getNombre)).collect(toList());
        		support.firePropertyChange("puntuacion", null,devolver);
        	}
        	else if (x==1)
        	{
        		ArrayList<Usuario> devolver=(ArrayList<Usuario>)listaPuntuaciones.stream().sorted(Comparator.comparing (Usuario::getDificultad)).collect(toList());
        		support.firePropertyChange("puntuacion", null,devolver);
        	}
        	else if (x==2)
        	{        		
        		ArrayList<Usuario> devolver=(ArrayList<Usuario>)listaPuntuaciones.stream().sorted(Comparator.comparing (Usuario::getPuntos)).collect(toList());
        		support.firePropertyChange("puntuacion", null,devolver);
        	}     	
        }
        
        public void Actionayudas() 
        {
        	ListaAyudas listaAyudas= new ListaAyudas();
        	ArrayList<Ayuda> lista = listaAyudas.getAyudas(); 
        	for (Ayuda i: lista) 
        	{
        		int devolver= i.Action().get(0);
        		
        		if (devolver!=-1) 
        		{
        			ArrayList<Integer> a = i.Action();
        			numAyudas++;
        			support.firePropertyChange("ayuda", 0, a);
        			return;
        		}
        	}
        }
       
       
        public boolean getfinVista1()
        {               
                return finVista1;       
        }
       
        public Casilla getCasilla(int f, int c)
        {
                return casillas [f][c];
        }
       
        public int dificultad()
        {
                return dificultad;
        }       
        public int getCasillaValor(int f, int c)
        {
                return casillas[f][c].getValor();
        }
       
        public int getCasillaFila (int i, int j)
        {
                return casillas[i][j].getFila();
        }
       
        public int getCasillaColumna (int i, int j)
        {
                return casillas[i][j].getColumna();
        }
        public void asignarValor(int x, int y, int valor)
        {
                if (getCasilla(x, y) instanceof CasillaNoInicial==true)
                {       
                        if (valor>=0)
                        {
                                casillas[x][y].asignarValor(valor);
                               
                              

                        }
                        else
                        {
                                casillas[x][y].asignarValor(0);

                        }
                }
               
                for (int i=0; i<casillas.length;i++)
                {
                        for (int j=0; j<casillas.length;j++)
                        {
                                if (getCasilla(i, j) instanceof CasillaNoInicial && getCasillaValor(i, j)==0)
                                {
                                        //System.out.print("Casilla "+i+j+": ");
                                        actualizarCandidatos((CasillaNoInicial) getCasilla(i, j));
                                }
                        }
                }
             //   support.firePropertyChange("escribe", null, null);
               
        }
       
        public ArrayList<Integer>  conseguirCasillasFila(Casilla casilla)
        {       
                ArrayList<Integer> casillaFila = new ArrayList<Integer>();
                int x=casilla.getFila();
                for (int i=0;i<9;i++)
                {
                        if(getCasillaValor(x, i)!=0)
                        {
                                casillaFila.add(getCasillaValor(x, i));
                                //System.out.print(getCasillaValor(x,i));
                        }
                       
                }
       
                //System.out.println();
               
                return casillaFila;
               
        }
       
        public ArrayList<Integer>  conseguirCasillasColumna(Casilla casilla)
        {       
               
               
               
                ArrayList<Integer> casillaColumna = new ArrayList<Integer>();
                int x=casilla.getColumna();
                for (int i=0;i<9;i++)
                {
                       
                        if(getCasillaValor(i, x)!=0)
                        {
                                casillaColumna.add(getCasillaValor(i, x));
                                //System.out.print(getCasillaValor(i,x));
                        }
                }
                //System.out.println();
               
                return casillaColumna;
               
        }
       
        public String transformarStringCandidatos(int i, int j)
        {
                if (casillas[i][j] instanceof CasillaNoInicial)
                {
                        return ((CasillaNoInicial) casillas[i][j]).transformarString();
                }
                return null;
        }
        public ArrayList<Integer>  conseguirCasillasRegion(Casilla casilla)
        {       
                ArrayList<Integer> casillaRegion = new ArrayList<Integer>();
                ArrayList<Integer> acotaciones=getRegion(casilla.getFila(), casilla.getColumna());
                       
                for (int i=acotaciones.get(0); i<acotaciones.get(1); i++)
                {
                        for(int j=acotaciones.get(2); j<acotaciones.get(3); j++)
                        {
                                if(getCasillaValor(i, j)!=0)
                                {
                                casillaRegion.add(getCasillaValor(i, j));
                                }
                        }
                }
               
                return casillaRegion;
               
        }
       
        public ArrayList<Integer> getRegion(int i, int j)
        {

                int x=i;
                int y=j;
               
               
                if (x==0|x==1|x==2)
                {
                        if(y==0|y==1|y==2)
                        {               
                                ArrayList<Integer> end = new ArrayList<Integer>(Arrays.asList(0,3,0,3));
                                return end;
                        }
               
                        else if(y==3|y==4|y==5)
                        {       
                                ArrayList<Integer> end = new ArrayList<Integer>(Arrays.asList(0,3,3,6));
                                return end;
                        }
                       
                        else if(y==6|y==7|y==8)
                        {               
                                ArrayList<Integer> end = new ArrayList<Integer>(Arrays.asList(0,3,6,9));
                                return end;
                        }                                       
                }
               
                else if (x==3|x==4|x==5)
                {
                        if(y==0|y==1|y==2)
                        {               
                                ArrayList<Integer> end = new ArrayList<Integer>(Arrays.asList(3,6,0,3));
                                return end;
                        }
               
                        else if(y==3|y==4|y==5)
                        {               
                                ArrayList<Integer> end = new ArrayList<Integer>(Arrays.asList(3,6,3,6));
                                return end;
                        }
                       
                        else if(y==6|y==7|y==8)
                        {               
                                ArrayList<Integer> end = new ArrayList<Integer>(Arrays.asList(3,6,6,9));
                                return end;
                        }
                }
               
                else if (x==6|x==7|x==8)
                {
                        if(y==0|y==1|y==2)
                        {               
                                ArrayList<Integer> end = new ArrayList<Integer>(Arrays.asList(6,9,0,3));
                                return end;
                        }
               
                        else if(y==3|y==4|y==5)
                        {               
                                ArrayList<Integer> end = new ArrayList<Integer>(Arrays.asList(6,9,3,6));
                                return end;
                        }
                       
                        else if(y==6|y==7|y==8)
                        {               
                                ArrayList<Integer> end = new ArrayList<Integer>(Arrays.asList(6,9,6,9));
                                return end;
                        }
                }
                return null;                               
        }
       
        private void actualizarCandidatos(CasillaNoInicial casilla)
        {
                ArrayList<Integer> todos = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
               
                ArrayList<Integer> casillaFila= conseguirCasillasFila(casilla);
                eliminarCandidatos(casillaFila, casilla);
                eliminarCandidatosLista(casillaFila, todos);
               
                //System.out.print(casilla.transformarString());
                ArrayList<Integer> casillaColumna =conseguirCasillasColumna(casilla);
                eliminarCandidatos(casillaColumna, casilla);
                eliminarCandidatosLista(casillaColumna, todos);
               
                ArrayList<Integer> casillaRegion= conseguirCasillasRegion(casilla);
                eliminarCandidatos(casillaRegion, casilla);
                eliminarCandidatosLista(casillaRegion, todos);

                eliminarCandidatosLista(casilla.getCandidatos(), todos);
               
                if (todos.size()>0)
                {
                        for (int c: todos)
                        {
                                casilla.agregarCandidato(c);
                        }
                }

        }
       
        public void eliminarCandidatos(ArrayList<Integer> lista,CasillaNoInicial objetivo)
        {       
                for (int c: lista)
                {               
                        objetivo.eliminarCandidato(c);                                                                               
                }
        //        System.out.println();
        }

        public void eliminarCandidatosLista(ArrayList<Integer> lista,ArrayList<Integer>  objetivo)
        {       
                for (Integer c: lista)
                {               
                        //int index =  objetivo.indexOf(c);
                        if ( objetivo.contains(c))
                        {
                                 objetivo.remove(c);
                        }                                                                               
                }
        //        System.out.println();
        }
       
        public ArrayList<Integer> getCasillaNoInicialCandidatos(int i, int j)
        {
                if (casillas[i][j] instanceof CasillaNoInicial)
                {
                        return ((CasillaNoInicial) casillas[i][j]).getCandidatos();
                }
                return null;
        }
       
               
        private String  importar(int q)
        {
                  
                   int x= dificultad;  
                  
                   
                  
                   String num="";

                   if (x==1)
                   {                       
                       int fin1=2;
                       
                           File f =new File("sudocus.txt");
                          
                           if (q==0)
                           {
                        	   fin1=10;
                           }
                          
                           try
                           {
                                Scanner sc = new Scanner(f);

                             //   System.out.println("los numeros");
                                for (int i=0; i<fin1; i++) 
                                {
                                    sc.nextLine();  
                                }

                                for (int i=0; i<9; i++) 
                            	{
                            	   num= num+ sc.nextLine();
                            	}
                                                                                 
                               sc.close();
                           }
                           catch (FileNotFoundException e)
                                   {

                                   e.printStackTrace();
                                   }
                          
                  
                  
                   }
                   else if (x==2)
                   {
                	   int fin1=22;
                	   
                           File f =new File("sudocus.txt");
                          
                           if (q==0)
                           {
                        	   fin1=30;
                           }
                           try
                           {
                        	   Scanner sc = new Scanner(f);

                               //   System.out.println("los numeros");
                                 for (int i=0; i< fin1; i++) 
                                 {
                                     sc.nextLine();  
                                 }


                                   for (int i=0; i<9; i++) 
                              	 {
                              		   num= num+ sc.nextLine();
                              	 }
                                                     
                                 sc.close();
                           }
                                catch (FileNotFoundException e) {

                                e.printStackTrace();
                            }
                   }
                   else if (x==3)
                   {
                	   int fin1= 42;
                       File f =new File("sudocus.txt");
                       if (q==0)
                       {
                    	   fin1=50;
                       }
                       try {
                        	   	Scanner sc = new Scanner(f);

                               //   System.out.println("los numeros");
                                 for (int i=0; i<fin1; i++) 
                                 {
                                     sc.nextLine();  
                                 }


                                   for (int i=0; i<9; i++) 
                              	 {
                              		   num= num+ sc.nextLine();
                              	 }
                                                  
                                 sc.close();
                               }
                                catch (FileNotFoundException e) {

                                e.printStackTrace();
                            }
                          
                   }
                  
                   return num;
        }
       
        public int[] convertirANumero(int q)
        {
        	int ch2[]= new int [81];
        	String num=importar(q);
        	char[] ch=num.toCharArray();
        	for (int i=0; i<ch.length; i++)
        	{
                ch2 [i]= Character.getNumericValue(ch[i]);

              //  System.out.print(ch2[i]);
        	}
        	return ch2;
        }
       
        public void comprobar()
        {
                int nErrores=0;
                int numeros[]=convertirANumero(0);
               
                int conversor[][]= new int [9][9];
                int u=0;
               
                for (int i=0; i<9; i++)
                {
                        for (int j=0; j<9; j++)
                        {
                                conversor[i][j]=numeros[u];
                                u++;       
                        }
                }
               
                for (int i=0; i<9; i++)
                {
                        for (int j=0; j<9; j++)
                        {
                                if (getCasillaValor(i, j)==conversor[i][j])
                                {
                                       
                                }
                                else
                                {
                                        nErrores++;
                                       
                                }
                        }
                }
        }
       
}