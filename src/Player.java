//import static Game.life;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Player extends Rectangle {
    
    

    public boolean right, left, up, down;
    private int speed = 4;
    int life = 3 ;
    GameOver over = new GameOver();
    Score score = new Score();
    int Hscore  ;
    Scanner sc ;
    
    public Player (int x, int y){
        
        setBounds(x,y,35,35);
                try {
             sc = new Scanner(new File("C:\\Users\\HP\\Downloads\\Compressed\\New folder\\experimentalPacman (2)\\score.txt"));
             Hscore = sc.nextInt();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    public void tick (){
        
        if(right && canMove(x+speed,y)) x+= speed;
        if(left && canMove(x-speed,y)) x-= speed;
        if(up && canMove(x,y-speed)) y-= speed;
        if(down && canMove(x,y+speed)) y+= speed;
        
        Bitmap level = Game.level;
        for(int i =0 ; i<level.apples.size();i++){
            
            if(this.intersects(level.apples.get(i))){
                
                level.apples.remove(i);
                Game.scr++;
                if(Game.scr > Hscore)
                    Hscore = Game.scr ; 
                break;
            }
            
        }
    
        if(level.apples.size() == 0) 
          {  
              Game.stage++;
              if(Game.stage == 2) {
                  Game.playerX = 45;
                 Game.playerY = 305;}
              else if(Game.stage ==3){
              
                  Game.playerX = 400;
                  Game.playerY = 290;
              }
              
              Game.player = new Player(Game.playerX,Game.playerY);
              Game.level = new Bitmap();
              return;
              
        }
        
        for(int i=0; i< Game.level.enemies.size();i++){
            Enemy en = Game.level.enemies.get(i);
            if(en.intersects(this)){
                Game.life-- ;
                x = Game.playerX;
                y=Game.playerY;
                
                if(Game.life == 0){
                    Game.life = 3;
                    Game.scr = 0 ;
                    score.HighScoreWrite(Hscore);
                    over.setVisible(true);
                    Game.stage =1;
                    Game.playerX = 840/2 ;
                    Game.playerY = 630/2;
                    Game.frame.setVisible(false);
                    Game.frame = null;
                    Game.game.stop();
                    //System.exit(OUT_TOP);
                    }
            }
        }
    }
    
    private boolean canMove(int nextx, int nexty){
    
        Rectangle bound = new Rectangle (nextx,nexty,width,height);
        Bitmap level = Game.level;
        
        for(int xx=0; xx<level.tiles.length;xx++){
            for(int yy=0; yy<level.tiles[0].length;yy++){
               if(level.tiles[xx][yy] != null){
                   if(bound.intersects(level.tiles[xx][yy])){
                       return false;
                   }
               }
            }
        
        }
        
    return true;
    }
    
    public void render (Graphics g){
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, width, height);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(Game.scr), 870, 60);
        g.setColor(Color.RED);
        g.drawString( "score", 870, 40);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(Game.life), 870, 120);
        g.setColor(Color.RED);
        g.drawString( "LIFE", 870, 100);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(Hscore), 870, 180);
        g.setColor(Color.RED);
        g.drawString( "High Score", 870, 160);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(Game.stage), 870, 240);
        g.setColor(Color.RED);
        g.drawString( "Stage", 870, 220);
        
    }
}
