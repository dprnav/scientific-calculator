package graph;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Scientific.FrameDesign;

@SuppressWarnings("serial")
public class Graph extends Frame
{
	Graph frame;
	JTextField t;
	JSlider slider_x,slider_y;
	static int tune_x=20,tune_y=20;
	String graph_eqn;
	Coordinates c;
	JButton b;
	DrawGraph draw_graph;
	public Graph(FrameDesign f,String s)
	{
		frame=this;
		setSize(380,457);
		setLocationRelativeTo(this);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		setTitle("Graph");
		draw_graph=new DrawGraph(c);
		add(draw_graph);
		b=new JButton("Plot Graph");
		graph_eqn=s;
		addComponents();
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent arg0) 
			{
				dispose();
				f.setVisible(true);
			}
		});
		slider_x.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) 
			{
				int value=slider_x.getValue();
				tune_x=value;
				draw_graph.setVisible(false);
				draw_graph=new DrawGraph(c);
				frame.add(draw_graph);
			}
		});
		slider_y.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) 
			{
				int value=slider_y.getValue();
				tune_y=value;
				draw_graph.setVisible(false);
				draw_graph=new DrawGraph(c);
				frame.add(draw_graph);
			}
		});
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				if(ae.getActionCommand().equalsIgnoreCase("plot graph"))
				{
					if(c==null)
					{
						c=new Coordinates();
						c.getCoordinates(graph_eqn);
						if(c.p==null)
							t.setText("No Graph");
					}
					draw_graph.setVisible(false);
					draw_graph=new DrawGraph(c);
					frame.add(draw_graph);
				}
			}
		});
	}
	
	void addComponents()
	{
		t=new JTextField();
		t.setSize(320,20);
		t.setBounds(20,320,340,40);
		t.setFont(new Font("SansSerif", Font.PLAIN, 25));
		t.setText(graph_eqn);
		t.setEditable(false);
		add(t);
		slider_x=new JSlider(JSlider.HORIZONTAL,1,100,tune_x);
		slider_y=new JSlider(JSlider.HORIZONTAL,1,100,tune_y);
		slider_x.setBounds(20,360,170,40);
		slider_y.setBounds(190,360,170,40);
		slider_x.setBackground(null);
		add(slider_x);
		add(slider_y);
		b.setSize(340,40);
		b.setBounds(20,400,340,40);
		b.setFocusPainted(false);
		b.setBackground(Color.ORANGE);
		add(b);
	}
}
