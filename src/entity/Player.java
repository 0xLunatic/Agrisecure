package entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    private boolean idle;

    public final int screenX;
    public final int screenY;
    public int hasHoe = 0;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWitdh/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 25;
        speed = 3;
        direction = "down";
    }
    public void getPlayerImage() {

        down1 = setup("c_down_1");
        down2 = setup("c_down_2");
        down3 = setup("c_down_3");

        idleDown1 = setup("c_down_3");
        idleDown2 = setup("c_down_3");

        up1 = setup("c_up_1");
        up2 = setup("c_up_2");
        up3 = setup("c_up_3");

        idleUp1 = setup("c_up_3");
        idleUp2 = setup("c_up_3");

        left1 = setup("c_left_2");
        left2 = setup("c_left_3");

        idleLeft1 = setup("c_left_3");
        idleLeft2 = setup("c_left_3");

        right1 = setup("c_right_2");
        right2 = setup("c_right_3");

        idleRight1 = setup("c_right_3");
        idleRight2 = setup("c_right_3");
    }
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" +imageName+ ".png"));
//            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void update(){
        if (keyH.upPressed == true ||
        keyH.downPressed == true ||
        keyH.leftPressed == true ||
        keyH.rightPressed == true){
            idle = false;
            if (keyH.upPressed == true){
                direction = "up";

            }
            else if (keyH.downPressed == true){
                direction = "down";

            }
            else if (keyH.leftPressed == true){
                direction = "left";

            }
            else if (keyH.rightPressed == true){
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObjects(objIndex);
            // COLLISION FALSE - PLAYER MOVE

            if (collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
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
    public void pickUpObjects(int i){
        if (i != 999){
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "hoe":
                    gp.playSoundEffect(1);
                    hasHoe++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got the hoe!");
                    break;
                case "bunchsand":
                    if (hasHoe > 0) {
                        gp.obj[i] = null;
                        hasHoe--;
                    }
                    break;
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
