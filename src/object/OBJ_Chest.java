package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject {

    GamePanel gp;
    public OBJ_Chest(GamePanel gp) {
        name = "chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/Chest.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}