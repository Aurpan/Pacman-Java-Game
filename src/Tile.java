
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Tile extends Rectangle {
    public Tile(int x,int y){
        setBounds(x,y,42,42);
    }
    
    public void render (Graphics g){
    
        if(Game.stage==1)
        {
            g.setColor(new Color(33,0,127));
            g.fillRect(x, y, width, height);
        }
        else if(Game.stage==2)
        {
            g.setColor(new Color(3,50,27));
            g.fillRect(x, y, width, height);
        }
        else if(Game.stage==3)
        {
            g.setColor(new Color(0,80,100));
            g.fillRect(x, y, width, height);
        }
        else if(Game.stage==4)
        {
            g.setColor(new Color(0,150,127));
            g.fillRect(x, y, width, height);
        }
        else if(Game.stage==5)
        {
            g.setColor(new Color(100,15,50));
            g.fillRect(x, y, width, height);
        }
    }
}
