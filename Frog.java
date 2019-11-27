 

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Frog
{
    // instance variables - replace the example below with your own
    int x,y,w,h,v;
    public Frog()
    {
        x = 250;
        y = 475;
        w = 25;
        h = 25;
        v = 0;
    }
    void move(char c) {
        
            if(c == 'w' && y>=h)
               y -= 25;
            if(c == 's' && y<=500-(2*h))
               y += 25;
            if(c == 'a' && x>=w)
                x -= 25;
            if(c == 'd' && x<=500-(2*w))
                x += 25; 
            //if(x<=500-w) 
                x += v;
        
    }
  
}