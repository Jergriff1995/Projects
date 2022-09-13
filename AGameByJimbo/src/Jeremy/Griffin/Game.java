package Jeremy.Griffin;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;


public class Game extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 60;
    Random random = new Random();

    private Timer timer;
    private int delay = 1;

    private int playerX = 310;
    private int ballStartX = random.ints(100,500)
            .findFirst().getAsInt();
    private int ballStartY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;

    public Game() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        map = new MapGenerator(6, 10);
       addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(false);
       timer = new Timer(delay, this);
       timer.start();



    }

    public void paint(Graphics graphics){
       //background
       graphics.setColor(Color.BLACK);
       graphics.fillRect(1,1,690,592);

       //draw tiles
        map.draw((Graphics2D) graphics);

        //score
        graphics.setColor(Color.white);
        graphics.setFont(new Font("SchoolHouse Cursive B", Font.BOLD, 15));
        graphics.drawString("Score : "+score, 560, 20);

       //boarders
        graphics.setColor(Color.yellow);
        graphics.fillRect(0,0,3, 592);
        graphics.fillRect(0,0,693, 3);
        graphics.fillRect(681,0,3, 592);

        //paddle
        graphics.setColor(Color.cyan);
        graphics.fillRect(playerX, 550, 100, 8);

        //ball
        graphics.setColor(Color.yellow);
        graphics.fillOval(ballStartX, ballStartY, 20, 20);

        if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            graphics.setColor(Color.green);
            graphics.setFont(new Font("SchoolHouse Cursive B", Font.BOLD, 55));
            graphics.drawString("WINNER!", 190, 300);
            graphics.setFont(new Font("SchoolHouse Cursive B", Font.BOLD, 35));
            graphics.drawString("Score : " + score, 265, 380);
            graphics.setFont(new Font("SchoolHouse Cursive B", Font.BOLD, 15));
            graphics.drawString("Press ENTER to play again!", 250, 410);

        }

        if(ballStartY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            graphics.setColor(Color.green);
            graphics.setFont(new Font("SchoolHouse Cursive B", Font.BOLD, 55));
            graphics.drawString("Game Over!", 190, 300);
            graphics.setFont(new Font("SchoolHouse Cursive B", Font.BOLD, 35));
            graphics.drawString("Score : " + score, 265, 380);
            graphics.setFont(new Font("SchoolHouse Cursive B", Font.BOLD, 15));
            graphics.drawString("Press ENTER to play again!", 250, 410);

        }
        graphics.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();
        if(play){
            if(new Rectangle (ballStartX, ballStartY, 20, 20)
                    .intersects(new Rectangle(playerX, 550, 100, 8))){
                try {
                    SoundHandler.RunMusic2("src/Resources/Boop.wav");
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                ballYdir = - ballYdir;
            }

           A: for(int i = 0; i < map.map.length; i++){
                for(int j = 0; j < map.map[0].length; j++){
                   if(map.map[i][j] > 0){
                       int brickX = j * map.brickWidth + 80;
                       int brickY = i * map.brickHeight +50;
                       int brickWidth = map.brickWidth;
                       int brickHeight = map.brickHeight;

                       Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                       Rectangle ballRect = new Rectangle(ballStartX, ballStartY, 20, 20);
                       Rectangle brickRect = rect;

                       if(ballRect.intersects(brickRect)){
                           try {
                               SoundHandler.RunMusic2("src/Resources/Beep.wav");
                           } catch (UnsupportedAudioFileException ex) {
                               ex.printStackTrace();
                           } catch (IOException ex) {
                               ex.printStackTrace();
                           } catch (LineUnavailableException ex) {
                               ex.printStackTrace();
                           }

                           map.setBrickValue(0, i, j);
                           totalBricks --;
                           score += 10;
                           if(ballStartX + 19 <= brickRect.x || ballStartX +1 >= brickRect.x + brickRect.width){
                               ballXdir = -ballXdir;
                           } else {
                               ballYdir = -ballYdir;
                           }
                           break A;
                       }

                   }
                }
            }

            ballStartX += ballXdir;
            ballStartY += ballYdir;
            if (ballStartX < 0 ){
                ballXdir = -ballXdir;
            }
            if (ballStartY < 0 ){
                ballYdir = -ballYdir;
            }
            if (ballStartX > 660 ){
                ballXdir = -ballXdir;
            }
        }

        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
           if(playerX >= 572 ){
               playerX = 572;
           } else{
               moveRight();
           }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <= 10 ){
                playerX = 10;
            } else{
               moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
               play = true;
               ballStartX = random.ints(100,500)
                        .findFirst().getAsInt();
               ballStartY = 350;
               ballXdir = -1;
               ballYdir = -2;
               playerX = 310;
               score = 0;
               map = new MapGenerator(6, 10);
               repaint();
            }
        }
    }
    public void moveRight(){
        play = true;
        playerX += 20;
    }
    public void moveLeft(){
        play = true;
        playerX -= 20;
    }


}
