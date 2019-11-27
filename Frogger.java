import java.util.*;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
public class Frogger extends JApplet implements Runnable, KeyListener
{
    Thread t;
    ArrayList <RightCar> cars = new ArrayList();
    ArrayList <LeftCar> leftcars = new ArrayList();
    ArrayList <Frog> frogs = new ArrayList();
    ArrayList <Log> logs = new ArrayList();
    ArrayList <LeftLog> leftlogs = new ArrayList ();
    ArrayList <Train> trains = new ArrayList ();
    ArrayList <PowerUp> powerups = new ArrayList();
    ArrayList <CarSpace> carspaces = new ArrayList();
    ArrayList <TrainSpace> trainspaces = new ArrayList();
    boolean froghit = false, gameover = false;
    int lives = 4, count = 16, rng = 0, level = 1;
    Random rand = new Random();
    public void init()
    {
        setFocusable(true);
        t = new Thread(this);
        t.start();
        for (int i = 0; i < 1; i++){
            frogs.add(new Frog());
        }
        for(int h = 150; h < 500; h+=100){
            for (int c = 0; c < 1; c++){
               
                    cars.add(new RightCar(-100, h));
                    carspaces.add(new CarSpace(0,h+25));
                
            }
        }
        for(int h = 200; h < 500; h += 100) {
            for(int c = 0; c < 1; c ++) {
                leftcars.add(new LeftCar(500, h));
                carspaces.add(new CarSpace(0,h+25));
            }
        }
        for(int h = 25; h < 125; h+= 50) {
            for(int c = 0; c < 1; c++) {
                
                    logs.add(new Log(-100,h));
                
                
            }
        }
        for(int h = 50; h < 125; h += 50) {
            for(int c = 0; c < 1; c++) {
                leftlogs.add(new LeftLog(500, h));
            }
        }
       
        addKeyListener(this);
    }

