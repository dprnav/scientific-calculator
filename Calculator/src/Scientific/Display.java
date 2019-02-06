package Scientific;
import javax.swing.JTextField;

public class Display
{
		String s;
		static String ANS;
		JTextField t;
		OperationPerformer o;
		final int pos;
		Display(JTextField t,int i)
		{
			o=new OperationPerformer();
			s=new String();
			ANS=new String();
			this.t=t;
			pos=i;
		}
		void addString(String s)
		{
			if(!s.equalsIgnoreCase("="))
			{
				if(s.equalsIgnoreCase("x\u207f"))
					s="^";
			    if(s.equalsIgnoreCase("\u22C5"))
					s=".";
				else if(!ANS.equalsIgnoreCase("") && this.s.equalsIgnoreCase("") && (s.equalsIgnoreCase("+") || s.equalsIgnoreCase("-") || s.equalsIgnoreCase("\u00D7") || s.equalsIgnoreCase("\u00F7") || s.equalsIgnoreCase("^") || s.equalsIgnoreCase("%")))
					s=ANS+s;
				this.s+=s;
				t.setText(this.s);
			}
			else
			{
				getResult();
			}
		}
		void ce()
		{
			this.s="";
			t.setText(null);
			ANS="";
		}
		void del()
		{
			try
			{
				int l=this.s.length(),l2=1;
				switch(this.s.charAt(this.s.length()-1))
				{
					case 'S':l2=3;
							break;
					case '\u00B9':l2=5;
							break;
					case 'g':l2=3;
							break;
					case 'n': if(this.s.charAt(this.s.length()-2)=='l')
								l2=2;
						   	  else
								l2=3;
							break;
					case 's':l2=3;
							break;
				}
				if(l!=0)
				{
					this.s=this.s.substring(0,l-l2);
				}
				t.setText(this.s);
			}
			catch(StringIndexOutOfBoundsException e)
			{

			}
			
		}
		void getResult()
		{
			try
			{
				ANS=o.getAnswer(this.s);
				t.setText(ANS);
				t.setCaretPosition(pos);
			}
			catch(Exception e)
			{
				t.setText("Syntax Error");
				t.setCaretPosition(pos);
			}
			o.delete();
			this.s="";
		}
}
