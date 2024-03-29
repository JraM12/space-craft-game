package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import utility.Resource;
import imp.GamePanel;
public class Obstacles {
  private class Obstacle {
    BufferedImage image;
    int x;
    int y;

    Rectangle getObstacle() {
      Rectangle obstacle = new Rectangle();
      obstacle.x = x;
      obstacle.y = y;
      obstacle.width = image.getWidth();
      obstacle.height = image.getHeight();

      return obstacle;
    }
  }
  
  private int firstX;
  private int obstacleInterval;
  private int movementSpeed;
  
  private ArrayList<BufferedImage> imageList;
  private ArrayList<Obstacle> obList;

  private Obstacle blockedAt;
  
  public Obstacles(int firstPos) {
    obList = new ArrayList<Obstacle>();
    imageList = new ArrayList<BufferedImage>();
    
    firstX = firstPos;
    obstacleInterval = 300;
    movementSpeed = 11;
    
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
     imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
     imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
     imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));
    imageList.add(new Resource().getResourceImage("../jaya.JPG"));	
    
    int x = firstX;
    
    for(BufferedImage bi : imageList) {
      
      Obstacle ob = new Obstacle();
     Random u=new Random();
      int rand=u.nextInt(325);	     
      ob.image = bi;
      ob.x = x;
      ob.y = rand;
      x += obstacleInterval;
      
      obList.add(ob);
    }
  }
  
  public void update() {
    Iterator<Obstacle> looper = obList.iterator();
    
    Obstacle firstOb = looper.next();
    firstOb.x -= movementSpeed;
    
    while(looper.hasNext()) {
      Obstacle ob = looper.next();
      ob.x -= movementSpeed;
    }
    
    Obstacle lastOb = obList.get(obList.size() - 1);
    
    if(firstOb.x < -firstOb.image.getWidth()) {
      obList.remove(firstOb);
      firstOb.x = obList.get(obList.size() - 1).x + obstacleInterval;
      obList.add(firstOb);
    }
  }
  
  public void create(Graphics g) {
	
    for(Obstacle ob : obList) {
      g.setColor(Color.black);
      // g.drawRect(ob.getObstacle().x, ob.getObstacle().y, ob.getObstacle().width, ob.getObstacle().height);
      g.drawImage(ob.image,ob.x,ob.y,null);
    }
  }
  
   public boolean hasCollided() {
    for(Obstacle ob : obList) {
      if(GamePanel.getShip().intersects(ob.getObstacle())) {
        blockedAt = ob;
        return true;
      }   
    }
    return false;
  }

  public void resume() {
    // this.obList = null;
    int x = firstX/2;   
    obList = new ArrayList<Obstacle>();
    
    for(BufferedImage bi : imageList) {
      
      Obstacle ob = new Obstacle();
       Random u=new Random();
      int rand=u.nextInt(325);
      ob.image = bi;
      ob.x = x;
      ob.y = rand;
      x += obstacleInterval;
      
      obList.add(ob);
    }
  }
  
}