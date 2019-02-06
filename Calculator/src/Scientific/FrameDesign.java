package Scientific;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JTextField;

import graph.Graph;
@SuppressWarnings("serial")
public class FrameDesign extends Frame implements ActionListener,KeyListener
{
	Display display;
	JTextField t;
	JButton b[];
	JButton nw,nw1;
	int x=0,y=125,l=62,w=100;
	public FrameDesign()
	{
		setTitle("Calculator");
		setSize(380,497);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		designWindow();
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
	}
	void designWindow()
	{
		t=new JTextField();
		display=new Display(t,t.getCaretPosition());
		add(t);
		t.setBounds(8,25,370,100);
		t.setFont(new Font("SansSerif", Font.PLAIN, 50));
		t.setBorder(null);
		t.setEditable(false);
		SolveInfix.setAngle("Rad");
		b=new JButton[33];
	
		Font f=new Font("SansSerif", Font.BOLD, 20);
		Font f1=new Font("SansSerif", Font.PLAIN, 20);
		
		
		b[0]=new JButton("Graph");
		b[1]=new JButton("x");
		b[2]=new JButton("ANS");
		b[3]=new JButton("CE");
		b[4]=new JButton("%");
		b[5]=new JButton("\u221A");
		b[6]=new JButton("x\u207f");  
		b[7]=new JButton("\u232b");
		b[8]=new JButton("7");
		b[9]=new JButton("8");
		b[10]=new JButton("9");
		b[11]=new JButton("\u00F7");
		b[12]=new JButton("4");
		b[13]=new JButton("5");
		b[14]=new JButton("6");
		b[15]=new JButton("\u00D7");
		b[16]=new JButton("1");
		b[17]=new JButton("2");
		b[18]=new JButton("3");
		b[19]=new JButton("-");
		b[20]=new JButton("\u22C5");
		b[21]=new JButton("0");
		b[22]=new JButton("=");
		b[23]=new JButton("+");
		
		for(int i=0;i<24;i++)
		{
			add(b[i]);
			b[i].setBorder(null);
			b[i].setBackground(Color.white);
			b[i].setFocusPainted(false);
		}
		
		for(int i=0,j=7;i<24;i++)
		{
			if((i>=0 && i<=3))
			{
				b[i].setBackground(Color.GRAY);
				b[i].setFont(f1);
			}
			else if((i>=4 && i<=6) || i==j)
			{
				b[i].setBackground(Color.LIGHT_GRAY);
				b[i].setFont(f1);
				if(i==j)
					j+=4;
			}
			else
			{
				if(i!=22)
				  b[i].setFont(f);
				else
					b[i].setFont(f1);
			}
		}
		b[0].setBounds(x,y,w,l);
		b[1].setBounds(x+100,y,w,l);
		b[2].setBounds(x+200,y,w,l);
		b[3].setBounds(x+300,y,w-40,l);
		b[4].setBounds(x+15,y+62,w-15,l);
		b[5].setBounds(x+100,y+62,w,l);
		b[6].setBounds(x+200,y+62,w,l);
		b[7].setBounds(x+300,y+62,w-40,l);
		b[8].setBounds(x,y+124,w,l);
		b[9].setBounds(x+100,y+124,w,l);
		b[10].setBounds(x+200,y+124,w,l);
		b[11].setBounds(x+300,y+124,w-40,l);
		b[12].setBounds(x,y+186,w,l);
		b[13].setBounds(x+100,y+186,w,l);
		b[14].setBounds(x+200,y+186,w,l);
		b[15].setBounds(x+300,y+186,w-40,l);
		b[16].setBounds(x,y+248,w,l);
		b[17].setBounds(x+100,y+248,w,l);
		b[18].setBounds(x+200,y+248,w,l);
		b[19].setBounds(x+300,y+248,w-40,l);
		b[20].setBounds(x,y+310,w,l);
		b[21].setBounds(x+100,y+310,w,l);
		b[22].setBounds(x+200,y+310,w,l);
		b[23].setBounds(x+300,y+310,w-40,l);
		
		nw=new JButton();
		nw.setBorder(null);
		nw.setBounds(x+360,y,20,372);
		nw.setBackground(Color.orange);
		nw.setActionCommand("false");
		nw.setFocusable(false);
		add(nw);
		nw1=new JButton();
		nw1.setBorder(null);
		nw1.setBounds(x,y+62,15,l);
		nw1.setBackground(Color.orange);
		nw1.setActionCommand("false");
		nw1.setFocusable(false);
		add(nw1);
		nw.addActionListener(this);
		nw1.addActionListener(this);
		for(int i=0;i<24;i++)
		{
			b[i].setFocusable(false);
			b[i].addActionListener(this);
		}
		addKeyListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent ae) 

