package chess;

import java.util.ArrayList;

/**
 *
 * @author Tezza Giacomo
 */
public class Board {
    private final int size;
    private Piece matrix[][];
    
    public Board() {
        this.size = 8;
        matrix = new Piece[size][size];
        init();
    }
    
    private void init() {
        for (int col = 0; col < size; col++) {
            matrix[1][col] = new Piece(PieceType.PAWN, PieceType.BLACK);
            matrix[6][col] = new Piece(PieceType.PAWN, PieceType.WITHE);
        }
        for (int col = 0; col < size; col++) {
            switch (col) {
                case 0:
                    matrix[0][col] = new Piece(PieceType.ROOK, PieceType.BLACK);
                    matrix[7][col] = new Piece(PieceType.ROOK, PieceType.WITHE);
                    break;
                case 1:
                    matrix[0][col] = new Piece(PieceType.KNIGHT, PieceType.BLACK);
                    matrix[7][col] = new Piece(PieceType.KNIGHT, PieceType.WITHE);
                    break;
                case 2:
                    matrix[0][col] = new Piece(PieceType.BISHOP, PieceType.BLACK);
                    matrix[7][col] = new Piece(PieceType.BISHOP, PieceType.WITHE);
                    break;
                case 3:
                    matrix[0][col] = new Piece(PieceType.QUEEN, PieceType.BLACK);
                    matrix[7][col] = new Piece(PieceType.QUEEN, PieceType.WITHE);
                    break;
                case 4:
                    matrix[0][col] = new Piece(PieceType.KING, PieceType.BLACK);
                    matrix[7][col] = new Piece(PieceType.KING, PieceType.WITHE);
                    break;
                case 5:
                    matrix[0][col] = new Piece(PieceType.BISHOP, PieceType.BLACK);
                    matrix[7][col] = new Piece(PieceType.BISHOP, PieceType.WITHE);
                    break;
                case 6:
                    matrix[0][col] = new Piece(PieceType.KNIGHT, PieceType.BLACK);
                    matrix[7][col] = new Piece(PieceType.KNIGHT, PieceType.WITHE);
                    break;
                case 7:
                    matrix[0][col] = new Piece(PieceType.ROOK, PieceType.BLACK);
                    matrix[7][col] = new Piece(PieceType.ROOK, PieceType.WITHE);
                    break;
            }
        } 
    }
    
    public void move(int row1, int col1, int row2, int col2) {
        matrix[row2][col2] = matrix[row1][col1];
        matrix[row1][col1] = null;
    }
    
