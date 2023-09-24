package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x,y;
    public int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, right1, right2, idleDown1, idleDown2, idleUp1, idleUp2, idleLeft1, idleLeft2, idleRight1, idleRight2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int idleState;
}
