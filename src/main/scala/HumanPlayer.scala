import scala.io.StdIn

class HumanPlayer(val name: String, val symbol: Char,val dot: Int) extends Player {

  def getName: String = name

  def move(board: Board): Int = {
    if(board.getState != States.InProgress)
      throw new Exception("Game is not in progress")
    println("")
    println("Enter a # of column: ( range 1 - 6 )")
    println("Human Turn: ")
    var input = StdIn.readInt()
    input = input - 1
    // works here
    if(input < 0 || input >= board.getDimY){
      println("Illegal move")
      move(board)
    }

    input
  }


}
