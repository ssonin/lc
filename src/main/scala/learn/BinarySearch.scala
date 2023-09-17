package learn

import scala.collection.mutable

object BinarySearch {

  def main(args: Array[String]): Unit = {
//    println(search(Array(-1,0,3,5,9,12), 2))
//    println(search(Array(5), 5))
    println(mySqrt(2147395599))

  }

  def search(nums: Array[Int], target: Int): Int = {
    def binarySearch(left: Int, right: Int): Int = {
      if (left > right) -1
      else {
        val mid = (right + left) / 2
        if (target == nums(mid)) mid
        else if (target < nums(mid)) binarySearch(left, mid - 1)
        else binarySearch(mid + 1, right)
      }
    }
    binarySearch(0, nums.length - 1)
  }

  /**
   * Sqrt(x)
   */
  def mySqrt(x: Int): Int = x match {
    case 0 => 0
    case i if i < 3  => 1
    case _ =>
      val seq = 1 until x
      var left = 0
      var right = seq.length - 1
      while (left <= right) {
        val mid = left + (right - left) / 2
        val current: Long = seq(mid) * seq(mid)
        if (current == x || (current < x && seq(mid + 1).longValue() * seq(mid + 1) > x)) {
          return mid + 1
        }
        else if (current < x) {
          left = mid + 1
        } else {
          right = mid - 1
        }
      }
      -1
  }

  def guessNumber(n: Int): Int = {
    if (n < 2) n
    else {
      var left = 1
      var right = n
      while (left <= right) {
        val mid = left + (right - left) / 2
        guess(mid) match {
          case 0 => return mid
          case -1 => right = mid - 1
          case 1 => left = mid + 1
        }
      }
      -1
    }
  }

  def guess(n: Int): Int = ???

}
