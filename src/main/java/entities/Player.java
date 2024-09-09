package entities;

import main.Game;
import utils.LoadSave;
import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.CanMoveHere;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int animationTick;
    private int animationIndex;
    private int animationSpeed = 15;
    private int playerAction = IDLE;
    private boolean moving = false;
    private boolean attacking = false;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private float playerSpeed = 2.0f;
    private int[][] levelData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initializeHitBox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex],  (int) (hitBox.x - xDrawOffset), (int)(hitBox.y - yDrawOffset), (int)width, (int)height, null);
        drawHitBox(g);
    }

    private void updateAnimationTick() {
        animationTick++;
        if(animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    public void setAnimation() {
        int startAnimation = playerAction;

        if(moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }

        if(attacking) {
            playerAction = ATTACK_1;
        }

        if(startAnimation != playerAction) {
            resetAnimationTick();
        }
    }

    public void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    public void updatePosition() {
        moving = false;
        if(!left && !right && !up && !down)
            return;

        float xSpeed = 0;
        float ySpeed = 0;

        if(left && !right)
            xSpeed = -playerSpeed;
        else if(right && !left)
            xSpeed = playerSpeed;


        if(up && !down)
            ySpeed = -playerSpeed;
        else if(down && !up)
            ySpeed = playerSpeed;

//        if(CanMoveHere(x + xSpeed, y + ySpeed, width, height, levelData)) {
//            this.x += xSpeed;
//            this.y += ySpeed;
//            moving = true;
//        }
        if(CanMoveHere(hitBox.x + xSpeed, hitBox.y + ySpeed, hitBox.width, hitBox.height, levelData)) {
            hitBox.x += xSpeed;
            hitBox.y += ySpeed;
            moving = true;
        }

    }

    private void loadAnimations() {
        BufferedImage image = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[9][6];

        for(int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = image.getSubimage(i*64, j*40, 64, 40);
            }
        }
    }

    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;
    }

    public void resetDirectionBoolean() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public boolean getLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean getRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean getUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean getDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}
