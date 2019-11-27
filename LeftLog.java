import java.util.*;
import java.awt.*;
import javax.swing.*;
//import java.awt.event.KeyEvent;
import java.awt.event.*; 
import java.util.Random;

public class LeftLog
{
    int x, y, w, l, v;
    Random rand = new Random();
    
    LeftLog (int h, int c){
        x = h; 
        y = c;
        w = 100 + (rand.nextInt(3)*20);
        l = 25;
        v = rand.nextInt(2) + 1;
    }
    public void Move(){
        
           x = x - v;
       
    }
}