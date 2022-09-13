package Jeremy.Griffin;

import java.awt.*;

public class MapGenerator {
    //initializes a 2D array that will represent the grid of bricks, as well as variables for their size.
    public int map[][];
    public int brickWidth;
    public int brickHeight;



    //receives the number of rows and columns and creates a 2D array with those dimensions.
    //every index in the grid is set to a value of 1.
    //the height and width variables are then populated based on the size of the 2D array.
    public MapGenerator(int row, int col){
        map = new int[row][col];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                map[i][j] = 1;
            }
        }
        brickWidth = 540/col;
        brickHeight = 150/row;
    }
    public void draw(Graphics2D g){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] > 0){
                    g.setColor(Color.PINK);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
}
