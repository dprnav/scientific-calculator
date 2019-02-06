package graph;

import Scientific.OperationPerformer;

class Point
{
	double x,y;
	Point link; 
	Point(double x,double y)
	{
		this.x=x;
		this.y=y;
		link=null;
	}
}
public class Coordinates
{
	Point p,last;
	void getCoordinates(String s)
	{
		if(s.isEmpty())
			return;
		OperationPerformer o=new OperationPerformer();
		for(double i=-10;i<=10;i+=.001)
		{
			String rslt=o.getAnswer(s.replaceAll("x",String.valueOf(i)));
			if(rslt.equalsIgnoreCase("nan") || rslt.equalsIgnoreCase("syntax error"))
				continue;
			double ans=Double.parseDouble(rslt);
			push(i,ans);
		}
	}
	void push(double x,double y)
	{
		Point temp=new Point(x,y);
		if(p==null)
		{
			p=temp;
			last=p;
		}
		else
		{
			last.link=temp;
			last=temp;
		}
	}
}