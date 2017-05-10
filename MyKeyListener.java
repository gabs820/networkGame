
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

	//initialize variables
	Timer t = new Timer(5, this);
	int x = 0, y = 0;
	int x2 = 0, y2 = 0;
	double velX = 0, velY = 0;
	boolean shooting;
	double firingTimer;
	double firingDelay;
	public static ArrayList<Bullet> bullets;
	private Graphics2D g;
	int firingAngle=0; //sets bullet direction to equal player direction
	double timeStart;
	double timeEnd;
	public static ArrayList<int[]> others;
	boolean isGameOver;
	public ArrayList<int[]> othersBullets;
	
	public MyKeyListener() {
		x = (int)(Math.random() * 750 + 1); //generates a random starting position.
		y = (int)(Math.random() * 500 + 1);
		t.start();
		addKeyListener(this);// to JPanel
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		shooting = false;
		firingTimer = System.nanoTime();
		firingDelay = 200;
		bullets = new ArrayList<Bullet>();
		timeStart = System.currentTimeMillis();
		isGameOver = false;
		othersBullets = new ArrayList<int[]>();
	}
	
	//this function updates the screen
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
		
		//paint the other player
		for (int a = 0; a < others.size(); ++a) {
			int[] opo = (int[])others.get(a);
			g.fillOval(opo[0], opo[1], 30, 30);
			
		}
		
		//paint other player's bullets
		for (int bb = 0;bb < othersBullets.size(); ++bb) {
			int[] opob = (int[])othersBullets.get(bb);
			g.fillOval(opob[0], opob[1], 10, 10);	
		}
	}

	//retrieve positions of other players for painting
	public void newPos(int pX, int pY)
	{
		others = new ArrayList<int[]>();
		int[] nP = new int[2];
		nP[0] = pX;
		nP[1] = pY;
		others.add(nP);
	}
	
	//retrieve positions of others' bullets for painting
	public void bullPos(ArrayList<int[]> in)
	{
		othersBullets = in;
	}
	
	//method for retrieving others' bullets
	public String getBullets()
	{
		String bString = "B,";
		for (int i = 0; i < bullets.size(); ++i) {
			Bullet b = (Bullet)bullets.get(i);
			bString += (b.getX()+","+b.getY()+",");
		}
		return bString;
	}
	
	//update position
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
		//shooting function
		if(shooting){
			double elapsed = (System.nanoTime() - firingTimer) /1000000;
			//Balance out the shooting rate.
		if(elapsed > firingDelay){
			bullets.add(new Bullet(firingAngle, x+30, y+30));
			firingTimer = System.nanoTime();
		}
		}
		
		//if hit by any bullet, end game
		if(bullets.size()>0)
		{
			for(int i = 0; i < bullets.size(); i++){
				bullets.get(i).update();
				if( bullets.get(i).getX() < (x+15) && bullets.get(i).getX() > (x-15) 
					&& bullets.get(i).getY() < (y+15) && bullets.get(i).getY() > (y-15) )
				{
					timeEnd = System.currentTimeMillis();
					System.out.println("Game over! You survived for " +((timeEnd-timeStart)/1000)+ " seconds.");
					isGameOver = true;
					System.exit(0);
				}
			}
		}
		if(othersBullets.size()>0)
		{
			for(int i = 0; i < othersBullets.size(); i++){
				if( othersBullets.get(i)[0] < (x+15) && othersBullets.get(i)[0] > (x-15) 
					&& othersBullets.get(i)[1] < (y+15) && othersBullets.get(i)[1] > (y-15) )
				{
					timeEnd = System.currentTimeMillis();
					System.out.println("Game over! You survived for " +((timeEnd-timeStart)/1000)+ " seconds.");
					isGameOver = true;
					System.exit(0);
				}
			}
		}
		
		repaint();
	}
	
	// player velocity based on direction you're going.
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
	
	//methods for getting keyboard input
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
	
	//display bullet
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