package imp;
import imp.GamePanel;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import components.Ground;
import java.awt.Color;
import utility.Resource;
public class UserInterface {
  JFrame mainWindow = new JFrame("Sp@cE Cr@FT");
  
  public static int WIDTH = 800;
  public static int HEIGHT = 500;

  
  public void create() {
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    Container container = mainWindow.getContentPane();
  

  GamePanel gamePanel = new GamePanel();
   gamePanel.Game();
    gamePanel.addKeyListener(gamePanel);
    gamePanel.setFocusable(true);
    
    container.setLayout(new BorderLayout());
    
  container.add(gamePanel,BorderLayout.CENTER);
    mainWindow.setSize(WIDTH, HEIGHT);
    mainWindow.setResizable(false);
    mainWindow.setVisible(true);
  }
  
  public static void main(String[] args) {
   
        new UserInterface().create();
     
  }
}