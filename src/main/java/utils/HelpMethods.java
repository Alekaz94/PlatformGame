package utils;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData) {
        if(!IsSolid(x, y, levelData))
            if(!IsSolid(x + width, y + height, levelData))
                if(!IsSolid(x + width, y, levelData))
                    if(!IsSolid(x, y + height, levelData))
                        return true;
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] levelData) {
        if(x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true;


        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];

        if(value >= 48 || value < 0 || value != 11)
            return true;
        return false;
    }

    public static float GetEntityXPositionNextToWall(Rectangle2D.Float hitBox, float xSpeed) {
        int currentTile = (int) (hitBox.x / Game.TILES_SIZE);
        if(xSpeed > 0) {
            // Right
            int tileXPosition = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitBox.width);
            return tileXPosition + xOffset - 1;
        } else {
            // Left
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float GetEntityYPositionUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, float airSpeed) {
        int currentTile = (int) (hitBox.y / Game.TILES_SIZE);
        if(airSpeed > 0) {
            // Falling / Touching floor
            int tileYPosition = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - hitBox.height);
            return tileYPosition + yOffset - 1;
        } else {
            // Jumping
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitBox, int[][] levelData) {
        // Check the pixel below bottom left and bottom right corner
        if(!IsSolid(hitBox.x, hitBox.y + hitBox.height + 1, levelData))
            if(!IsSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, levelData))
                return false;
        return true;
    }
}
