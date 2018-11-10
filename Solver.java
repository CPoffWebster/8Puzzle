import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    
    private searchNode goal = null; //search node
    
    private class searchNode implements Comparable<searchNode> {
        private Board board; 
        private searchNode previous;
        private int countMoves; //count the number of moves made
        
        //initiate  variables
        searchNode(Board board, searchNode previous, int countMoves) {
            this.board = board;
            this.previous = previous;
            this.countMoves = countMoves;
        }
        
        
        @Override
        public int compareTo(searchNode that) {
            //calculate the priority
            if (this.priority() < that.priority()) {
                return -1;
            }
            if (this.priority() == that.priority()) {
                return 0;
            }
            return 1;
        }
        
        //priority can use manhattan or hamming
        public int priority() {
            return board.manhattan() + countMoves;
            
        }
        
    }
    
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        //corner cases
        if (initial == null) throw new java.lang.NullPointerException();
        if (!initial.isSolvable()) throw new java.lang.IllegalArgumentException();
        
        //create goal search node
        if (initial.isGoal()) goal = new searchNode(initial, null, 0);
        
        
        MinPQ<searchNode> PQ = new MinPQ<>(); //create min priority queue
        searchNode current = new searchNode(initial, null, 0); //create searchNode for how the board currently is
        //insert the current searchNode into the priority Queue
        PQ.insert(current);
        
        //A* algorithm
        while (!current.board.isGoal()) { //not the goal board
            current = PQ.delMin(); //choose the smallest value

            for (Board b : current.board.neighbors()) { //look at neighbors
                if (b != current.board) { //as long as the board isn't a duplicate
                    PQ.insert(new searchNode(b, current, current.countMoves + 1)); //insert it
                }
            } //check if it is the goal board
            if (current.board.isGoal()) { 
                goal = current;
            }
        }
     }


     //min number of moves to solve initial board
     public int moves() {
        return goal.countMoves;
     }

     //sequence of boards in a shortest solution
     public Iterable<Board> solution() {
        Stack<Board> sequence = new Stack<>();
        sequence.push(goal.board); //add goal board to the stack
        while (goal.previous != null) { 
            goal = goal.previous;
            sequence.push(goal.board); //goal.previous
        }
        //return the stack
        return sequence;
     }

    
    // unit testing 
    public static void main(String[] args) {
        
    }
    
}

