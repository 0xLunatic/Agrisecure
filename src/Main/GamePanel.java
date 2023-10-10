package Main;

import entity.Player;
import object.SuperObject;
import tile.Tile;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWitdh = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    public Player player;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    Thread gameThread;
    public AssetSetter aSetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[10];
    Sound sound = new Sound();
    Sound music = new Sound();
    public UI ui = new UI(this);

    /// FPS
    int fps = 60;

    //Player Default Position

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 3;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWitdh, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        startGameThread();
        player = new Player(this, keyH);
        player.getPlayerImage();

    }
    public void setupGame(){
        aSetter.setObject();

        playMusic(0);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        double drawInterval = 1000000000.0 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        int timer = 0;
        int drawCount = 0;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000){
                System.out.println("FPS : " + drawCount);
                drawCount = 0;
                timer = 0;
            }

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }


        }
    }
    public void update(){

        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

         // DEBUG
//        long drawStart = 0;
//        if (keyH.checkDrawTime == true){
//            drawStart = System.nanoTime();
//        }


        Graphics2D g2 = (Graphics2D) g;
        // TILE
        tileM.draw(g2);
        // OBJECT
        for (int i = 0; i < obj.length; i++){
            if (obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        // PLAYER
        player.draw(g2);

        // UI
        ui.draw(g2);


//        if (keyH.checkDrawTime == true){
//            long drawEnd = System.nanoTime();
//            long passed = drawEnd - drawStart;
//            g2.setColor(Color.white);
//            g2.drawString("Draw Time : " + passed, 10, 400);
//            System.out.println("Draw Time : " + passed);
//        }


    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSoundEffect(int i){
        sound.setFile(i);
        sound.play();
    }

}
