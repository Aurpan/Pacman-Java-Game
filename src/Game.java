import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener {

    private boolean isRunning = false ;
    public static final int width=1000, height = 635;
    public static final String Tile = "pac-man";
    public static int scr = 0 , life = 3, stage =3;
    public static int playerX = 840/2 ,playerY = 630/2;

    //public int Hscore = score.readScore();
    
    private Thread thread;
    
    public static Player player;
    public static Game game;
    public static JFrame frame;

    public static  Bitmap level;
    Score score = new Score();
    
    public Game(){
        Dimension dimension = new Dimension(Game.width,Game.height);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        
        addKeyListener(this);
        if(Game.stage == 2) {
                  Game.playerX = 45;
                 Game.playerY = 305;}
        else if(Game.stage == 3){
              
                  Game.playerX = 400;
                  Game.playerY = 290;
              }
        
        player = new Player(playerX,playerY);
        level = new Bitmap ();
        
    }
    
    public synchronized void start(){
        if(isRunning) return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if(!isRunning) return;
        isRunning = false;
        
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tick(){
       player.tick();
       level.tick();
       
    }
    private void render (){
        BufferStrategy bs = getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.width, Game.height);
        
        player.render(g);
        level.render(g);
        g.dispose();
        bs.show();
    
    }
    
    @Override
    public void run() {
        requestFocus();
        int fps = 0;
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double targetTick = 60.0;
        double delta =0;
        double intervalBetweenTicks = 1000000000/targetTick;
        
        
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime )/intervalBetweenTicks;
            lastTime = now;
            
            while(delta >= 1){
                tick();
                render();
                fps++;
                delta--;
            }
            
            
            if(System.currentTimeMillis() - timer >= 1000){
                fps = 0;
                timer += 1000;
            }
        }
        
        stop();
    }
    
    public void start_game(){
    
         game = new Game();
         frame = new JFrame();
        frame.setTitle(Game.Tile);
        frame.add(game);
        frame.setResizable(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
        
        game.start();
    
    }
    
     public void updateScore(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.drawString("" + score, 870, 60);
        g.drawString( "score", 870, 30);
    }

  @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = true;
       if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = true;
       if(e.getKeyCode() == KeyEvent.VK_UP) player.up = true;
       if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = true;
    }

    
    @Override
    public void keyTyped(KeyEvent e) {
       
    }

   
    @Override
    public void keyReleased(KeyEvent e) {
       if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
       if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
       if(e.getKeyCode() == KeyEvent.VK_UP) player.up = false;
       if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = false;
    }
}
