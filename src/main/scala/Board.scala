import scala.collection.immutable
import scala.util._

case class Cell(row:Int, col:Int) {
  // my linter was begging me to add the equals method
  override def equals(that: Any): Boolean = this.col == that
}

// init const.
case class Board(
             var dim: Dimensions,
             table:List[Int],
             state: States.Value,
             winDot: Int ) extends Validation {

  // some getters, needed to to it a bit cleaner
  def getWinDot: Int = winDot
  def getState: States.Value = state
  def getDimX:Int = dim.X
  def getDimY:Int = dim.Y
  def getNumberOfDots = table.count(_ != 0)

  //get the position in table from coord
  def position(row: Int, col: Int): Int =
    if (isValidCol(col,dim.Y) && isValidRow(row,dim.X)) table(col + dim.Y * row)
    else 0

  //get the position in table from cell
  def position(cell: Cell): Int =
    if (isValidCol(cell.row,dim.Y) && isValidRow(cell.row,dim.X)) table(cell.col + dim.Y * cell.row)
    else 0

  def printBoard(board: Board, player1: Player, player2: Player): Unit ={
    for( row <- 0 until board.getDimX) {
      for ( col <- 0 until board.getDimY){
        board.position(row,col) match {
          case player1.dot => print(player1.symbol)
          case player2.dot => print(player2.symbol)
          case 0 => print("_")
        }
      }
      println()
    }
  }


  def drop(row:Int, col:Int, dot:Int): (List[Int], Int) = {
    position(row,col) match {
      case 0 =>
        val newGrid = table.updated(col + dim.Y * row, dot)
        newGrid -> row
      case _ => drop(row - 1, col, dot)
    }
  }

  def isValidMove(col: Int): Boolean = isValidCol(col, dim.Y) && position(0,col) == 0 && state != States.FourInLine

  def move(col: Int, dot: Int): Board ={
    if (isValidMove(col)) {
      drop(dim.X-1,col,dot) match {
        case(newGrid, row) =>
          if (isWinner(newGrid, row, col, dot, dim.X, dim.Y))
              new Board(Dimensions(dim.X, dim.Y), newGrid, States.FourInLine, dot)
          else
            if  (row == 0 && areColumnsFull(newGrid,dim.Y))
              new Board(Dimensions(dim.X, dim.Y), newGrid, States.OutOfBounds, dot)
            else
              new Board(Dimensions(dim.X,dim.Y), newGrid, States.InProgress, dot)
      }
    } else {
      throw new Exception("Illegal move")
    }
  }
  def cells: Seq[Cell] = for (row <- 0 until dim.X; col <- 0 until dim.Y) yield Cell(row,col)

}
object Board {
  def apply(dim: Dimensions): Board = {
    val table = List.fill[Int](dim.X * dim.Y)(0)
    new Board(Dimensions(dim.X,dim.Y), table, States.InProgress, 0)
  }
}

