package imp;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import utility.Resource;
import java.util.ArrayList;
import components.Ground;
import components.Obstacles;

public class GamePanel extends JPanel implements KeyListener, Runnable {
  
  public static int WIDTH;
  public static int HEIGHT;
  private Thread animator;
  
  private boolean running = false;
  private boolean gameOver = false;
  
  Ground ground;
  Obstacles obstacles;
  private int score;
private static int X, Y;
 
  int dx,dy;
 
  public static final int STAND_STILL = 1, DIE = 2;
                    
  private static int state;

  private int foot;

  static BufferedImage image;
  BufferedImage dead;



  public void spaceship() {
    image = new Resource().getResourceImage("../spaceship.png");
    dead = new Resource().getResourceImage("../spaceship.png");

  	Y = Ground.GROUND_Y - image.getHeight() + 5;
  	X = 100;
    state = 1;
     }

  public void create(Graphics g) {

    switch(state) {

      case STAND_STILL:
        System.out.println("stand");
        g.drawImage(image, X, Y, null);
        break;
      case DIE: 
	int i;
        g.drawImage(dead, X, Y, null); 
	g.setColor(Color.blue);

	g.setFont(new Font("TimesRoman", Font.BOLD, 30)); 
	g.drawString("GAMEOVER",X,Y); 
	
	
        
        break;     
    }
  }

  public void die() {
    state = DIE;
  }

  public static Rectangle getShip() {
    Rectangle spaceship = new Rectangle();
    spaceship.x =X;
    spaceship.y=Y;
    spaceship.width = image.getWidth();
    spaceship.height = image.getHeight();

    return spaceship;
  }

  public void Game() {
    WIDTH = UserInterface.WIDTH;
    HEIGHT = UserInterface.HEIGHT;
    
    ground = new Ground(HEIGHT);

	this.spaceship();
    obstacles = new Obstacles((int)(WIDTH * 1.5));

    score = 0;
    
    setSize(WIDTH, HEIGHT);
    setVisible(true);
setBackground(Color.black);
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
g.setColor(Color.red);
    g.setFont(new Font("Courier New", Font.BOLD, 25));
    g.drawString(Integer.toString(score), getWidth()/2 - 5, 100);
    ground.create(g);
    this.create(g);
    obstacles.create(g);
  }
  
  public void run() {
    running = true;

    while(running) {
      updateGame();
      repaint();      
      try {
        Thread.sleep(20);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void updateGame() {
    score += 1;

    ground.update();

    obstacles.update();

    if(obstacles.hasCollided()) {
      this.die();
      repaint();
      running = false;
      gameOver = true;
      System.out.println("collide");
    }
     //game complete condition
  }

  public void reset() {
    score = 0;
    System.out.println("reset");
    obstacles.resume();
    gameOver = false;
  }
  public void move(int dx,int dy) {
        
        X += dx;
	
        if(Y<=250 && Y>=50){	
	Y +=dy;
}else if(Y>=250){
Y -=50;
}else{
Y +=50;
}


     state = 1;
    }
  public void keyTyped(KeyEvent e) {
    
   
    if(e.getKeyChar() == 'j') {    
      if(gameOver) reset();
      if (animator == null || !running) {
        System.out.println("Game starts");        
        animator = new Thread(this);
        animator.start();     
       
        
      } else {
        
      }
    }
  }
  public void keyPressed(KeyEvent e){
int key = e.getKeyCode();
if (key == KeyEvent.VK_LEFT) {
            dx = -50;
            dy=0;
	this.move(dx,dy);
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 50;
	    dy=0;	
	   this.move(dx,dy);
        }

        if (key == KeyEvent.VK_UP) {
            dy = -50;
	    dx=0;
this.move(dx,dy);
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 50;
 	    dx=0;
	this.move(dx,dy); 
}  }
  
  public void keyReleased(KeyEvent e) {
  }
}
