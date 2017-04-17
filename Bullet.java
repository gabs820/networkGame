// Edited from Source: https://www.youtube.com/watch?v=p9Y-NBg8eto

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Bullet extends JPanel {

	double x;
	double y;
	int r;
	double dx;
	double dy;
	double radius;
	double speed;
	
	public Bullet(double angle, int startx, int starty){
		this.x = startx;
		this.y = starty;
		r = 2;
		radius = Math.toRadians(angle);
		dx = Math.cos(radius);
		dy = Math.sin(radius);
	}
	
	public boolean update(){
		x += dx;
		y += dy;
		
		if( x<-r || x > 800 + r 
		|| y < -r || y > 600 + r){
			return true;
		}
		return false;			
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillOval((int)x, (int) y, 10, 10);
	}

	
	
}