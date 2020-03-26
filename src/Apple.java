
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Apple extends Rectangle{
    
    public Apple(int x, int y){
    setBounds(x+10,y+14,10,10);
    }
    
    public void render (Graphics g){
        g.setColor(Color.magenta);
        g.fillRect(x, y, width, height);
    }
}
