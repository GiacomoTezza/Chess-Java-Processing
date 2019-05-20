/*
 * Chess game
 */
package chess;

import java.util.ArrayList;

/**
 *
 * @author Tezza Giacomo
 */
public class ChessApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board);
        System.out.println();
        board.move(0, 0, 3, 3);
        board.move(1, 0, 3, 1);
        System.out.println(board);
        ArrayList pmoves = board.getPossibleMoves(3, 3);
        for (int w = 0; w < pmoves.size(); w++) {
            System.out.print(((int[]) pmoves.get(w))[0] + " " + ((int[]) pmoves.get(w))[1] + "\t");
        }
        System.out.println();
    }
    
}
