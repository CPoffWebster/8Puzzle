import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class BoardTest {
    
    int [][] tiles;
    int [] array;
    
    @Test
    public void testTileAt() {
        int[][] tiles = {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };
        
        Board test = new Board(tiles);
        assertEquals(8, test.tileAt(0, 0));
        assertEquals(5, test.tileAt(2, 2));
    }
    
    @Test
    public void testHamming() {
        int[][] tiles = {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };
        
        int[][] tiles2 = {
                {1, 3, 2},
                {4, 7, 6},
                {5, 8, 0}
        };
        
        int[][] tiles3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        
        Board test = new Board(tiles);
        Board test2 = new Board(tiles2);
        Board test3 = new Board(tiles3);
        assertEquals(5, test.hamming());
        assertEquals(4, test2.hamming());
        assertEquals(0, test3.hamming());
        
    }
    
    @Test
    public void testManhattan() {
        int[][] tiles = {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };
        
        int[][] tiles2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        
        int[][] tiles3 = {
                {1,  2,  3,  4,  5,  6,  7,  8,  9, 10 },
                {11, 12, 13, 14, 15, 16, 17, 18, 19, 20 },
                {21, 22, 23, 24, 25, 26, 27, 28, 29, 30 },
                {31, 32, 33, 34, 35, 36, 37, 38, 39, 40 },
                {41, 42, 43, 44, 45, 46, 47, 48, 49, 50 },
                {51, 52, 53, 54, 55, 56, 57, 58, 59, 60 },
                {61, 62, 63, 64, 65, 66, 67, 68, 69, 70 },
                {71, 72, 73, 74, 75, 76, 77, 78, 79, 80 },
                {81, 82, 83, 84, 85, 86, 87, 88, 89, 90 },
                {91, 92, 93, 94, 95, 96, 97, 98, 99,  0 }
        };
        
        Board test = new Board(tiles);
        Board test2 = new Board(tiles2);
        Board test3 = new Board(tiles3);
        assertEquals(10, test.manhattan());
        assertEquals(0, test2.manhattan());
        assertEquals(0, test3.manhattan());
        
    }
    
    @Test
    public void testIsGoal() {
        int[][] tiles = {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };
        
        int[][] tiles2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        
        Board test = new Board(tiles);
        Board test2 = new Board(tiles2);
        assertFalse(test.isGoal());
        assertTrue(test2.isGoal());
    }
    
    @Test
    public void testIsSolvable() {
        int[][] tiles = {
                {1, 2, 3},
                {4, 0, 6},
                {8, 5, 7}
        };
        
        int[][] tiles2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 15, 14, 0}
        };
        
        int[][] tiles3 = {
                {1, 2, 3, 4},
                {5, 6, 0, 8},
                {9, 10, 7, 11},
                {13, 14, 15, 12}
        };
        
        int[][] tiles4 = {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };
        
        Board test = new Board(tiles);
        Board test2 = new Board(tiles2);
        Board test3 = new Board(tiles3);
        Board test4 = new Board(tiles4);
        assertFalse(test.isSolvable());
        assertFalse(test2.isSolvable());
        assertTrue(test3.isSolvable());
        assertTrue(test4.isSolvable());
        
    }
    
    @Test
    public void testEquals() {
        int[][] tiles = {
                {1, 2, 3},
                {4, 0, 6},
                {8, 5, 7}
        };
        
        int[][] tiles2 = {
                {1, 2, 3},
                {4, 0, 6},
                {8, 5, 7}
        };
        
        int[][] tiles3 = {
                {1, 2, 3},
                {4, 6, 0},
                {8, 5, 7}
        };
        
        Board test = new Board(tiles);
        Board test2 = new Board(tiles2);
        Board test3 = new Board(tiles3);
        assertTrue(test.equals(test2));
        assertFalse(test.equals(test3));
        
    }
    
    

}
