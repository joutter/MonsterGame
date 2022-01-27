public class Player {
    int xPos;
    int yPos;
    int oldX;
    int oldY;

    public final char playerChar = '\u2588';

    public Player(int terminalWidth, int terminalHeight){
        xPos =terminalWidth/2;
        yPos = terminalHeight/2;
    }
    public void move(int x, int y){
        int velocity = 1;
        oldX = xPos;
        oldY = yPos;
        xPos += x*velocity;
        yPos += y*velocity;
    }

}
