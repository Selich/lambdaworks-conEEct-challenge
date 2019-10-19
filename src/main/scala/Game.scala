import scala.io.StdIn
case class Game(var board: Board, firstPlayer: Player, secondPlayer: Player) {

  override def equals(that: Any): Boolean = true

  gameLoop(firstPlayer,secondPlayer,board)

  def gameLoop(currentPlayer: Player, otherPlayer: Player, board: Board): Unit = {
    var newBoard = board.move(currentPlayer.move(board), currentPlayer.dot)
    println("")
    board.printBoard(newBoard, currentPlayer, otherPlayer)
    newBoard.getState match {
      case States.InProgress  => continueGame(newBoard, currentPlayer, otherPlayer)
      case States.FourInLine  => printWinner(newBoard, currentPlayer, otherPlayer)
      case States.OutOfBounds => printOutOfBounds(newBoard, currentPlayer, otherPlayer)
      case _ => println("Well... this is unexpected"); throw new Exception("Game end")
    }
  }

  def continueGame(newBoard: Board, currentPlayer: Player, otherPlayer: Player) {
    gameLoop(otherPlayer, currentPlayer, newBoard)
  }

  def printWinner(newBoard: Board, currentPlayer: Player, otherPlayer: Player) : Unit = {
    println("")
    newBoard.getWinDot match {
      case firstPlayer.dot => println(firstPlayer.getName + " ( " + firstPlayer.symbol + " ) " + "is the winner!")
      case secondPlayer.dot => println(secondPlayer.getName + " ( " + secondPlayer.symbol + " ) " + "is the winner!")
      case _ => println("This is not right...")
    }
    retry(currentPlayer, otherPlayer, newBoard)
  }
  def printOutOfBounds(newBoard: Board, currentPlayer: Player , otherPlayer: Player) : Unit = {
    println("Draw")
    retry(currentPlayer, otherPlayer, newBoard)
  }
  def retry(currentPlayer: Player, otherPlayer: Player, newBoard: Board): Unit = {
    println("")
    println("Retry? (y/n)")
    var board = Board(newBoard.dim)
    StdIn.readChar() match {
      case 'n' => sys.exit(0)
      case _ =>
        println("")
        print("Winner goes first? (y/n): ")
        StdIn.readChar() match {
          case 'y' =>
            gameLoop(currentPlayer, otherPlayer, board)
          case 'n' =>
            gameLoop(otherPlayer, currentPlayer, board)
          case _ =>
            println("Enter valid input.")
            retry(currentPlayer, otherPlayer, board)
        }
    }
  }





}

