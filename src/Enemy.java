
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends Rectangle {
    
    private int random =0, smart =1,find_path=2;
    private int state = random;
    private int lastDir = -1;
    private int time = 0;
    private int targetTime = 60*4;
    private int spd = 1;
    private int right =0, left = 1, up =2, down =3 ;
    private int dir = -1;
    public Random randomGen;
    
    public Enemy(int x, int y){
        randomGen = new Random();
        setBounds(x,y,35,35);
        dir = randomGen.nextInt(4);
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
    public void tick(){
        
        if(state == random){
            
            if(dir == right){
                if(canMove(x+spd,y)){
                    x+=spd;
                }else{
                    dir = randomGen.nextInt(4);
                }
                
            }else if(dir == left){
                if(canMove(x-spd,y)){
                    x-=spd;
                }else{
                    dir = randomGen.nextInt(4);
                }
                
            }else if(dir == up){
                if(canMove(x,y-spd)){
                    y-=spd;
                }else{
                    dir = randomGen.nextInt(4);
                }
                
            }else if(dir == down){
                if(canMove(x,y+spd)){
                    y+=spd;
                }else{
                    dir = randomGen.nextInt(4);
                }
            }
        time++;
        if(time == targetTime) {
            
            state = smart;
            time = 0;
        }
        }
        else if(state == smart){
         boolean move = false;
        
         if(x < Game.player.x){
             if(canMove(x+spd,y)){
                 x+=spd;
                 move= true;
                 lastDir = right;
             }
         }
         if(x > Game.player.x){
             if(canMove(x-spd,y)){
                 x-=spd;
                 move= true;
                 lastDir = left;
             }
         }
         if(y < Game.player.y){
             if(canMove(x,y+spd)){
                 y+=spd;
                 move= true;
                 lastDir = down;
             }
         }   
         if(y > Game.player.y){
             if(canMove(x,y-spd)){
                 y-=spd;
                 move= true;
                 lastDir = up;
             }
         }
         
         if(x== Game.player.x && y == Game.player.y) move = true;
         
         
         if(!move){
             if(lastDir== right){
                 if(y < Game.player.y){
                     if(canMove(x,y+spd)){
                         y+=spd;
                     }
                 }else{
                     if(canMove(x,y-spd)){
                         y-=spd;
                     }
                 }
                 
                 if(canMove(x+spd,y)){
                     x+=spd;
                 }
             }else if(lastDir == left){
                 if(y < Game.player.y){
                     if(canMove(x,y+spd)){
                         y+=spd;
                     }
                 }else{
                     if(canMove(x,y-spd)){
                         y-=spd;
                     }
                 }
                 
                 if(canMove(x-spd,y)){
                     x-=spd;
                 }
                 
             }else if(lastDir == up){
                 if(x < Game.player.x){
                     if(canMove(x+spd,y)){
                         x+=spd;
                     }
                 }else{
                     if(canMove(x-spd,y)){
                         x-=spd;
                     }
                 }
                 
                 if(canMove(x,y-spd)){
                     y-=spd;
                 }
             }else if(lastDir == down){
                 if(x < Game.player.x){
                     if(canMove(x+spd,y)){
                         x+=spd;
                     }
                 }else{
                     if(canMove(x-spd,y)){
                         x-=spd;
                     }
                 }
                 
                 if(canMove(x,y+spd)){
                     y+=spd;
                 }
             
             }
         
         }
         
            time++;
        if(time == targetTime){ state = random;
        time =0;
        }
            
        
        
        }
    
        
        
    }
    
        
    public void render(Graphics g){
       if(Game.stage==1)
       {
            g.setColor(Color.red);
            g.fillOval(x,y,32,32 );
       }
       else if(Game.stage==2)
       {
            g.setColor(Color.cyan);
            g.fillOval(x,y,32,32 );
       }
       else if(Game.stage==3)
       {
            g.setColor(Color.lightGray);
            g.fillOval(x,y,32,32 );
       }
       else if(Game.stage==4)
       {
            g.setColor(Color.pink);
            g.fillOval(x,y,32,32 );
       }
       else if(Game.stage==5)
       {
            g.setColor(Color.green);
            g.fillOval(x,y,32,32 );
       }
    }

    
}
