import Queue;


public class Board {
    
    private static final int EMPTY = 0; //represents the empty space 
    
    private int[][] board; //the NbyN board
    
    // construct a board from an N-by-N array of tiles
    // (where tiles[i][j] = tile at row i, column j)
    public Board(int[][] tiles) {           
        int N = tiles.length;
        this.board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.board[i][j] = tiles[i][j];
            }
        }
    }
    
    // return tile at row i, column j (or 0 if blank)
    public int tileAt(int i, int j) {
        //corner case
        if (i < 0 && i > (size() - 1) || j < 0 && j > (size() - 1)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return board[i][j];
    }
    
    // board size N
    public int size() {
        return board.length;
    }
    
    // number of tiles out of place
    public int hamming() {
        int N = size(); //size of board length
        int count = 0; //count the total hamming
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (isNotInPlace(row, col)) {
                    count++;
                }
            }
        }
        return count; 
    }
    
    //checks to see if the number is not in the correct space
    private boolean isNotInPlace(int row, int col) {
        int space = board[row][col];
        
        //check if the space is empty and if the space is in the correct position
        return !emptySpace(space) && space != correctPosition(row, col);
    }
    
    //calculate the correct position for a space
    private int correctPosition(int row, int col) {
        return row*size() + col + 1;
    }
    
    //boolean for an empty space
    private boolean emptySpace(int space) {
        return space == EMPTY;
    }
    
    //int of space location on the board
    private int board(int row, int col) {
        return board[row][col];
    }
    
    
    
    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int N = size(); //size of board length
        int count = 0; //count the total manhattan
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] != correctPlace(row, col) && board[row][col] != 0) {
                    count += distanceAway(row, col);
                }
            }
        }
        return count; //for api compiler
    }
    
    private int correctPlace(int row, int col) {
        if (row == size() - 1 && col == size() - 1) return 0;
        return size() * row + col + 1;
    }
    
    //calculate distance
    private int distanceAway(int row, int col) {
        int space = board[row][col]; //position of space
        
        //where the space should be
        int correctRow = (space - 1) / size(); 
        int correctCol = (space - 1) % size(); 
        
        //calculate and return distanceAway
        return Math.abs(row - correctRow) + Math.abs(col - correctCol);
    }
    
    
    
    // is this board the goal board?
    public boolean isGoal()  {
        int N = size(); //size of board length
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (isNotInPlace(row, col)) return false;
            }
        }
        return true; 
    }
    
    
    // is this board solvable?
    public boolean isSolvable() {
        
        //initialize arrayForm of board[row][col]
        int N = size() * size(); //calculates all spaces in the grid
        int[] arrayForm = new int[N]; //initialize array to calculate inversions
        int k = 0; //set array values
        //initialize array
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                arrayForm[k] = board[i][j];
                k++;
            }
        }
        //even or odd board
        if (size() % 2 == 0) { //for even boards
            int sum = inversions(arrayForm) + blankRow();
            if (sum % 2 == 0) {  //if sum is even it is unsolvable
                return false;
            } else {  //if sum is odd than it is solvable
                return true;
            }
        } else { //for odd boards
            int sum = inversions(arrayForm);
            if (sum % 2 == 0) { //if sum is even it is solvable
                return true;
            } else {  //if sum is odd it is unsolvable
                return false;
            }
        }
    }
    
    //calculate the number of inversions in a board
    private int inversions(int[] array) {
        int count = 0; //number of inversions
        //count through the array
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                //when number is less than a greater number inc inversions
                if (array[i] > array[j]) {
                    if (array[i] == 0 || array[j] == 0) {
                        //do nothing
                    } else {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    //what row is the blank space in?
    private int blankRow() {
        for (int row = 0; row < size(); row++) {
            for (int col = 0; col < size(); col++) {
                //when the blank space is found
                if (emptySpace(board[row][col])) {
                    //return whatever row the space is in
                    return row;
                }
            }
        }
        return 0;
    }
    
    
    
    // does this board equal y?
    public boolean equals(Object y) {
        
        if (y == this) return true; //if they're equal
        if (y == null) return false; //if it's null they're not equal
        if (!(y instanceof Board)) return false; //if y isn't an instance of Board
        if (((Board) y).size() != size()) return false; //Boardy isn't the same size
        //compare the individual numbers in the two boards
        for (int row = 0; row < size(); row++)
            for (int col = 0; col < size(); col++)
                //if any number does not equal the same number in that position
                if (((Board) y).board[row][col] != board(row, col)) return false;
        //else return true
        return true;
    }
    
    //all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<Board>();
        
        //count through the board matrix
        for (int row = 0; row < size(); row++) {
            for (int col = 0; col < size(); col++) {
                if (board[row][col] == 0) { //at the empty space
                    
                    if (row > 0) { //shift left
                        neighbors.enqueue(newBoard(row, col, row - 1, col));
                    }
                    if (row < size() - 1) { //shift right
                        neighbors.enqueue(newBoard(row, col, row + 1, col));
                    }
                    if (col > 0) { //shift up
                        neighbors.enqueue(newBoard(row, col, row, col - 1));
                    }
                    if (col < size() - 1) { //shift down
                        neighbors.enqueue(newBoard(row, col, row, col + 1));
                    }
                    return neighbors;
                }
            }
        }
        return neighbors;
    }
    
    //new board for game tree
    private Board newBoard(int row1, int col1, int row2, int col2) {
        Board newBoard = new Board(board);
        newBoard.shift(row1, col1, row2, col2);
        return newBoard;
    }
    
    //shift numbers to other positions in the grid
    private void shift(int row1, int col1, int row2, int col2) {
        int temp = board[row2][col2];
        board[row2][col2] = board[row1][col1];
        board[row1][col1] = temp;
    }
    
    
    
    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder printBoard = new StringBuilder();
        printBoard.append(size() + "\n");
        for (int row = 0; row < size(); row++) {
            for (int col = 0; col < size(); col++)
                printBoard.append(String.format("%2d ", board(row, col)));
            printBoard.append("\n");
        }
        return printBoard.toString();
    }
    
    
    
    
    // unit testing (required)
    public static void main(String[] args) {
        
    }
}

