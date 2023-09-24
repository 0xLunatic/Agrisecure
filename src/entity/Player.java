package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    private BufferedImage spriteSheet = null;
    private boolean idle;
    private int tickCount = 0;
    private boolean swap;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "1";
    }
    public void getPlayerImage(){
        try{
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Down_3.png"));

            idleDown1 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Idle_Down_1.png"));
            idleDown2 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Idle_Down_2.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Up_3.png"));

            idleUp1 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Idle_Up_1.png"));
            idleUp2 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Idle_Up_2.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Left_2.png"));

            idleLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Idle_Left_1.png"));
            idleLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Idle_Left_2.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Right_2.png"));

            idleRight1 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Idle_Right_1.png"));
            idleRight2 = ImageIO.read(getClass().getResourceAsStream("/player/Alex_Idle_Right_2.png"));
        } catch (IOException e){
            e.printStackTrace();

        }
    }

    public void update(){
        if (keyH.upPressed == true ||
        keyH.downPressed == true ||
        keyH.leftPressed == true ||
        keyH.rightPressed == true){
            idle = false;
            if (keyH.upPressed == true){
                direction = "up";
                y -= speed;
            }
            else if (keyH.downPressed == true){
                direction = "down";
                y += speed;
            }
            else if (keyH.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            else if (keyH.rightPressed == true){
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter > 10){
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }else {
            idle = true;
            spriteCounter++;
            if (spriteCounter > 30) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = right1;
        if (idle && direction.equals("down")) {
            if (spriteNum == 1) {
                image = idleDown1;
            } else if (spriteNum == 2) {
                image = idleDown2;
            }
        }else if (idle && direction.equals("up")){
            if (spriteNum == 1) {
                image = idleUp1;
            } else if (spriteNum == 2) {
                image = idleUp2;
            }
        }else if (idle && direction.equals("left")){
            if (spriteNum == 1) {
                image = idleLeft1;
            } else if (spriteNum == 2) {
                image = idleLeft2;
            }
        }else if (idle && direction.equals("right")){
            if (spriteNum == 1) {
                image = idleRight1;
            } else if (spriteNum == 2) {
                image = idleRight2;
            }
        } else {
            switch (direction) {
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (keyH.downPressed == false) {
                        image = down3;
                    }
                    break;
                case "up":
                    if (spriteNum == 1){
                        image = up1;
                    }
                    if (spriteNum == 2){
                        image = up2;
                    }
                    if (keyH.upPressed == false) {
                        image = up3;
                    }
                    break;
                case "right":
                    if (spriteNum == 1){
                        image = right1;
                    }
                    if (spriteNum == 2){
                        image = right2;
                    }
                    if (keyH.rightPressed == false) {
                        image = right1;
                    }
                    break;
                case "left":
                    if (spriteNum == 1){
                        image = left1;
                    }
                    if (spriteNum == 2){
                        image = left2;
                    }
                    if (keyH.leftPressed == false) {
                        image = left1;
                    }
                    break;
            }
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
