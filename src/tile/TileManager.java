package tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();

    }
    public void getTileImage() {
        // GRASS
        setup(0, "000", false);
        setup(1, "001", false);
        setup(2, "002", false);
        setup(3, "003", false);
        setup(4, "004", false);
        setup(5, "005", false);
        setup(6, "006", false);
        setup(7, "007", false);
        setup(8, "008", false);
        setup(9, "009", false);
        setup(10, "010", false);
        setup(11, "011", false);
        setup(12, "012", false);
        setup(13, "013", false);
        setup(14, "014", false);
        setup(15, "015", false);
        setup(16, "016", false);
        setup(17, "017", false);

        // WATER
        setup(18, "018", true);
        setup(19, "019", true);
        setup(20, "020", true);
        setup(21, "021", true);
        setup(22, "022", true);
        setup(23, "023", true);
        setup(24, "024", true);
        setup(25, "025", true);
        setup(26, "026", true);
        setup(27, "027", true);
        setup(28, "028", true);
        setup(29, "029", true);
        setup(30, "030", true);
        setup(31, "031", true);

        // Another Utils
        setup(32, "032", true); // BRICKS
        setup(33, "033", true); // HOUSE
    }
    public void setup(int index, String imagePath, boolean collision){
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read((getClass().getResourceAsStream("/tiles/" +imagePath+ ".png")));
//            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("/maps/worldmap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    if (num == 3){
                        System.out.println("TES");
                    }

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (IOException e){

        }

    }
    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Stop moving the camera at world edge
//            int rightOffset = gp.screenWitdh - gp.player.screenX;
//            screenX = checkIfAtEdgeOfXAxis(worldX, screenX, rightOffset);
//
//            int bottomOffset = gp.screenHeight - gp.player.screenY;
//            screenY = checkIfAtEdgeOfYAxis(worldY, screenY, bottomOffset);

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//            } else if (gp.player.screenX > gp.player.worldX
//                    || gp.player.screenY > gp.player.worldY
//                    || rightOffset > gp.worldWidth - gp.player.worldX
//                    || bottomOffset > gp.worldHeight - gp.player.worldY) {
//                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//            }
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
    private int checkIfAtEdgeOfXAxis(int worldX, int screenX, int rightOffset) {
        if (gp.player.screenX > gp.player.worldX) {
            return worldX;
        }

        if (rightOffset > gp.worldWidth - gp.player.worldX) {
            return gp.screenWitdh - (gp.screenWitdh - worldX);
        }

        return screenX;
    }
    private int checkIfAtEdgeOfYAxis(int worldY, int screenY, int bottomOffset) {
        if (gp.player.screenY > gp.player.worldY) {
            return worldY;
        }

        if (bottomOffset > gp.worldHeight - gp.player.worldY) {
            return gp.screenHeight - (gp.worldHeight - worldY);
        }
        return screenY;
    }
}
