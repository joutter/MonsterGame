import java.util.HashMap;
import java.util.Random;

public class Enemy {
    int xPos;
    int yPos;
    int oldX;
    int oldY;
    public final char enemyChar = '\u2588';
    int terminalWidth;
    int terminalHeight;
    HashMap<String,Integer> map = new HashMap<>();

    public Enemy(int terminalWidth, int terminalHeight){
        this.terminalWidth = terminalWidth;
        this.terminalHeight = terminalHeight;

        Random r = new Random();
        xPos = r.nextInt(terminalWidth);
        yPos = r.nextInt(terminalHeight);
    }

        public void move(int playerX, int playerY){
            String mapString = xPos +","+yPos;
            map.put(mapString,1);

            oldX = xPos;
            oldY = yPos;
            int velocity = 1;
            if(playerX<xPos){
                xPos -= velocity;
            }
            if(playerX>xPos){
                xPos += velocity;
            }
            if(playerY<yPos){
                yPos -= velocity;
            }
            if(playerY>yPos){
                yPos += velocity;
            }
        }

        public HashMap<String,Integer> getMap(){
            return map;
        }
}
