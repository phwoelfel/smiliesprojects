package Widerstandsschaltung;

import java.awt.Graphics;

public class Spannungsquelle {
	public int widerstand;
	public int spannung;
	public int strom;
	public int x;
	public int y;
	public int pos;
	
	
	public Spannungsquelle(int sp){
		widerstand=0;
		spannung=sp;
		strom=0;
	}
	public Spannungsquelle(int sp, int xa, int ya){
		widerstand=0;
		spannung=sp;
		strom=0;
		x=xa;
		y=ya;
	}
	public Spannungsquelle(int sp, int xa, int ya, int posi){
		widerstand=0;
		spannung=sp;
		strom=0;
		x=xa;
		y=ya;
		pos=posi;
	}
	
	public void draw(Graphics g, int mode){
		if(pos==2){
			g.drawOval(x, y, 20, 20);
			g.drawLine(x, y+10, x+20, y+10);
			if(mode==1){
				g.drawString(spannung +" V", x+5, y+35);
			}
		}
		else{
			g.drawOval(x, y, 20, 20);
			g.drawLine(x+10, y, x+10, y+20);
			if(mode==1){
				g.drawString(spannung +" V", x+25, y+15);
			}
		}
	}
	
	public void draww(int x, int y, Graphics g, int mode){
		g.drawOval(x, y, 20, 20);
		g.drawLine(x, y+10, x+20, y+10);
		if(mode==1){
			g.drawString(spannung +" V", x+5, y+35);
		}
	}
	
	public void draws(int x, int y, Graphics g, int mode){
		g.drawOval(x, y, 20, 20);
		g.drawLine(x+10, y, x+10, y+20);
		if(mode==1){
			g.drawString(spannung +" V", x+25, y+15);
		}
	}
}
