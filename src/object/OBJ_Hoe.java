package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Hoe extends SuperObject {

    GamePanel gp;
    public OBJ_Hoe(GamePanel gp) {
        name = "hoe";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Items/Iron Hoe.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
