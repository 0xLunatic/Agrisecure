package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BunchSand extends SuperObject {
    GamePanel gp;
    public OBJ_BunchSand(GamePanel gp) {
        name = "bunchsand";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/Bunch Sand.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
