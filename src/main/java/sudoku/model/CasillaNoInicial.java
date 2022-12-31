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
                return new ArrayList<>(candidatos);
        }
       

       
        public String transformarString()
        {
                String resultado="";
                for(int n: candidatos)
                {
                        resultado= resultado + String.valueOf (n)+" ";
                }
                return resultado;
        }
       
        public void eliminarCandidato (int x)
        {
                int index = candidatos.indexOf(x);
                if (candidatos.contains(x))
                {
                candidatos.remove(index);               
                }
        }
       
        public void agregarCandidato (int x)
        {       
                if (candidatos.contains(x))
                {
                        return;
                }
                candidatos.add(x);       
        }
       
       
}