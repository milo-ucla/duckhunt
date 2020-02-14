import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.filechooser.*;
import java.lang.*;
public class Duck
{
    // duck dimensions
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width;
    private int height; 
    private double ratio;
    private String img; //now it's a String? oh no what is he up to. . . 
    // private BufferedImage duckRight; 
    // private BufferedImage duckLeft; 
    // private BufferedImage duckRightFlap; 
    // private BufferedImage duckDead;
    // private BufferedImage duckLeftFlap; 
    private boolean isRight;
    private int speed;
    private boolean dead;
    private int flaps;
    private boolean flapped;

    public Duck(int xCoordinate, int yCoordinate)
    {
        dx= (int)(Math.random()*10-5);
        dy= -5;
        speed = (int)(Math.sqrt(dx*dx+dy*dy));
        x = xCoordinate;
        y = yCoordinate;
        flaps = 0;
        isRight=(dx>=0);
        dead = false;
        height = 89;
        width = 100;
        
    }

    public String getImageType(){
        return img;
    }

    public boolean isDead(){
        return dead;
    }

    public int getX(){
        return x;   
    }

    public int getY(){
        return y;   
    }

    public int getDX(){
        return dx;   
    }

    public int getDY(){
        return dy;   
    }

    public void move(){

        try {
            if (!(dead&&(y>=GamePanel.height-height)))//stops drawing when duck hits ground to save RAM
            {
                int ySign = 1; int xSign = 1;
                double speed = Math.sqrt(dx*dx+dy*dy);

                if (!dead){
                    flap();

                    if (dy<0){
                        ySign = -1;
                    }
                    else ySign = 1;
                    if (dx<0){
                        xSign = -1;
                    }
                    else xSign = 1;

                    if (y<=0){
                        y=0; // prevents something from fking up if dy is too small
                        dy = -1*(int)(Math.random()*dy-1); //random number between 1 and 5
                        dx = xSign*(int)(Math.sqrt(speed*speed-dy*dy)+0.5);
                        //System.out.println("Collision: top side");
                        flap();
                    }
                    if(y>=GamePanel.height-height){
                        y=GamePanel.height-height;
                        dy = -1*(int)(Math.random()*dy+1); //random number between -1 and -5
                        dx = xSign*(int)(Math.sqrt(speed*speed-dy*dy)+0.5);
                        //System.out.println("Collision: bottom side");
                        flap();
                    }
                    if(x<=0){
                        x=0;
                        dx = -1*(int)(Math.random()*dx-1);
                        dy = ySign*(int)(Math.sqrt(speed*speed-dx*dx)+0.5);
                        switchDirection();
                        //System.out.println("Collision: left side");
                        flap();
                    }
                    if(x>=GamePanel.width-width){
                        x=GamePanel.width-width-1;
                        dx = -1*(int)(Math.random()*dx+1);
                        dy = ySign*(int)(Math.sqrt(speed*speed-dx*dx)+0.5);
                        switchDirection(); // now you're left duck
                        //System.out.println("Collision: right side");
                        flap();
                    } 
                }
                x+=dx;
                y+=dy;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }}

    public void die(){ 
        y+=-10;
        dx=0;
        dy=15;
        img="duckDead";
        dead=true;

    }

    public void flap(){

        if (flaps%5==0){ //flaps every three movements so that it's not spazzing
            if (isRight){
                if (flapped){
                    img = "duckRight";
                    flapped = false;
                }
                else {
                    img = "duckRightFlap";
                    flapped = true;
                }
            }
            else{
                if (flapped){
                    img = "duckLeft";
                    flapped = false;
                }
                else {
                    img = "duckLeftFlap";
                    flapped = true;
                }
            }
        }
        flaps++;

    }

    public void switchDirection(){
        if (isRight) img = "duckLeft";
        else img = "duckRight";
        isRight = !isRight;
    }

}