	{
		String s=ae.getActionCommand();
		if(ae.getSource()==nw)
		{
				if(s.equalsIgnoreCase("false"))
				{
					b[0].setText("sin");
					b[1].setText("cos");
					b[2].setText("tan");
					b[4].setText("sin\u207B\u00B9");
					b[5].setText("cos\u207B\u00B9");
					b[6].setText("tan\u207B\u00B9");
					b[11].setText("log");
					b[15].setText("ln");
					b[19].setText("(");
					b[23].setText(")");
					nw.setActionCommand("true");
					nw1.setActionCommand("false");
				}
				else
				{
					b[0].setText("Graph");
					b[1].setText("x");
					b[2].setText("ANS");
					b[4].setText("%");
					b[5].setText("\u221A");
					b[6].setText("x\u207f");
					b[11].setText("\u00F7");
					b[15].setText("\u00D7");
					b[19].setText("-");
					b[23].setText("+");
					nw.setActionCommand("false");
					nw1.setActionCommand("false");
				}
	
		}
		else if(ae.getSource()==nw1)
		{
				if(s.equalsIgnoreCase("false"))
				{
					b[4].setText("\u03C0");
					b[5].setText("e");
					b[6].setText(SolveInfix.getAngle());
					nw1.setActionCommand("true");
				}
				else if(nw.getActionCommand().equalsIgnoreCase("false"))
				{
					b[4].setText("%");
					b[5].setText("\u221A");
					b[6].setText("x\u207f");
					nw1.setActionCommand("false");
				}
				else
				{
					b[4].setText("sin\u207B\u00B9");
					b[5].setText("cos\u207B\u00B9");
					b[6].setText("tan\u207B\u00B9");
					nw1.setActionCommand("false");
				}
		}
		else if(s.equalsIgnoreCase("Graph"))
		{
			this.setVisible(false);
			new Graph(this,t.getText());
		}
		else if(s.equalsIgnoreCase("CE"))
		{
			display.ce();
		}
		else if(s.equalsIgnoreCase("\u232b"))
		{
			display.del();
		}
		else if(ae.getSource()==b[6] && nw1.getActionCommand().equalsIgnoreCase("true") )
		{
			if(s.equalsIgnoreCase("deg"))
				SolveInfix.setAngle("Rad");
			else
				SolveInfix.setAngle("Deg");
			b[6].setText(SolveInfix.degree);
		}
		else
		{
			display.addString(s);
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) 
	{	
	}
	@Override
	public void keyReleased(KeyEvent arg0) 
	{
	}
	@Override
	public void keyTyped(KeyEvent ke) 
	{
		char ch=ke.getKeyChar();
		switch(ch)
		{
			case '0':b[21].doClick();
			break;
			case '1': b[16].doClick();
					break;
			case '2': b[17].doClick();
			break;
			case '3': b[18].doClick();
			break;
			case '4': b[12].doClick();
			break;
			case '5': b[13].doClick();
			break;
			case '6': b[14].doClick();
			break;
			case '7': b[8].doClick();
			break;
			case '8': b[9].doClick();
			break;
			case '9': b[10].doClick();
			break;
			case '.': b[20].doClick();
			break;
			case '=': b[22].doClick();
			break;
			case KeyEvent.VK_ENTER: b[22].doClick();
			break;
			case KeyEvent.VK_BACK_SPACE: b[7].doClick();
			break;
			case KeyEvent.VK_DELETE: b[3].doClick();
			break;
		}
	}
}
