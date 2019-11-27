import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.util.Random;

public class PowerUp
{
    Random rand = new Random();
    int x,y,w,l;
    public PowerUp(int h, int c)
    {
        x = h;
        y = c;
        w = 25;
        l = 25;
    }
    boolean show() {
        return(rand.nextInt(10) == 9); 
        
    }
}