    public void run(){
        try{
            while(true){
                if(froghit == false) {
                   for(int i = 0; i < cars.size(); i++){
                   
                   cars.get(i).Move();
                    
                    
                    int temp;
                 
                    if(cars.get(i).x >= 500 ) {
                        temp = cars.get(i).y;
                        cars.remove(i);
                        cars.add(new RightCar(-100, temp)); 
                        
                    }
                    
                    
                  if(
                            cars.get(i).x + cars.get(i).w >= frogs.get(0).x && 
                            cars.get(i).x + cars.get(i).w >= frogs.get(0).x + frogs.get(0).w &&
                            cars.get(i).x <= frogs.get(0).x &&
                            cars.get(i).y == frogs.get(0).y  ){
                            froghit = true;
                        }
                  }   
                }
                if(froghit == false) {
                    for(int i = 0; i < leftcars.size(); i++) {
                        leftcars.get(i).Move();
                        int temp;
                     
                        if(leftcars.get(i).x <= -leftcars.get(i).w ) {
                            temp = leftcars.get(i).y;
                            leftcars.remove(i);
                            leftcars.add(new LeftCar(500, temp)); 
                            
                        }
                         if(
                            leftcars.get(i).x <= frogs.get(0).x && 
                            leftcars.get(i).x + leftcars.get(i).w >= frogs.get(0).x + frogs.get(0).w &&
                            leftcars.get(i).y == frogs.get(0).y  ){
                            froghit = true;
                        }
                    }
                }
                for(int i = 0; i < logs.size(); i++) {
                    logs.get(i).Move();
                    int temp;
                    
                        if(
                            logs.get(i).x + logs.get(i).w >= frogs.get(0).x && 
                            logs.get(i).x + logs.get(i).w >= frogs.get(0).x + frogs.get(0).w &&
                            logs.get(i).x <= frogs.get(0).x &&
                            logs.get(i).y == frogs.get(0).y &&
                            logs.get(i).y + logs.get(i).l == frogs.get(0).y + frogs.get(0).h){
                                //Very buggy/inconsistent
                            frogs.get(0).v = logs.get(i).v;
                        }
                        else
                        {
                            frogs.get(0).v = 0;
                            /* Makes you die if you go in the water, but is buggy, so we commented it out if(frogs.get(0).y == logs.get(0).y || frogs.get(0).x > 500) 
                                froghit = true;*/
                        }
                        
                        if(logs.get(i).x >= 500) {
                        temp = logs.get(i).y;
                        logs.remove(i);
                        logs.add(new Log(-100, temp)); 
                        
                    }
                     
                }
                for(int i = 0; i < leftlogs.size(); i++) {
                    leftlogs.get(i).Move();
                    int temp;
                     if(
                            leftlogs.get(i).x <= frogs.get(0).x && 
                            leftlogs.get(i).x + leftlogs.get(i).w >= frogs.get(0).x + frogs.get(0).w &&
                            leftlogs.get(i).y == frogs.get(0).y  ){
                                //Very buggy/inconsistent
                            frogs.get(0).v = -logs.get(i).v;
                        } else {
                            frogs.get(0).v = 0;
                           /* Makes you die if you go in the water, but is buggy, so we commented it out if(frogs.get(0).y == leftlogs.get(0).y || frogs.get(0).x < 0) 
                                froghit = true;*/
                        }
                    
                   
                        if(leftlogs.get(i).x <= -leftlogs.get(i).w) {
                        temp = leftlogs.get(i).y;
                        leftlogs.remove(i);
                        leftlogs.add(new LeftLog(500, temp));
                    }
                }
                if(froghit == false) {
                     for(int i = 0; i < trains.size(); i++) {
                        trains.get(i).Move();
                        int temp;
                        if(trains.get(i).x >= 2000) {
                            temp = trains.get(i).y;
                            trains.remove(i);
                            trains.add(new Train(-300, temp));
                        }
                       if( trains.get(i).x + trains.get(i).w >= frogs.get(0).x && 
                            trains.get(i).x + trains.get(i).w <= frogs.get(0).x + frogs.get(0).w &&
                            trains.get(i).y == frogs.get(0).y ) {
                              froghit = true;  
                         }
                    }
            
                }
               
                 for(int i = 0; i < frogs.size(); i++){
                    Frog tmp = frogs.get(i);
                    tmp.move('z');
                    
                    repaint();
                }
                for(int i = 0; i < powerups.size(); i++) {
                    if(frogs.get(0).x == powerups.get(i).x && frogs.get(0).y == powerups.get(i).y) {
                        powerups.remove(i);
                        lives++;
                    }
                }
                t.sleep(16);
                count ++;
                repaint();

            }
        }catch (InterruptedException e){}
        repaint();
    }

    public void paint (Graphics g)
    {
        g.setColor(new Color(54, 118, 60));
        g.fillRect(0,0,500,500);
       
        if(frogs.get(0).y != 0) {
            if(froghit == false) {
             showStatus("Welcome to Frogger. Movement: w,a,s,d. Reach the other side. Lives:" + lives + " Level:" + level);
            }
            if(froghit) {
                if(lives > 1) 
                    showStatus("You died. Press R to Restart");
                if(lives <= 1)
                    showStatus("Game Over. Levels Completed: " + level);
            }
        }
        if(frogs.get(0).y == 0) {
            showStatus("Level Complete! Press N to Continue.");
        }
       
        for(int i = 0; i < trains.size(); i++) {
            g.setColor(new Color(93,79,69));
            g.fillRect(0, (int)trains.get(i).y, 500, 25);
            g.setColor(new Color(102,51,0));
            for(int w = 0; w < 500; w+=20) {
                g.fillRect(w, (int) trains.get(i).y, 5, 25);
            }
            g.setColor(new Color(224, 224, 219));
            g.fillRect(0, (int) trains.get(i).y, 500, 5);
            g.fillRect(0, (int) trains.get(i).y + 20, 500, 5);
              for(int j = 0; j < powerups.size(); j++) {
                g.setColor(new Color(255, 215, 0));
                g.fillRect((int) powerups.get(j).x, (int) powerups.get(j).y, (int) powerups.get(j).w, (int) powerups.get(j).l);
            }
            g.setColor(new Color(43, 91, 100));
            g.fillRect((int) trains.get(i).x, (int) trains.get(i).y, (int) trains.get(i).w, (int) trains.get(i).l);
        }
        for(int i = 0; i < trainspaces.size(); i++) {
             g.setColor(new Color(93,79,69));
            g.fillRect(0, (int)trainspaces.get(i).y, 500, 25);
        }
        for(int i = 0; i < cars.size(); i++){
           g.setColor(new Color(86,82,87));
            g.fillRect(0, (int) cars.get(i).y, 500, 25);
           // g.setColor(new Color(86, 176, 0));
           // g.fillRect(0, (int) cars.get(i).y + 25, 500, 25);
            g.setColor(new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random())));
            
