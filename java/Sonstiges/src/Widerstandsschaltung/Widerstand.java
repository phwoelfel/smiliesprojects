package Widerstandsschaltung;

import java.awt.Graphics;

public class Widerstand {
	public int widerstand;
	public int spannung;
	public int strom;
	public int x;
	public int y;
	public int pos;
	
	
	public Widerstand(int w){
		widerstand=w;
		spannung=0;
		strom=0;
	}
	public Widerstand(int w, int xa, int ya){
		widerstand=w;
		spannung=0;
		strom=0;
		x=xa;
		y=ya;
	}
	public Widerstand(int w, int xa, int ya, int posi){
		widerstand=w;
		spannung=0;
		strom=0;
		x=xa;
		y=ya;
		pos=posi;
	}
	
	public void draw(Graphics g, int mode){
		if(pos==1){
			g.drawRect(x, y, 10, 30);
			if(mode==1){
				g.drawString(widerstand +" Ohm", x+15, y+20);
			}
		}
		else{
			g.drawRect(x, y, 30, 10);
			if(mode==1){
				g.drawString(widerstand +" Ohm", x+15, y-10);
			}
		}
	}
	
	
	public void draws(int x, int y, Graphics g, int mode){
		g.drawRect(x, y, 10, 30);
		if(mode==1){
			g.drawString(widerstand +" Ohm", x+15, y+20);
		}
	}
	
	public void draww(int x, int y, Graphics g, int mode){
		g.drawRect(x, y, 30, 10);
		if(mode==1){
			g.drawString(widerstand +" Ohm", x+15, y-10);
		}
	}
}
