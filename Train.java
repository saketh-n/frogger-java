import java.util.*;
import java.awt.*;
import javax.swing.*;
//import java.awt.event.KeyEvent;
import java.awt.event.*; 
import java.util.Random;

public class Train
{
    int x, y, w, l, v;
    Random rand = new Random();
    
    Train (int h, int c){
        x = h; 
        y = c;
        w = 250;
        l = 25;
        v = (rand.nextInt(5) + 5)*5;
    }
    public void Move(){

           x = x + v;
   
    }
}
