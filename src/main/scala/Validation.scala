import States._

trait Validation extends Patterns {



  def isValidRow(row: Int,dimX: Int): Boolean = row >= 0 && row < dimX
  def isValidCol(col: Int, dimY: Int): Boolean = col >= 0 && col < dimY
  def isValidMove(col: Int, dimY: Int, position: (Int,Int) => Int,state: States.Value): Boolean = isValidCol(col, dimY) && position(0, col) == 0 && state != States.FourInLine
  def isValidMove(cell: Cell, board: Board): Boolean = (cell.col >= 0) && (cell.col < board.getDimY) && (cell.row >= 0) && (cell.row < board.getDimX)
  def areColumnsFull(table:List[Int], dimY: Int) : Boolean = (0 until dimY).count(col => table(col) != 0) == dimY
  def isWinner(table:List[Int], row:Int, col:Int, dot:Int, dimX: Int, dimY: Int) : Boolean =
    areFourConnected( table, horizontal(row,col), dot, dimX, dimY ) ||
    areFourConnected( table, vertical(row,col), dot, dimX, dimY ) ||
    areFourConnected( table, firstDiagonal(row,col), dot, dimX, dimY ) ||
    areFourConnected( table, secondDiagonal(row,col), dot, dimX, dimY )
  def areFourConnected(table:List[Int],cellArray:Array[Cell],dot:Int,dimX: Int, dimY: Int):Boolean ={
    val cells = cellArray.foldLeft(List[Cell]())(
      (list,cell) => if ( list.length == 4) list
        else if (isValidRow(cell.row,dimX) && isValidCol(cell.col,dimY) && (table(cell.col + dimY * cell.row) == dot)) cell :: list
        else List()
    )
    cells.size == 4
  }




}
