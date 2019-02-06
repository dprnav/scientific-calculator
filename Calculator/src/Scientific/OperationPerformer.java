package Scientific;
import java.text.DecimalFormat;
import java.util.Stack;

class Expression
{
	Double operand;
	Character operator;
	boolean status;	//Operand=True::Operator=False
	Expression link;
}
public class OperationPerformer
{
	String s;
	Expression exp;
	DecimalFormat dff;
	public OperationPerformer() 
	{
		exp=null;
		dff=new DecimalFormat(".####");
	}
	public String getAnswer(String s)
	{
		this.s=s;
		createInfixStack();
		return evaluateInfix(new Stack<Character>(),new Stack<Double>());
	}
	void createInfixStack()
	{
		replaceWithSynonym();
		int l=this.s.length();
		for(int i=0;i<l;i++)
		{
			char ch=this.s.charAt(i);
			if(ch=='+' || ch=='-')
			{
				char ch1;
				if (i!=0)
					ch1=this.s.charAt(i-1);
				else
					ch1='\0';
				if(i==0 || ch1=='|' || ch1=='(' || ch1=='^' || ch1=='/' 
						|| ch1=='*' || ch1=='-' || ch1=='+' || ch1=='s'
						|| ch1=='c'|| ch1=='t'|| ch1=='S' || ch1=='C'
						|| ch1=='T'|| ch1=='l'|| ch1=='L' || ch1=='%')
				{
					int j=i+1;
					while(j<l && (Character.isDigit(this.s.charAt(j)) || this.s.charAt(j)=='.'))
						j++;
					if(j!=i+1)
					{
						push(this.s.substring(i, j),true);
						i=j-1;
						continue;
					}
				}
				push(String.valueOf(ch),false);
			}
			else if(Character.isDigit(ch) || ch=='.')
			{
				int j=i+1;
				while(j<l && (Character.isDigit(this.s.charAt(j)) || this.s.charAt(j)=='.'))
					j++;
				if(j!=i+1)
				{
					push(this.s.substring(i, j),true);
					i=j-1;
					continue;
				}
				push(String.valueOf(ch),true);
			}
			else
			{
				if(ch=='e')
					push(String.valueOf(Math.E),true);
				else if(ch=='\u03C0')
					push(String.valueOf(Math.PI),true);
				else
					push(String.valueOf(ch),false);
			}
		}
	}
    void replaceWithSynonym()
	{
		this.s=this.s.replaceAll( "\u221A","|");
		this.s=this.s.replaceAll( "\u00F7","/");				
		this.s=this.s.replaceAll( "\u00D7","*");
		this.s=this.s.replaceAll( "sin\u207B\u00B9","S");	
		this.s=this.s.replaceAll( "cos\u207B\u00B9","C");				
		this.s=this.s.replaceAll( "tan\u207B\u00B9","T");
		this.s=this.s.replaceAll( "sin","s");				
		this.s=this.s.replaceAll( "cos","c");				
		this.s=this.s.replaceAll( "tan","t");								
		this.s=this.s.replaceAll( "log","l");				
		this.s=this.s.replaceAll( "ln","L");	
		this.s=this.s.replaceAll( "ANS",Display.ANS);
	}
    @SuppressWarnings("deprecation")
	void push(String s,boolean c)
    {
    	Expression ex=new Expression();
    	ex.status=c;
    	ex.link=null;
    	if(c)
    	{
    		try
    		{
    			ex.operand=new Double(Double.parseDouble(s));
    		}
    		catch(NumberFormatException e)
    		{
    			throw new RuntimeException();
    		}
    	}
		else
		{
			ex.operator=new Character(Character.valueOf(s.charAt(0)));
		}
    	if(exp==null)
    	{
    		exp=ex;
    	}
    	else
    	{
	    	ex.link=exp;
	    	exp=ex;
    	}
    }
    void delete()
    {
    	this.s=null;
    	this.exp=null;
    }
    String evaluateInfix(Stack<Character> oprtr,Stack<Double> oprnd)
    {
    	while(exp!=null)
    	{
    		if(exp.status)
    			oprnd.push(exp.operand);
    		else
    		{
    			if(exp.operator=='s'|| exp.operator=='c'|| exp.operator=='t'|| exp.operator=='S' || exp.operator=='C'|| exp.operator=='T'|| exp.operator=='l'|| exp.operator=='L')
    			{
    				oprnd=SolveInfix.solveInfix(oprnd, exp.operator);
    			}
    			else if(oprtr.empty() || getPriority(oprtr.peek())<=getPriority(exp.operator))
    			{
    				if(exp.operator==')')
    				{
    					exp=exp.link;
    					oprnd.push(Double.parseDouble(evaluateInfix(new Stack<Character>(),new Stack<Double>())));	
    				}
    				else
    					oprtr.push(exp.operator);
    			}
    			else if(getPriority(oprtr.peek())>getPriority(exp.operator))
    			{
    				if(exp.operator=='(')
    				{
    					
    					return String.valueOf(dff.format(oprnd.pop()));
    				}
    				else
    				{
	    				while(!oprtr.empty() && oprtr.peek()>getPriority(exp.operator) )
						{
							oprnd=SolveInfix.solveInfix(oprnd,oprtr.pop());
						}
	    				oprtr.push(exp.operator);
    				}
    			}
    		}
    		if(exp!=null)
    			exp=exp.link;
    	}
    	while(!oprtr.empty())
    	{
    		oprnd=SolveInfix.solveInfix(oprnd, oprtr.pop());
    	}
    	if((oprnd.size()==1 && oprtr.empty()))
    		return String.valueOf(dff.format(oprnd.pop()));
    	else
    		throw new RuntimeException();
    }
    int getPriority(char ch)
    {
    	if(ch=='+' || ch=='-')
    		return 0;
    	else if(ch=='*'  || ch=='/')
    		return 1;
    	else if(ch=='^')
    		return 2;
    	else 
    		return 3;
    }
    
}

