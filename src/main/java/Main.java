import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.util.HashMap;

public class Main {
    static Terminal terminal;
    static Enemy enemy;
    static Player player;

    public static void main(String[] args) throws Exception {
        TerminalSize ts = new TerminalSize(60,40);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(ts);
        terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        int terminalWidth = ts.getColumns();
        int terminalHeight = ts.getRows();

        player = new Player(terminalWidth,terminalHeight);
        enemy = new Enemy(terminalWidth,terminalHeight);
        terminal.setForegroundColor(TextColor.ANSI.GREEN);
        terminal.setCursorPosition(player.xPos,player.yPos);
        terminal.putCharacter(player.playerChar);
        terminal.setForegroundColor(TextColor.ANSI.BLUE);
        terminal.setCursorPosition(enemy.xPos,enemy.yPos);
        terminal.putCharacter(enemy.enemyChar);
        terminal.flush();

        KeyStroke keyStroke = null;
        Character c = ' ';
        boolean runGame = true;
        KeyType type;

        while (runGame) {
            moveEnemy();
            terminal.setForegroundColor(TextColor.ANSI.GREEN);
            HashMap<String, Integer> tmp = enemy.getMap();
            String s = player.xPos+","+ player.yPos;
            if(tmp.containsKey(s) || ((player.xPos == enemy.xPos) &&(player.yPos == enemy.yPos))){
                System.out.println("Smäller");
                terminal.close();
                runGame = false;
            }
            do {

                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();

                if (keyStroke != null) {
                    type = keyStroke.getKeyType();
                    c = keyStroke.getCharacter();

                    switch (type){
                        case ArrowUp -> player.move(0,-1);
                        case ArrowDown -> player.move(0,1);
                        case ArrowLeft -> player.move(-1,0);
                        case ArrowRight -> player.move(1,0);
                    }

                }
                if(c == Character.valueOf('q')){
                    runGame = false;
                    terminal.close();
                }

                terminal.setCursorPosition(player.oldX,player.oldY);
                terminal.putCharacter(' ');
                terminal.setCursorPosition(player.xPos,player.yPos);
                terminal.putCharacter(player.playerChar);

                terminal.flush();

            } while (keyStroke == null);
        }
    }
    public static void moveEnemy() throws  Exception{
        terminal.setForegroundColor(TextColor.ANSI.BLUE);
            enemy.move(player.xPos, player.yPos);
            terminal.setCursorPosition(enemy.xPos, enemy.yPos);
            terminal.putCharacter(enemy.enemyChar);
            terminal.flush();
    }
}
