import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class KeyStrokeHandler {
    KeyType type;
    Terminal terminal;

    public KeyStrokeHandler(Terminal terminal) throws Exception{
        this.terminal = terminal;
        terminal.flush();
        KeyStroke keyStroke = null;
        Character c = ' ';
        boolean runGame = true;
        //GameBody gameBody = new GameBody(terminal);

        while (runGame) {
            do {
                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();

                if (keyStroke != null) {
                    type = keyStroke.getKeyType();
                    c = keyStroke.getCharacter();
                    //System.out.println(c + " : "+type);
                   // gameBody.move(type);
                }
                if(c == Character.valueOf('q')){
                    runGame = false;
                    terminal.close();
                }

            } while (keyStroke == null);
        }
    }
}

