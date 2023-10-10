package Main;

import object.OBJ_BunchSand;
import object.OBJ_Chest;
import object.OBJ_Hoe;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new OBJ_Hoe(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[1] = new OBJ_BunchSand(gp);
        gp.obj[1].worldX = 9 * gp.tileSize;
        gp.obj[1].worldY = 9 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest(gp);
        gp.obj[2].worldX = 9 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;
    }
}
