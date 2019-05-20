/**
 *
 * @author Tezza Giacomo
 */
public class Piece {
    private final PieceType type;
    private final PieceType color;
    
    public Piece(PieceType type, PieceType color) {
        this.type = type;
        this.color = color;
    }
    
    @Override
    public String toString() {
        String s = "";
        switch (getType()) {
            case PAWN:
                s += "P";
                break;
            case ROOK:
                s += "R";
                break;
            case KNIGHT:
                s += "k";
                break;
            case BISHOP:
                s += "B";
                break;
            case KING:
                s += "K";
                break;
            case QUEEN:
                s += "Q";
                break;
        }
        return s;
    }

    /**
     * @return the type
     */
    public PieceType getType() {
        return type;
    }

    /**
     * @return the color
     */
    public PieceType getColor() {
        return color;
    }
}
