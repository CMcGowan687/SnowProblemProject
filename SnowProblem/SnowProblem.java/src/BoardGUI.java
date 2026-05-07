import javax.swing.*;
import java.awt.*;

public class BoardGUI extends JFrame {

    private JButton[][] tiles;

    private int selectedRow = -1;    // start of the implemenetation of movement
    private int selectedCol = -1;

    private ImageIcon TreeIcon;
    private ImageIcon SmallSnowballIcon;
    private ImageIcon LargeSnowballIcon;
    private ImageIcon SnowmanHeadIcon;
    
    public BoardGUI() {

        //create the JFrame e.g. title and the close operation
        setTitle("Snow Problem");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TreeIcon = new ImageIcon("Tree.png");
        SmallSnowballIcon = new ImageIcon ("SmallSnowball.png");
        LargeSnowballIcon = new ImageIcon ("LargeSnowball.png");
        SnowmanHeadIcon = new ImageIcon ("SnowmanHead.png");


        JPanel boardPanel = new JPanel();

        boardPanel.setLayout(new GridLayout(4,5));

        tiles = new JButton[4][5];

        for (int r = 0; r < 4; r++) {

            for (int c = 0; c < 5; c++) {

                tiles[r][c] = new JButton("."); // using . to indicate where each button is.
                
                int row = r;
                int col = c;

                tiles[r][c].addActionListener(e -> Click(row, col));
                
                boardPanel.add(tiles[r][c]);
            } 
        }
        
        //adding the game pieces for the first level

        placeSmallSnowball(0,1);
        
        placeLargeSnowball(3,4);

        placeSnowmanHead(3,0); //all objects are set for the first level
        
        add(boardPanel);

        setVisible(true);
    
    }

    //names are just place holders until images are added.

    public void placeSmallSnowball(int row, int col) {

        tiles[row][col].setIcon(SmallSnowballIcon);
    }

    public void placeLargeSnowball(int row, int col) {

        tiles[row][col].setIcon(LargeSnowballIcon);
    }

    public void placeSnowmanHead(int row, int col) {

        tiles[row][col].setIcon(SnowmanHeadIcon);
    }

    //adding the event for the action listener

    public void Click(int row, int col) {

        if(selectedRow == -1) {

            String piece = tiles[row][col].getText();
            
            if (piece.equals("Small") || piece.equals("Large")){ //only the small and large snowballs can be moved

                selectedRow = row;
                selectedCol = col;

                tiles[row][col].setBackground(Color.YELLOW);

                System.out.println("Piece Selected");
            }
        }

        //To move the piece with a second click with the mouse.
        else {

        // checking if it is horizontal or verticle. if not it wont work

            String piece = tiles[selectedRow][selectedCol].getText();

            int newRow = selectedRow;
            int newCol = selectedCol;

            if (col == selectedCol){

                if (row > selectedRow) {

                    while (!Blocked(newRow + 1, newCol)){

                        newRow++;
                    }
                }

                else if (row <selectedRow) {

                    while (!Blocked(newRow - 1, newCol)){

                        newRow--;
                    }
                }
            }

            else if (row == selectedRow) {

                if (col > selectedCol){

                    while (!Blocked(newRow, newCol + 1)) {

                        newCol++;
                    }
                }

                else if (col < selectedCol){

                    while(!Blocked(newRow, newCol -1));

                    newCol--;
                }
            }
            if (newRow != selectedRow || newCol != selectedCol) {

            tiles[selectedRow][selectedCol].setText(".");
            tiles[selectedRow][selectedCol].setBackground(null); // now the colour will go and the piece of the board will go back to normal.

            tiles[row][col].setText(piece);
            }
         
    
        
        else {

            System.out.println("Invalid Move");
            
            tiles[selectedRow][selectedCol].setBackground(null);

        }

            selectedRow = -1;
            selectedCol = -1;
        
            System.out.println("The Piece has been moved"); //tell player the piece has moved correctly.
    }
}

    public boolean Blocked(int row, int col) {

        if (row < 0 || row >= tiles.length ||
            col < 0 || col >= tiles[row].length) {

                return true;
            }

            return !tiles[row][col].getText().equals(".");
    }
}
