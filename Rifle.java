// import java.awt.event.MouseListener;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import javax.swing.*;
// import java.awt.*;

// import java.awt.*;
import java.awt.image.*;
// import java.awt.event.*;
// import java.io.File;
import java.util.*;
// import javax.swing.*;
// import java.io.*;
// import javax.imageio.*;
// import java.lang.*;

import java.io.File;
import java.io.*;
import javax.imageio.*;
import javax.swing.filechooser.*;
import java.awt.*;
import javax.swing.*;
public class Rifle {
    //where initialization occurs:
    //Register for mouse events on blankArea and the panel.
    BufferedImage crosshair;
    int x;
    int y;
    public Rifle (){ 
        try{        
            crosshair = ImageIO.read(new File("images/crosshair.png"));
        }
        catch(Exception e){
        }
    }

    public void update (int newX, int newY){

        x = newX;
        y = newY;
    }

    public void drawRifle(Graphics g){
        g.drawImage(crosshair, (int)(x)-15, (int)(y)-15,  null);
    }

    // public double getMouseX(){
    // location = MouseInfo.getPointerInfo().getLocation();
    // return location.getX();
    // }
    // public double getMouseY(){
    // location = MouseInfo.getPointerInfo().getLocation();
    // return location.getY();
    // }
}