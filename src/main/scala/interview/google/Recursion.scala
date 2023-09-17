package interview.google

object Recursion {

  def main(args: Array[String]): Unit = {

  }

  def generateParenthesis(n: Int): List[String] = {
    ???
  }

  /**
   * Strobogrammatic Number
   */
  def isStrobogrammatic(num: String): Boolean = {
    if (num.isEmpty) true
    else if (num.length == 1) isSymmetrical(num.head)
    else isSymmetrical(num.head, num.last) && isStrobogrammatic(num.substring(1, num.length - 1))
  }

  def isSymmetrical(x: Char, y: Char): Boolean = {
    (x == y && isSymmetrical(x)) || (x == '6' && y == '9') || (x == '9' && y == '6')
  }

  def isSymmetrical(x: Char): Boolean = x == '0' || x == '1' || x == '8'

}
