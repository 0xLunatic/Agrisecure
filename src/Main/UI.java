package Main;

import object.OBJ_Hoe;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40;
    BufferedImage hoeImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public UI(GamePanel gp){
        this.gp=gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Hoe hoe = new OBJ_Hoe(gp);
        hoeImage = hoe.image;
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(hoeImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("Hoe : " + gp.player.hasHoe, 74, 50);

        if (messageOn == true){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

            messageCounter++;

            if (messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
