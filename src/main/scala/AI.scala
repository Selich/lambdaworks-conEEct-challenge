case class AlphaBetaPair(var alpha: Int, var beta: Int)

class AI(val name: String, val symbol: Char,val dot: Int,val level: Int)  extends Player with Modes  {

  def getName: String = name
  def generateNewBoard(col: Int, dot: Int,board: Board) = board.move(col,dot)

  def move(board:Board): Int ={

    if(board.getState != States.InProgress)
      throw new Exception("Game is not in progress")
    else {
      var pair: AlphaBetaPair = AlphaBetaPair(-999999, 999999)
      var resultColumn: Int = 0
      for (col <- 0 until board.getDimY) {
        if (board.isValidMove(col, board.getDimY, board.position, board.getState)) {
          val newBoard: Board = board.move(col, dot)
          val solution: Int = calculate(newBoard, level - 1, false, pair)
          if (pair.alpha < solution) {
            pair.alpha = solution; resultColumn = col
          }
        }
      }
      resultColumn
    }

  }

  def calculate(board: Board, level: Int, isMaximizingPlayer: Boolean, pair: AlphaBetaPair): Int = {
    // evaluate board state
    if (level == 0 || board.getState != States.InProgress) {
      var score = 0
      board.getState match {
        case States.FourInLine =>
          if (board.winDot == dot) score = 1000000
          else score = 0
        case States.InProgress =>
        case _ => score = 999
      }
      score
    } else {
      // break flag
      var flag = false
      // if finding alpha
      if (isMaximizingPlayer) {
        var newAlpha = pair.alpha
        // for every col or when the break flag is true
        for (col <- 0 until board.dim.Y if !flag) {
          if (board.isValidMove(col, board.getDimY, board.position, board.getState)) {
            // choose the bigger alpha out of older alpha and returning recursion var
            newAlpha = math.max(newAlpha, calculate(generateNewBoard(col, dot, board), level - 1, !isMaximizingPlayer, AlphaBetaPair(newAlpha, pair.beta)))
            // Found a bigger alpha, break
            if (pair.beta <= newAlpha) flag = true
          }
        }
        newAlpha
      } else {
      // if finding beta
        var newBeta = pair.beta
        // for every col or when the break flag is true
        for (col <- 0 until board.dim.Y if !flag) {
          if (board.isValidMove(col, board.getDimY, board.position, board.getState)) {
            newBeta = math.min(newBeta, calculate(generateNewBoard(col, if (dot == 1) 2 else 1, board), level - 1, !isMaximizingPlayer, AlphaBetaPair(pair.alpha, newBeta)))
            // Found a smaller beta, break
            if (newBeta <= pair.alpha) flag = false
          }
        }
        newBeta
      }
    }
  }
  }
