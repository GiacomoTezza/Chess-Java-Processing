Board board;
int squareSize;
PieceType player;
int clickX;
int clickY;
int selectedX;
int selectedY;
boolean isSelected;
ArrayList pmoves;
PImage b_king;
PImage w_king;
PImage b_queen;
PImage w_queen;
PImage b_bishop;
PImage w_bishop;
PImage b_knight;
PImage w_knight;
PImage b_rook;
PImage w_rook;
PImage b_pawn;
PImage w_pawn;
PImage brownL;
PImage brownD;
PImage grayL;
PImage grayD;

void setup() {
  size(600, 600);
  background(0);
  board = new Board();
  squareSize = width / 8;
  player = PieceType.WITHE;
  isSelected = false;
  loadPieces();
}

void draw() {
  drawChessBoard();
  if (!isGameOver()) {
    drawSelection();
    drawPmoves(); 
    drawPieces();
  } else {
    textSize(squareSize / 2);
    textAlign(CENTER, CENTER);
    fill(0);
    text("Press 'R' to play again...", width/2, height/2);
  }
  if (keyPressed) {
    if (key == 'r') {
      board = new Board();
      player = PieceType.WITHE;
      isSelected = false;
    }
  }
}

void mouseClicked() {
  clickX = floor(mouseX / squareSize);
  clickY = floor(mouseY / squareSize);
  if (isSelected) {
    for (int i = 0; i < pmoves.size(); i++) {
      int cellR = ((int[])pmoves.get(i))[0];
      int cellC = ((int[])pmoves.get(i))[1];
      if (cellR == clickY && cellC == clickX) {
        board.move(selectedY, selectedX, clickY, clickX);
        clickX = selectedX;
        clickY = selectedY;
        player = (player == PieceType.WITHE) ? PieceType.BLACK : PieceType.WITHE;
      }
    }
  }
  isSelected = false;
  if (board.getPiece(clickY, clickX) != null && board.getPieceColor(clickY, clickX) == player) {
    isSelected = true;
    selectedX = clickX;
    selectedY = clickY;
    pmoves = board.getPossibleMoves(clickY, clickX);
  }
}

boolean isGameOver() {
  boolean isWKingDead = true;
  boolean isBKingDead = true;
  int WpiecesCount = 0;
  int BpiecesCount = 0;
  
  for (int i = 0; i < 8; i++) {
    for (int j = 0; j < 8; j++) {
      if (board.getPiece(i, j) != null) {
        if (board.getPieceColor(i, j) == PieceType.BLACK) {
          BpiecesCount++;
          if (board.getPieceType(i, j) == PieceType.KING) {
            isBKingDead = false;
          }
        } else {
          WpiecesCount++;
          if (board.getPieceType(i, j) == PieceType.KING) {
            isWKingDead = false;
          }
        }
      }
    }
  }
  
  return isWKingDead || isBKingDead || (WpiecesCount <= 1) || (BpiecesCount <= 1);
}

void drawSelection() {
  if (isSelected) {
    noStroke();
    fill(50, 200, 0, 50);
    rect(clickX * squareSize, clickY * squareSize, squareSize, squareSize);
  }
}

void drawPmoves() {
  int cellR, cellC;
  if (isSelected) {
    for (int i = 0; i < pmoves.size(); i++) {
      cellR = ((int[])pmoves.get(i))[0];
      cellC = ((int[])pmoves.get(i))[1];
      noStroke();
      fill(255, 200, 0, 50);
      rect(cellC * squareSize, cellR * squareSize, squareSize, squareSize);
    }
  }
}

void drawChessBoard() {
  for (int i = 0; i < 8; i++) {
    for (int j = 0; j < 8; j++) {
      if ((i%2 == 0) == (j%2 == 0)) {
        image(brownD, j * squareSize, i * squareSize, squareSize, squareSize);
      } else {
        image(brownL, j * squareSize, i * squareSize, squareSize, squareSize);
      }
    }
  }
}

void drawPieces() {
  for (int i = 0; i < 8; i++) {
    for (int j = 0; j < 8; j++) {
      if (board.getPieceType(i, j) != null) {
        switch (board.getPieceType(i, j)) {
        case PAWN:
          if (board.getPieceColor(i, j) == PieceType.BLACK) {
            image(b_pawn, j * squareSize, i * squareSize, squareSize, squareSize);
          } else {
            image(w_pawn, j * squareSize, i * squareSize, squareSize, squareSize);
          }
          break;

        case ROOK:
          if (board.getPieceColor(i, j) == PieceType.BLACK) {
            image(b_rook, j * squareSize, i * squareSize, squareSize, squareSize);
          } else {
            image(w_rook, j * squareSize, i * squareSize, squareSize, squareSize);
          }
          break;

        case KNIGHT:
          if (board.getPieceColor(i, j) == PieceType.BLACK) {
            image(b_knight, j * squareSize, i * squareSize, squareSize, squareSize);
          } else {
            image(w_knight, j * squareSize, i * squareSize, squareSize, squareSize);
          }
          break;

        case BISHOP:
          if (board.getPieceColor(i, j) == PieceType.BLACK) {
            image(b_bishop, j * squareSize, i * squareSize, squareSize, squareSize);
          } else {
            image(w_bishop, j * squareSize, i * squareSize, squareSize, squareSize);
          }
          break;

        case KING:
          if (board.getPieceColor(i, j) == PieceType.BLACK) {
            image(b_king, j * squareSize, i * squareSize, squareSize, squareSize);
          } else {
            image(w_king, j * squareSize, i * squareSize, squareSize, squareSize);
          }
          break;

        case QUEEN:
          if (board.getPieceColor(i, j) == PieceType.BLACK) {
            image(b_queen, j * squareSize, i * squareSize, squareSize, squareSize);
          } else {
            image(w_queen, j * squareSize, i * squareSize, squareSize, squareSize);
          }
          break;

        default:
          break;
        }
      }
    }
  }
}

void loadPieces() {
  b_king = loadImage("./img/b_king.png");
  w_king = loadImage("./img/w_king.png");
  b_queen = loadImage("./img/b_queen.png");
  w_queen = loadImage("./img/w_queen.png");
  b_bishop = loadImage("./img/b_bishop.png");
  w_bishop = loadImage("./img/w_bishop.png");
  b_knight = loadImage("./img/b_knight.png");
  w_knight = loadImage("./img/w_knight.png");
  b_rook = loadImage("./img/b_rook.png");
  w_rook = loadImage("./img/w_rook.png");
  b_pawn = loadImage("./img/b_pawn.png");
  w_pawn = loadImage("./img/w_pawn.png");
  brownL = loadImage("./img/brownL.png");
  brownD = loadImage("./img/brownD.png");
  grayL = loadImage("./img/grayL.png");
  grayD = loadImage("./img/grayD.png");
}
