import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SalamanderSearch {
    public static void main(String[] args) {
        char[][] enclosure1 = {
            {'.','.','.','.','.','.'},
            {'W','.','W','W','.','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','s','.','.'},
        };

        char[][] enclosure2 = {
            {'.','.','.','.','.','.'},
            {'W','W','W','W','s','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','.','.','.'},
        };
    }

    /**
     * Returns whether a salamander can reach the food in an enclosure.
     * 
     * The enclosure is represented by a rectangular char[][] that contains
     * ONLY the following characters:
     * 
     * 's': represents the starting location of the salamander
     * 'f': represents the location of the food
     * 'W': represents a wall
     * '.': represents an empty space the salamander can walk through
     * 
     * The salamander can move one square at a time: up, down, left, or right.
     * It CANNOT move diagonally.
     * It CANNOT move off the edge of the enclosure.
     * It CANNOT move onto a wall.
     * 
     * This method should return true if there is any sequence of steps that
     * the salamander could take to reach food.
     * 
     * @param enclosure
     * @return whether the salamander can reach the food
     * @throws IllegalArgumentException if the enclosure does not contain a salamander
     */
    public static boolean canReach(char[][] enclosure) {
        int[] start = salamanderLocation(enclosure);
        boolean[][] visisted = new boolean[enclosure.length][enclosure[0].length];
        return canReach(enclosure, start, visisted);
    }

    private static boolean canReach(char[][] enclosure, int[] current, boolean[][] visited) {
        int currentR = current[0];
        int currentC = current[1];
        if (enclosure[currentR][currentC] == 'f') return true;
        if (visited[currentR][currentC]) return false;
        visited[currentR][currentC] = true;
        List<int[]> moves = possibleMoves(enclosure, current);

        for (int[] x : moves) {
            if (canReach(enclosure, x, visited)) return true;
        }
        return false;
    }

    public static int[] salamanderLocation(char[][] enclosure) {
        for (int i = 0; i < enclosure.length; i++) {
            for (int j = 0; j < enclosure[0].length; j++) {
                if (enclosure[i][j] == 's') {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No salamander present");
    }

    public static List<int[]> possibleMoves(char[][] enclosure, int[] current) {
        int currentR = current[0];
        int currentC = current[1];

        List<int[]> output = new ArrayList<int[]>();

        int newR = currentR-1;
        int newC = currentC;
        if (newR >= 0 && enclosure[newR][newC] != 'W') output.add(new int[]{newR, newC});

        newR = currentR+1;
        newC = currentC;
        if (newR < enclosure.length && enclosure[newR][newC] != 'W') output.add(new int[]{newR, newC});

        newR = currentR;
        newC = currentC-1;
        if (newC >= 0 && enclosure[newR][newC] != 'W') output.add(new int[]{newR, newC});

        newR = currentR;
        newC = currentC+1;
        if (newC < enclosure[0].length && enclosure[newR][newC] != 'W') output.add(new int[]{newR, newC});
        
        return output;
    }
}
