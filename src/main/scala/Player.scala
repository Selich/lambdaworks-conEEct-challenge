import scala.io.StdIn

trait Player {

  val name:String
  val symbol: Char
  val dot: Int


  def getName: String
  def move(board:Board) : Int

}

