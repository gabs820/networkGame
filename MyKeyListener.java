// Edited from Source: https://www.youtube.com/watch?v=p9Y-NBg8eto

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
public class MyKeyListener extends JPanel implements ActionListener, KeyListener {


	Timer t = new Timer(5, this);
	int x = 0, y = 0;
	double velX = 0, velY = 0;
	boolean shooting;
	double firingTimer;
	double firingDelay;
	public static ArrayList<Bullet> bullets;
	private Graphics2D g;
	
	public MyKeyListener() {
		t.start();
		//addKeyListener(this);// to JPanel
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		shooting = false;
		firingTimer = System.nanoTime();
		firingDelay = 200;
		bullets = new ArrayList<Bullet>();
		addKeyListener(this);// to JPanel
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(x, y, 30, 30);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
		x += velX;
		y += velY;
		//System.out.println("x vel: " + velX + "y vel: " + velY);
		if(shooting){
			double elapsed = (System.nanoTime() - firingTimer) /1000000;
		if(elapsed > firingDelay){
			System.out.println("shooting!");
			bullets.add(new Bullet(0, x, y));
			firingTimer = System.nanoTime();
		}
		}
		if(bullets.size()>0)
		{
			for(int i = 0; i < bullets.size(); i++){
				bullets.get(i).update();
				//bullets.get(i).paintComponent(g);
			}
		}
		
	}
	
	// For some reason negative velocity seems to be faster??
	public void up() {
		velY = -0.5;
		velX = 0;
	}
	public void down() {
		velY = 1.5;
		velX = 0;
	}
	public void left() {
		velY = 0;
		velX = -0.5;
	}
	public void right() {
		velY = 0;
		velX = 1.5;
	} 
	
	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			up();
		}
		if (code == KeyEvent.VK_DOWN) {
			down();
		}
		if (code == KeyEvent.VK_LEFT) {
			left();
		}
		if (code == KeyEvent.VK_RIGHT) {
			right();
		}
		if(code == KeyEvent.VK_SPACE){
			shooting = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_SPACE){
			shooting = false;
		}
	}
	
	// Need to include these because of the implements KeyListener
	public void keyTyped(KeyEvent e) {}
}

class Bullet extends JPanel {

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
	
	public void update(){
		x += dx;
		y += dy;
		repaint();
		System.out.println("1. X: "+x+"; Y:"+y );
		if( x<-r || x > 800 + r 
		|| y < -r || y > 600 + r){
			//return 0;
		}
		//return false;			
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillOval((int)x, (int) y, 10, 10);
		
	}

	
	
}