    private boolean isInBoard(int row, int col) {
        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean isLegal(int move[], PieceType color) {
        int r = move[0];
        int c = move[1];
        
//        if (r < 0 && r > 7 && c < 0 && c > 7) {
//            return false;
//        }
        return isInBoard(r, c) && matrix[r][c] == null;
    }
    
    private boolean isEatable(int move[], PieceType color) {
        int r = move[0];
        int c = move[1];
        PieceType oppositeColor = (color == PieceType.WITHE) ? PieceType.BLACK : PieceType.WITHE;
        
        if (isInBoard(r, c) && matrix[r][c] != null) {
            if (matrix[r][c].getColor() == oppositeColor) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList getPossibleMoves(int row, int col) {
        Piece piece = matrix[row][col];
        ArrayList possibleMoves = new ArrayList();
        int r, c;
        int cell[] = new int[2];
        
        if (piece != null) {
            switch (piece.getType()) {
                case PAWN:
                    if (piece.getColor() == PieceType.WITHE) {
                        if (isInBoard(row - 1, col - 1) && matrix[row - 1][col - 1] != null && matrix[row - 1][col - 1].getColor() == PieceType.BLACK) {
                            cell[0] = row - 1;
                            cell[1] = col - 1;
                            possibleMoves.add(cell.clone()); 
                        }
                        if (isInBoard(row - 1, col + 1) && matrix[row - 1][col + 1] != null && matrix[row - 1][col + 1].getColor() == PieceType.BLACK) {
                            cell[0] = row - 1;
                            cell[1] = col + 1;
                            possibleMoves.add(cell.clone()); 
                        }
//                        if (row == 6) {
//                            cell[0] = row - 2;
//                            cell[1] = col;
//                            if (isLegal(cell, piece.getColor()) && matrix[row - 1][col] == null) {
//                               possibleMoves.add(cell.clone()); 
//                            }
//                        }
                        cell[0] = row - 1;
                        cell[1] = col;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        }
                    } else {
                        if (isInBoard(row + 1, col - 1) && matrix[row + 1][col - 1] != null && matrix[row + 1][col - 1].getColor() == PieceType.WITHE) {
                            cell[0] = row + 1;
                            cell[1] = col - 1;
                            possibleMoves.add(cell.clone()); 
                        }
                        if (isInBoard(row + 1, col + 1) && matrix[row + 1][col + 1] != null && matrix[row + 1][col + 1].getColor() == PieceType.WITHE) {
                            cell[0] = row + 1;
                            cell[1] = col + 1;
                            possibleMoves.add(cell.clone()); 
                        }
//                        if (row == 1) {
//                            cell[0] = row + 2;
//                            cell[1] = col;
//                            if (isLegal(cell, piece.getColor()) && matrix[row + 1][col] == null) {
//                               possibleMoves.add(cell.clone()); 
//                            }
//                        }
                        cell[0] = row + 1;
                        cell[1] = col;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        }
                    }
                    break;
                case ROOK:
                    r = row;
                    c = col;
                    while (r > 0) {
                        r--;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone());
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (r < 7) {
                        r++;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (c > 0) {
                        c--;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (c < 7) {
                        c++;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }
    //                for (r = 0; r < 8; r++) {
    //                    if (r != row) {
    //                        cell[0] = r;
    //                        cell[1] = col;
    //                        if (isLegal(cell, piece.getColor())) {
    //                           possibleMoves.add(cell.clone()); 
    //                        }
    //                    }
    //                }
    //                for (c = 0; c < 8; c++) {
    //                    if (c != col) {
    //                        cell[0] = row;
    //                        cell[1] = c;
    //                        if (isLegal(cell, piece.getColor())) {
    //                           possibleMoves.add(cell.clone()); 
    //                        }
    //                    }
    //                }
                    break;
                case KNIGHT:
                    cell[0] = row - 2;
                    cell[1] = col - 1;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row - 2;
                    cell[1] = col + 1;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row - 1;
                    cell[1] = col - 2;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row - 1;
                    cell[1] = col + 2;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row + 1;
                    cell[1] = col - 2;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row + 1;
                    cell[1] = col + 2;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row + 2;
                    cell[1] = col - 1;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row + 2;
                    cell[1] = col + 1;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    break;
                case BISHOP:
                    r = row;
                    c = col;
                    while (r > 0 && c > 0) {
                        r--;
                        c--;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (r < 7 && c < 7) {
                        r++;
                        c++;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (r < 7 && c > 0) {
                        r++;
                        c--;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (r > 0 && c < 7) {
                        r--;
                        c++;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }
                    break;
                case KING:
                    cell[0] = row - 1;
                    cell[1] = col - 1;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row - 1;
                    cell[1] = col;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row - 1;
                    cell[1] = col + 1;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row;
                    cell[1] = col - 1;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row;
                    cell[1] = col + 1;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row + 1;
                    cell[1] = col - 1;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row + 1;
                    cell[1] = col;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    cell[0] = row + 1;
                    cell[1] = col + 1;
                    if (isLegal(cell, piece.getColor())) {
                        possibleMoves.add(cell.clone()); 
                    } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                    }
                    break;
                case QUEEN:
                    r = row;
                    c = col;
                    while (r > 0) {
                        r--;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (r < 7) {
                        r++;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (c > 0) {
                        c--;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (c < 7) {
                        c++;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }
    //                for (r = 0; r < 8; r++) {
    //                    if (r != row) {
    //                        cell[0] = r;
    //                        cell[1] = col;
    //                        if (isLegal(cell, piece.getColor())) {
    //                            possibleMoves.add(cell.clone()); 
    //                        }
    //                    }
    //                }
    //                for (c = 0; c < 8; c++) {
    //                    if (c != col) {
    //                        cell[0] = row;
    //                        cell[1] = c;
    //                        if (isLegal(cell, piece.getColor())) {
    //                            possibleMoves.add(cell.clone()); 
    //                        }
    //                    }
    //                }

                    r = row;
                    c = col;
                    while (r > 0 && c > 0) {
                        r--;
                        c--;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (r < 7 && c < 7) {
                        r++;
                        c++;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (r < 7 && c > 0) {
                        r++;
                        c--;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }

                    r = row;
                    c = col;
                    while (r > 0 && c < 7) {
                        r--;
                        c++;
                        cell[0] = r;
                        cell[1] = c;
                        if (isLegal(cell, piece.getColor())) {
                            possibleMoves.add(cell.clone()); 
                        } else if (isEatable(cell, piece.getColor())){
                            possibleMoves.add(cell.clone());
                            break;
                        } else {
                            break;
                        }
                    }
                    break;
            }
            return possibleMoves;
        }
        return null;
    }
    
    public PieceType getPieceType(int row, int col) {
        if (matrix[row][col] != null) {
            return matrix[row][col].getType();
        }
        return null;
    }
    
    public PieceType getPieceColor(int row, int col) {
        if (matrix[row][col] != null) {
            return matrix[row][col].getColor();
        }
        return null;
    }
    
    public Piece getPiece(int row, int col) {
        return matrix[row][col];
    }
    
    @Override
    public String toString() {
        String s = "";
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                s += (matrix[row][col] == null) ? " " : matrix[row][col];
            }
            s += "\n";
        }
        return s;
    }
}
