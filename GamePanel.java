
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.filechooser.*;
import javax.swing.Timer;
import java.awt.event.*;
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener{
    public static int width = 1000;//(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static int height = 800;//(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    BufferedImage grass = null;
    private double ratio;
    private int duckHeight;
    private int duckWidth;
    private int score;
    private int ducksKilled;
    private boolean gameOver;
    private Font font;
    private BufferedImage duckImg; 
    private BufferedImage duckRight; 
    private BufferedImage duckLeft; 
    private BufferedImage duckRightFlap; 
    private BufferedImage duckDead;
    private BufferedImage duckLeftFlap; 
    private BufferedImage bullet;
    private ArrayList<Duck> ducks = new ArrayList<Duck>();
    private Point location;
    public Rifle r = new Rifle();
    public int bullets;
    public int level;

    ActionListener taskPerformer = new ActionListener(){
            public void actionPerformed(ActionEvent evt){                
                repaint();
            }
        };
    public GamePanel(int delay) //starts game
    {
        //RunGame.startGame();
        new Timer(delay, taskPerformer).start();
        JFrame f = new JFrame();
        f.add(this);
        f.setSize(width, height);
        f.setVisible(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        try {
            grass = ImageIO.read(new File("images/background.png"));
            duckRight = ImageIO.read(new File("images/duckRight.png"));
            duckLeft = ImageIO.read(new File("images/duckLeft.png"));
            duckRightFlap = ImageIO.read(new File("images/duckRightFlap.png"));
            duckLeftFlap = ImageIO.read(new File("images/duckLeftFlap.png"));
            duckDead = ImageIO.read(new File("images/duckDead.png"));
            bullet = ImageIO.read(new File("images/bullet.png"));
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
        level = 1;        
        duckHeight = duckRight.getHeight();
        ratio  = 100.00/duckRight.getWidth();
        duckWidth = 100;
        duckHeight = (int)(ratio*duckHeight);
        score = 0;
        font = new Font("Comic Sans MS", Font.BOLD, 30);
        spawnDucks();  
        bullets = 4;
        ducksKilled = 0;
        gameOver = false;

    }

    private void spawnDucks(){
        for (int x = 0; x<level+2; x++){
            Duck d = new Duck((int)(Math.random()*width), height-50);
            ducks.add(d);
        }
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(0,0, width, height);
        g.setColor(Color.black);
        g.setFont(font);
        if (!gameOver) g.drawString(score +"", 10, 50);
        if (gameOver) g.drawString("You Suck!", width/2-100, height/2);

        for (Duck d : ducks){
            d.move();
            switch (d.getImageType()){
                case "duckRight" : g.drawImage(duckRight, d.getX(), d.getY(), duckWidth, duckHeight,  null);
                break;
                case "duckLeft" : g.drawImage(duckLeft, d.getX(), d.getY(), duckWidth, duckHeight,  null);
                break;
                case "duckLeftFlap":g.drawImage(duckLeftFlap, d.getX(), d.getY(), duckWidth, duckHeight,  null);
                break;
                case "duckRightFlap":g.drawImage(duckRightFlap, d.getX(), d.getY(), duckWidth, duckHeight,  null);
                break;
                case "duckDead":g.drawImage(duckDead, d.getX(), d.getY(), duckWidth, duckHeight,  null);
                break;
            }
        }
        for (int x = 0; x<bullets; x++){
            g.drawImage(bullet, width-50-10*x, 50, 30,48,null);
        }
        r.drawRifle(g);
        g.drawImage(grass, 0, -50, 1000, 800,  null);

        //
    }

    public void mousePressed(MouseEvent e) {  
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {     
    }

    public void mouseExited(MouseEvent e) {        
    }

    public void mouseMoved(MouseEvent e){
        r.update(e.getX(),e.getY());
    }

    public void mouseDragged(MouseEvent e) {        
    }

    public void mouseClicked(MouseEvent e) {
        bullets--;
        for (Duck d : ducks){
            if ((e.getX()-d.getX()<100)&&(e.getX()-d.getX()>-70)&&((e.getY()-d.getY()<100)&&(e.getY()-d.getY()>-70))){
                d.die();
                score+=(200-Math.abs(e.getX()-d.getX()))*(level);
                ducksKilled++;
            }
        }

        if (!(bullets<0)&&(ducksKilled==ducks.size())){
            score+=bullets*100;
            level++;
            spawnDucks();
            bullets = level+3;
        }
        if ((bullets<=0)&&(ducksKilled!=ducks.size())){
            gameOver = true;
        }

        //System.out.println(e.getX() +":"+ e.getY());
    }

    public void saySomething(String eventDescription, MouseEvent e) {
    }

} // close ImageEdito