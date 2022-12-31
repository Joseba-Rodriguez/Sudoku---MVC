package sudoku.vista;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldCharLimit extends PlainDocument
{
	private int limit;
	
	public JTextFieldCharLimit(int limitation) 
	{
		// TODO Auto-generated constructor stub
		limit=limitation;
	}
	
	public void insertString(int offset,String str, AttributeSet set) throws BadLocationException
	{
		if (str==null) 
		{
			return;
		}
		else if ((getLength() + str.length())<=limit ) 
		{
			str = str.toUpperCase();
			super.insertString(offset, str, set);
		}
		else 
		{
			/*&& (java.lang.Integer.parseInt(str)==1|java.lang.Integer.parseInt(str)==2|java.lang.Integer.parseInt(str)==3
			|java.lang.Integer.parseInt(str)==4|java.lang.Integer.parseInt(str)==5|java.lang.Integer.parseInt(str)==6|java.lang.Integer.parseInt(str)==7
			|java.lang.Integer.parseInt(str)==8|java.lang.Integer.parseInt(str)==9)*/
			
			return;
		}
	}

}