            g.fillRect((int) cars.get(i).x, (int) cars.get(i).y, (int) cars.get(i).w, (int) cars.get(i).l);
        }
        for(int i = 0; i < carspaces.size(); i++) {
            g.setColor(new Color(86, 176, 0));
            g.fillRect(0, (int) carspaces.get(i).y, 500, 25);
        }
        for(int i = 0; i< leftcars.size(); i++) {
            g.setColor(new Color(86,82,87));
            g.fillRect(0, (int) leftcars.get(i).y, 500, 25);
            //g.setColor(new Color(86, 176, 0));
            //g.fillRect(0, (int) leftcars.get(i).y + 25, 500, 25);
            g.setColor(new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random())));
            g.fillRect((int) leftcars.get(i).x, (int) leftcars.get(i).y, (int) leftcars.get(i).w, (int) leftcars.get(i).l);
        }
        for(int i = 0; i < logs.size(); i++) {
                g.setColor(new Color(64, 164, 223));
                g.fillRect(0, (int) logs.get(i).y, 500, 25);
                g.setColor(new Color(192,64,0));
                g.fillRect((int) logs.get(i).x, (int) logs.get(i).y, (int) logs.get(i).w, 25);
                
        }
        for(int i = 0; i < leftlogs.size(); i++) {
            g.setColor(new Color(64, 164, 223));
            g.fillRect(0, (int) leftlogs.get(i).y, 500, 25);
            g.setColor(new Color(192,64,0));
            g.fillRect((int) leftlogs.get(i).x, (int) leftlogs.get(i).y, (int) logs.get(i).w, 25);
            
        }
        for(int i = 0; i < frogs.size(); i++){
            if(froghit == false) {
                g.setColor(Color.green);
            }
            if(froghit) {
                g.setColor(new Color(128, 0 ,0));
            }
            Frog tmp = frogs.get(i);
            g.fillRect(tmp.x, tmp.y, tmp.w, tmp.h); 
        }

    }

    public void keyPressed(KeyEvent k) {

    }

    public void keyReleased(KeyEvent e) {}
    public void Clear() {
        for(int j = 0; j < 3; j++) {
               for(int i = 0; i < cars.size(); i++) {
                   cars.remove(i);
                  
               }
               for(int i = 0; i < leftcars.size(); i++) {
                   leftcars.remove(i);
                  
                }
               for(int i = 0; i < leftlogs.size(); i++) {
                   leftlogs.remove(i);
                   
                }
               for(int i = 0; i < logs.size(); i++) {
                   logs.remove(i);
                
                }
               for(int i = 0; i < trains.size(); i++) {
                   trains.remove(i);
                }
               for(int i = 0; i < carspaces.size(); i++) {
                   carspaces.remove(i);
                }
               for(int i = 0; i < powerups.size(); i++) {
                   powerups.remove(i);
                }
               for(int i = 0; i < trainspaces.size(); i++) {
                   trainspaces.remove(i);
                }
            }
    }
    public void LevelGen() {
         //Randomly generates level
            for(int h = 25; h < 475; h+= 50) {
                rng = rand.nextInt(4);
                if(rng == 0) {
                    cars.add(new RightCar(-100, h));
                    //If You get Past Level 10 it is slightly buggy, objects spawn within one another
                    //This is because of the next lines of code that were supposed to increase difficulty after level 10
                    if(level < 10 || rand.nextInt(level) == 2) {
                        carspaces.add(new CarSpace(0, h+25));
                    }
                    else {
                        if(rand.nextInt(3) == 0) {
                            cars.add(new RightCar(0, h +25));
                        }
                        if(rand.nextInt(3) == 1) {
                            leftcars.add(new LeftCar(0, h + 25));
                        }
                        if(rand.nextInt(3) == 2) {
                            trains.add(new Train(0, h + 25));
                        }
                    }
                    
                }
                if(rng == 1) {
                    leftcars.add(new LeftCar(500,h));
                    //If You get Past Level 10 it is slightly buggy, objects spawn within one another
                    //This is because of the next lines of code that were supposed to increase difficulty after level 10
                      if(level < 10 || rand.nextInt(level) == 2) {
                        carspaces.add(new CarSpace(0, h+25));
                    }
                    else {
                        if(rand.nextInt(3) == 0) {
                            cars.add(new RightCar(0, h +25));
                        }
                        if(rand.nextInt(3) == 1) {
                            leftcars.add(new LeftCar(0, h + 25));
                        }
                        if(rand.nextInt(3) == 2) {
                            trains.add(new Train(0, h + 25));
                        }
                    }
                    
                }
                if(rng == 2) {
                    trains.add(new Train(-300, h));
                   if(rand.nextInt(32) == 1) {
                        powerups.add(new PowerUp(25*(rand.nextInt(20)),h));
                    }
                    //If You get Past Level 10 it is slightly buggy, objects spawn within one another
                    //This is because of the next lines of code that were supposed to increase difficulty after level 10
                     if(level < 10 || rand.nextInt(level) == 2) {
                        trainspaces.add(new TrainSpace(0, h+25));
                    }
                    else {
                        if(rand.nextInt(3) == 0) {
                            cars.add(new RightCar(0, h +25));
                        }
                        if(rand.nextInt(3) == 1) {
                            leftcars.add(new LeftCar(0, h + 25));
                        }
                        if(rand.nextInt(3) == 2) {
                            trains.add(new Train(0, h + 25));
                        }
                    }
                    
                    }
                
                if(rng == 3) {
                    logs.add(new Log(-100,h));
                    leftlogs.add(new LeftLog(500,h+25));
                }
              
            
        }
       
        }
    
    public void keyTyped(KeyEvent e) {
        if(froghit == false) {
              for(int i = 0; i < frogs.size(); i++){
                Frog tmp = frogs.get(i);
                tmp.move(e.getKeyChar());
                repaint();
            }
        }
        if(lives > 1) {
                if(e.getKeyChar() == 'r') {
                 if(froghit) {
                   froghit = false;
                   lives--;
                   frogs.get(0).x = 250;
                   frogs.get(0).y = 475;
                }
            }
        }
       
       if(e.getKeyChar() == 'n' && frogs.get(0).y == 0) {
           frogs.get(0).x = 250;
           frogs.get(0).y = 475;
           Clear();
           LevelGen();
           level++;
        }
       //Dev Cheats
       /* Enable them for easy testing
       if(e.getKeyChar() == 'e') {
           //instant win the level
           frogs.get(0).y = 0;
        }
       if(e.getKeyChar() == 'c') {
           //removes all objects
           Clear();
        }
        if(e.getKeyChar() == 'l') {
            //new level
            Clear();
            LevelGen();
        }
        if(e.getKeyChar() == 'p') {
            lives++;
        }*/
    }
}
