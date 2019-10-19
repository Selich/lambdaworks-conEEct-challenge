import util.control.Breaks._
import scala.annotation.tailrec
import scala.io.StdIn
import scala.util._

trait Modes{
    var isDebugMode = false;
    var isMenuMode = true;
}

case class Dimensions(val X:Int, val Y:Int)
object Main extends App with Modes{


    print("\u001b")
    var humanSymbol = 'o'
    var botSymbol = 'x'

    val botName = "Bot2000"
    val humanName = "Hu-MAN"


    var isHumanFirst = true

    var dimX: Int = 6
    var dimY: Int = 6

    if(isMenuMode){
        println(" |                     |         |     \\ \\        /          |         \n |      _` | __ `__ \\  __ \\   _` |  _` |\\ \\  \\   / _ \\   __| |  /  __| \n |     (   | |   |   | |   | (   | (   | \\ \\  \\ / (   | |      < \\__ \\ \n_____|\\__,_|_|  _|  _|_.__/ \\__,_|\\__,_|  \\_/\\_/ \\___/ _|   _|\\_\\____/ \n                                                                      ")
        println("Welcome to the conEEct LambdaWorks challenge!")
        println("Choose the dimensions of the board: ")
        println("")
        print("Number of rows: ")
        dimX = StdIn.readInt()
        println("")
        print("Number of columns: ")
        dimY = StdIn.readInt()
        if(dimX < 4 && dimY < 4) {
            println("")
            println("Well, that does not make much sense, but ok...")
        }

        println("")
        print("Choose your symbol: ")
        humanSymbol = StdIn.readChar()
        println("")
        print("Choose bots symbol: ")
        botSymbol = StdIn.readChar()
        println("")
        println("Who shall go first? ")
        println("- 1.You")
        println("- 2.Bot")
        println("- 3.Random")
        StdIn.readInt() match {
            case 1 => isHumanFirst = true
            case 2 => isHumanFirst = false
            case 3 => isHumanFirst= if(Random.nextInt() % 2 == 0) true else false
            case _ =>  println("Choose a number between 1 - 3")

        }
    }

    val human = new HumanPlayer(humanName, humanSymbol, 1)
    val bot = new AI(botName,  botSymbol, 2,dimX + 1)

    val board = Board(Dimensions(dimX,dimY))

    if(isHumanFirst) {
        val game: Game = new Game(board , human, bot)
        game.gameLoop(human,bot,board)
    } else {
        val game: Game = new Game(board , bot, human)
        game.gameLoop(bot,human,board)

    }



}
