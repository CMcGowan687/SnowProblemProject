import javax.swing.*;
import java.awt.*;

public class BoardGUI extends JFrame {

    private JButton[][] tiles;

    private int selectedRow = -1;    // start of the implemenetation of movement
    private int selectedCol = -1;

    private ImageIcon SmallSnowballIcon;
    private ImageIcon LargeSnowballIcon;
    private ImageIcon SnowmanHeadIcon;
    private ImageIcon StackIcon;
    private ImageIcon CompleteIcon;
    
    public BoardGUI() {

        //create the JFrame e.g. title and the close operation
        setTitle("Snow Problem");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        SmallSnowballIcon = new ImageIcon ("SmallSnowball.png");
        LargeSnowballIcon = new ImageIcon ("LargeSnowball.png"); // all game icons are now added
        SnowmanHeadIcon = new ImageIcon ("SnowmanHead.png");
        StackIcon = new ImageIcon("Stack.png");
        CompleteIcon = new ImageIcon("CompleteStack.png");


        JPanel boardPanel = new JPanel();

        boardPanel.setLayout(new GridLayout(4,5));

        tiles = new JButton[4][5];

        for (int r = 0; r < 4; r++) {

            for (int c = 0; c < 5; c++) {

                tiles[r][c] = new JButton(""); // using . to indicate where each button is.
                
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

    //adding the event for the action listener

    public void Click(int row, int col) {

        if(selectedRow == -1) {

            String piece = tiles[row][col].getText();
            
            if (piece.equals("Small") || piece.equals("Large") || piece.equals("Head")){ //only the small and large snowballs can be moved

                selectedRow = row;
                selectedCol = col;

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

                    while(!Blocked(newRow, newCol -1)) {

                    newCol--;
                    }
                }
            }
        
            int stackRow = newRow;
            int stackCol = newCol;

                if (row > selectedRow) {

                    stackRow++;
                }

                else if (row < selectedRow){

                stackRow--;

                }

                else if (col > selectedCol) {

                    stackCol++;

                }

                else if (col < selectedCol) {

                    stackCol--;
                }



            if (newRow != selectedRow || newCol != selectedCol) {

                String destinationPiece = "";

                if(newRow != selectedRow || newCol != selectedCol) {

                    destinationPiece = tiles[stackRow][stackCol].getText();
                }


                if(piece.equals("Small") && destinationPiece.equals("Large")) { // stacking the small and the large snowballs
                    
                    
                    tiles[selectedRow][selectedCol].setText("");
                    tiles[selectedRow][selectedCol].setIcon(null);

                    tiles[stackRow][stackCol].setText("");
                    tiles[stackRow][stackCol].setIcon(null);

                    placeStack(stackRow, stackCol);
                    
                }

                else if(piece.equals("Head") && destinationPiece.equals("Stack")){

                    tiles[selectedRow][selectedCol].setText("");
                    tiles[selectedRow][selectedCol].setIcon(null);

                    tiles[stackRow][stackCol].setText("");
                    tiles[stackRow][stackCol].setIcon(null);

                    placeCompleteStack(stackRow, stackCol); // places completed snowman in that place
                }

                else {

                    tiles[selectedRow][selectedCol].setText("");
                    tiles[selectedRow][selectedCol].setIcon(null);

                    tiles[newRow][newCol].setText(piece);

                    if(piece.equals("Small")) {

                        tiles[newRow][newCol].setIcon(SmallSnowballIcon);

                    }

                    else if (piece.equals("Large")) {

                        tiles [newRow][newCol].setIcon(LargeSnowballIcon);
                    }

                    else if (piece.equals("Head")) {

                        tiles[newRow][newCol].setIcon(SnowmanHeadIcon);
                    }
                }
            }

         //deleted code position if needed.
    
        
        else {

            System.out.println("Invalid Move"); //tells the player in the output that this is an invalid move
            
            tiles[selectedRow][selectedCol].setBackground(null);

        }

            selectedRow = -1;
            selectedCol = -1;
        
            System.out.println("The Piece has been moved"); //tell player the piece has moved correctly.
    }
}

    public boolean Blocked(int row, int col) { // snowballs will stop moving if they are blocked by another piece.

        if (row < 0 || row >= tiles.length ||
            col < 0 || col >= tiles[row].length) {

                return true;
            }

            return !tiles[row][col].getText().equals("");

            
    }

    public void placeSmallSnowball(int row, int col) {

        tiles[row][col].setText("Small");
        tiles[row][col].setIcon(SmallSnowballIcon);
    }

    public void placeLargeSnowball(int row, int col) {

        tiles[row][col].setText("Large");
        tiles[row][col].setIcon(LargeSnowballIcon);
    }

    public void placeSnowmanHead(int row, int col) {

        tiles[row][col].setText("Head");
        tiles[row][col].setIcon(SnowmanHeadIcon);
    }

    public void placeStack(int row, int col) {

        tiles[row][col].setText("Stack");
        tiles[row][col].setIcon(StackIcon);
    }

    public void placeCompleteStack(int row, int col) {

        tiles[row][col].setText("CompleteStack");
        tiles[row][col].setIcon(CompleteIcon);

    }

}

