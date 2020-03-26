
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Bitmap {

    public int width;
    public int height;
    
    public Tile[][] tiles;
    public ArrayList<Apple> apples;
    public ArrayList<Enemy> enemies; 
    
    public Bitmap (){
        apples = new ArrayList<>();
        enemies = new ArrayList<>();
        if(Game.stage == 1) enemies.add(new Enemy(45,530));
        String stage = "map.png";
        try {
            if(Game.stage == 2){
            enemies.add(new Enemy(45,45));
            enemies.add(new Enemy(45,545));
            enemies.add(new Enemy(445,45));
            enemies.add(new Enemy(740,145));
            stage = "map2.png";
        
            }
            
            else if(Game.stage ==3){
            
            enemies.add(new Enemy(45,45));
            enemies.add(new Enemy(45,545));
            enemies.add(new Enemy(445,45));
            enemies.add(new Enemy(760,145));
            enemies.add(new Enemy(400,520));
            stage = "map3.png";
            
            }
            
            else if (Game.stage == 4){
             stage = "map4.png";
            
            }
            else if (Game.stage == 5){
             stage = "map5.png";
            
            }
            File file = new File(stage);
            FileInputStream fis = new FileInputStream(file);
            BufferedImage map = ImageIO.read(fis);
            
            this.width = map.getWidth();
            this.height = map.getHeight();
            int[] pixels = new int[width*height];
            tiles = new Tile[width][height];
            map.getRGB(0, 0, width, height, pixels,0, width);
            
             for(int xx=0; xx <width;xx++){
                for(int yy=0; yy<height; yy++){
                    int val = pixels[xx + (yy*width)];
                
                    if(val == 0xFF000000) {
                        tiles[xx][yy] = new Tile(xx*42,yy*42);
                
                    }
                    else if(val == 0xFF0000FF){
                        Game.playerX = xx*42;
                        Game.playerY = yy*42;
               }else if(val == 0xFFFF0000){
               //enemy
               enemies.add(new Enemy(xx*42,yy*42));
               }else{
                   apples.add(new Apple(xx*42,yy*42));
               }
            }
        }
        } catch (IOException ex) {
            //Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
       
    }
    
    public void tick(){
        for(int i =0; i<enemies.size();i++){
            enemies.get(i).tick();
        }
    }
    
    public void render (Graphics g){
        for(int x =0; x<width; x++){
            for(int y=0; y<height; y++){
                if(tiles[x][y] != null)
                tiles[x][y].render(g);
            }
        }
        
        for(int i=0;i<apples.size();i++){
              apples.get(i).render(g);
        }
        
        for(int i =0; i<enemies.size();i++){
            enemies.get(i).render(g);
        }
    }

    
    
}
