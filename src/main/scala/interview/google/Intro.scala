package interview.google

import scala.collection.mutable

object Intro {

  def main(args: Array[String]): Unit = {
    //    println(search(Array(-1,0,3,5,9,12), 2))
    //    println(search(Array(5), 5))

//    println(licenseKeyFormatting("5F3Z-2e-9-w", 4))
//    println(licenseKeyFormatting("2-5g-3-J", 2))
    println(licenseKeyFormatting("2-4A0r7-4k", 3))
//    println(totalFruit(Array(3,3,3,1,2,1,1,2,3,3,4)))
//    println(totalFruit(Array(1,2,1,3,3)))
  }

  def totalFruit(fruits: Array[Int]): Int = {

    def dt(i: Int, bucket: Set[Int]): (Int, Set[Int]) = {
      if (i == fruits.length - 1) (1, Set(fruits.last))
      else {
        val (count, current) = dt(i + 1, bucket)
        if (current.size == 2 && !current.contains(fruits(i))) (count, current)
        else (count + 1, current + fruits(i))
      }
    }

    dt(0, Set())._1
  }

  /**
   * License Key Formatting
   */
  def licenseKeyFormatting(s: String, k: Int): String = {
    val cleared = for {
      ch <- s if ch != '-'
    } yield ch.toUpper
    val firstGroupLength = cleared.length % k
    val acc = new StringBuilder(cleared.substring(0, firstGroupLength))
    var i = firstGroupLength
    while (i <= cleared.length - k) {
      if (i > 0) {
        acc.append("-")
      }
      acc.append(cleared.substring(i, i + k))
      i += k
    }
    acc.toString()
  }

}
