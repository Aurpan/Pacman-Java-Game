import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Score {
    String s;
    public int sc ;
    
//    public int fetch()
//    {
//        int q = 0 ;
//        try {
//             q = readScore();
//        } catch (IOException ex) {
//            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return q;
//    }
    
    public void HighScoreWrite(int p)
    {
        try
        {
            FileWriter fw = new FileWriter("C:\\Users\\HP\\Downloads\\Compressed\\New folder\\experimentalPacman (2)\\score.txt",false);
            fw.write(Integer.toString(p));
            fw.close();
            
        }catch(IOException e)
        {
            System.out.println("ERROR!");
        }
    }
    
    public void readScore() throws IOException
    {
        FileReader fr;
        try {
            fr = new FileReader("C:\\Users\\HP\\Downloads\\Compressed\\New folder\\experimentalPacman (2)\\score.txt");
            BufferedReader br = new BufferedReader(fr);
            
            s = null;
            while((s = br.readLine()) != null)
                    System.out.println(s);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }
         sc = parseInt(s) ; 
         System.out.println(sc);
        // return sc;
        
    
    }
}
