abstract class Patterns {
  def horizontal(row:Int, col:Int): Array[Cell] =
    Array(
      Cell(row, col - 3),
      Cell(row, col - 2),
      Cell(row, col - 1),
      Cell(row, col),
      Cell(row, col + 1),
      Cell(row, col + 2),
      Cell(row, col + 3)
    )
  def vertical(row:Int, col:Int): Array[Cell] =
    Array(
      Cell(row - 3, col),
      Cell(row - 2, col),
      Cell(row - 1, col),
      Cell(row    , col),
      Cell(row + 1, col),
      Cell(row + 2, col),
      Cell(row + 3, col)
    )
  def firstDiagonal(row:Int, col:Int): Array[Cell] =
    Array(
      Cell(row - 3, col - 3),
      Cell(row - 2, col - 2),
      Cell(row - 1, col - 1),
      Cell(row    , col),
      Cell(row + 1, col + 1),
      Cell(row + 2, col + 2),
      Cell(row + 3, col + 3)
    )
  def secondDiagonal(row:Int, col:Int): Array[Cell] =
    Array(
      Cell(row + 3, col - 3),
      Cell(row + 2, col - 2),
      Cell(row + 1, col - 1),
      Cell(row    , col),
      Cell(row - 1, col + 1),
      Cell(row - 2, col + 2),
      Cell(row - 3, col + 3)
    )


}

