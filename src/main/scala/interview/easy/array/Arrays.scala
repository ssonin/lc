package interview.easy.array

object Arrays {

  def main(args: Array[String]): Unit = {

    println(plusOne(Array(1, 2, 3)).mkString(","))
  }

  def plusOne(digits: Array[Int]): Array[Int] = {

    def walk(i: Int, carry: Int, acc: List[Int]): List[Int] = {
      if (i < 0)
        if (carry == 0) acc
        else 1 :: acc
      else {
        val result = digits(i) + carry
        if (result > 9) walk(i - 1, 1, 0 :: acc)
        else walk(i - 1, 0, result :: acc)
      }
    }

    walk(digits.length - 1, 1, Nil).toArray
  }

}
