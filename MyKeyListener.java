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
import java.util.Random;
public class MyKeyListener extends JPanel implements ActionListener, KeyListener {


	Timer t = new Timer(5, this);
	int x = 0, y = 0;
	double velX = 0, velY = 0;
	boolean shooting;
	double firingTimer;
	double firingDelay;
	public static ArrayList<Bullet> bullets;
	private Graphics2D g;
	int firingAngle=0; //sets bullet direction to equal player direction
	
	public MyKeyListener() {
		x = (int)(Math.random() * 750 + 1); //generates a random starting position.
		y = (int)(Math.random() * 500 + 1);
		System.out.println("X: "+x+" Y: "+y);
		t.start();
		addKeyListener(this);// to JPanel
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		shooting = false;
		firingTimer = System.nanoTime();
		firingDelay = 200;
		bullets = new ArrayList<Bullet>();
	}
	
	public void paintComponent(Graphics g) {
		//paint the player
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(x, y, 30, 30);

		//paint each bullet
		for (int i = 0; i < bullets.size(); ++i) {
			Bullet b = (Bullet)bullets.get(i);
			if(b.isVisible())
			{
				g.fillOval(b.getX(), b.getY(), 10, 10);
			}
			if(!b.isVisible()) // deletes bullet out of array list.
			{
				bullets.remove(i);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		       
		if(x<750)
		{
			x += velX;     
		}
		else
		{
			x-=velX;
		}
		if(y<500)
		{
			y += velY;		
		}
		else
		{
				y-=velY;
		}
		if(shooting){
			double elapsed = (System.nanoTime() - firingTimer) /1000000;
			//Balance out the shooting rate.
		if(elapsed > firingDelay){
			bullets.add(new Bullet(firingAngle, x, y));
			firingTimer = System.nanoTime();
		}
		}
		if(bullets.size()>0)
		{
			for(int i = 0; i < bullets.size(); i++){
				bullets.get(i).update();
			}
		}
		repaint();
	}
	
	// For some reason negative velocity seems to be faster??
	public void up() {
		velY = -0.5;
		velX = 0;
		firingAngle = 270;
	}
	public void down() {
		velY = 1.5;
		velX = 0;
		firingAngle = 90;
	}
	public void left() {
		velY = 0;
		velX = -0.5;
		firingAngle = 180;
	}
	public void right() {
		velY = 0;
		velX = 1.5;
		firingAngle = 0;
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

	double x; double y;
	int r;
	double dx; double dy;
	double radius;
	double speed;
	boolean visible; //responsible for painting
	
	public Bullet()
	{
		super();
	}
	
	public Bullet(double angle, int startx, int starty){
		this();
		this.x = startx;
		this.y = starty;
		visible = true;
		r = 2;
		radius = Math.toRadians(angle);
		dx = Math.cos(radius); //sets angle of firing
		dy = Math.sin(radius);
	}
	
	//getters
	public int getX()
	{
		return (int)x;
	}
	public int getY()
	{
		return (int)y;
	}
	public boolean isVisible()
	{
		return visible;
	}
	
	public void update(){
		x += dx*2; //bullet moves twice the speed of the player.
		y += dy*2;
		
		//stop drawing if beyond these pixel dimensions.
		if(x>800 || x<-1)		
		{
			visible = false;
		}
		if(y>600|| y < -1)
		{
			visible = false;
		}
		
	}
	
}