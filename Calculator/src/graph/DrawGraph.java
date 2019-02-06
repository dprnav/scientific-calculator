package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel
{
	Coordinates c;
	Point p;
	double centre_x=170,centre_y=130;
	public DrawGraph(Coordinates c) 
	{
		this.c=c;
		setSize(340,260);
		setBounds(20,40,340,260);
	}
	@Override
	protected void paintComponent(Graphics g) 
	{
		g.setColor(Color.LIGHT_GRAY);
		for(int i=10;i<340;i+=10)
			g.drawLine(i,0,i,260);
		for(int i=10;i<260;i+=10)
			g.drawLine(0,i,360,i);
		g.setColor(Color.BLACK);
		g.drawLine(0,0,360,0);	//top horizontal
		g.drawLine(0,0,0,299);	//left vertical
		g.drawLine(0,259,340,259);	//bottom horizontal
		g.drawLine(339,0,339,259);	//right vertical
		g.drawLine(170,0, 170, 259);	//vertical axis
		g.drawLine(0,130,339,130);		//horizontal axis
		if(c==null)
			return;
		double x=centre_x,y=centre_y;
		Graphics2D g2=(Graphics2D) g;
		g2.setColor(Color.red);
		p=c.p;
		while(p!=null)
		{
			x=centre_x+(p.x*Graph.tune_x);
			y=centre_y-(p.y*Graph.tune_y);
			g2.draw(new Line2D.Double(x, y,centre_x+(p.x*Graph.tune_x), centre_y-(p.y*Graph.tune_y)));
			p=p.link;
		}
	}
}
