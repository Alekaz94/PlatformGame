package utils;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }


    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMPING = 2;
        public static final int FALLING = 3;
        public static final int FALLING_GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;

        public static int GetSpriteAmount(int playerAction) {
            switch (playerAction) {
                case RUNNING:
                    return 6;
                    case IDLE:
                        return 5;
                        case JUMPING:
                            return 3;
                case HIT:
                    return 4;
                case ATTACK_1:
                    return 3;
                case ATTACK_JUMP_1:
                    return 3;
                case ATTACK_JUMP_2:
                    return 3;
                case FALLING_GROUND:
                    return 2;
                case FALLING:
                    return 1;
                default:
                    return 1;
            }
        }
    }
}
