package Scientific;
import java.util.Stack;

public class SolveInfix
{
	static String degree;
	static Stack<Double> solveInfix(Stack<Double> oprnd,char ch)
	{
			switch(ch)
			{
				case 'l':oprnd.push(Math.log10(oprnd.pop()));
				break;
				case 'L':oprnd.push(Math.log(oprnd.pop()));
				break;
				case '|':oprnd.push(Math.sqrt(oprnd.pop()));
				break;
				case '+':oprnd.push(oprnd.pop()+oprnd.pop());
					break;
				case '-':double d1=oprnd.pop(),d2=oprnd.pop();
					oprnd.push(d1-d2);
				break;
				case '*':oprnd.push(oprnd.pop()*oprnd.pop());
				break;
				case '/':double d3=oprnd.pop(),d4=oprnd.pop();
						oprnd.push(d3/d4);
						break;
				case '^':double d5=oprnd.pop(),d6=oprnd.pop();
					oprnd.push(Math.pow(d5,d6));
				break;
				case '%':double d7=oprnd.pop(),d8=oprnd.pop();
				oprnd.push((d7*d8/100));
				default:double d=oprnd.pop();
						if(degree.equalsIgnoreCase("deg") && (ch=='s' || ch=='c' || ch=='t'))
							d=SolveInfix.convertToRadian(d);
						switch(ch)
						{
								case 's':d=(Math.sin(d));
								break;
								case 'c':d=(Math.cos(d));
								break;
								case 't':d=(Math.tan(d));
								break;
								case 'S':d=(Math.asin(d));
								break;
								case 'C':d=(Math.acos(d));
								break;
								case 'T':d=(Math.atan(d));
								break;
						}
						if(degree.equalsIgnoreCase("deg") && (ch=='S' || ch=='C' || ch=='T'))
							d=SolveInfix.convertToDegree(d);
						oprnd.push(d);
			}
			return oprnd;
	}
	static String getAngle()
    {
    	return degree;
    }
    static void setAngle(String degree)
    {
    	SolveInfix.degree=degree;
    }
    static double convertToRadian(double deg)
    {
    	return (deg*Math.PI/180);
    }
    static double convertToDegree(double rad)
    {
    	return (rad*180/Math.PI);
    }
}
