import java.util.*;
import java.awt.*;
import javax.swing.*;
//import java.awt.event.KeyEvent;
import java.awt.event.*; 
import java.util.Random;

public class RightCar
{
    int x, y, w, l, v;
    Random rand = new Random();
    
    RightCar (int h, int c){
        x = h; 
        y = c;
        w = 60*(rand.nextInt(2)+1);
        l = 25;
        v = rand.nextInt(10) + 5;
    }
    public void Move(){

           x = x + v;
   
    }
}
