package inputs;

import entities.Player;
import gamestates.GameState;
import main.Game;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
        }
    }
}
