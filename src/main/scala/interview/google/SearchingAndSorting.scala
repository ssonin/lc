package interview.google

import scala.collection.mutable.ArrayBuffer

object SearchingAndSorting {

  def main(args: Array[String]): Unit = {
//    println(merge(Array(Array(1, 4), Array(0, 4)))
//      .map(_.mkString("[", ", ", "]"))
//      .mkString(", "))
    println(searchRange(Array(8,8,8,8,8,8,10), 8).mkString(","))
  }

  /**
   * Merge Intervals
   */
  def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {
    val ordered = intervals.sortWith((a, b) => a(0) < b(0))
    var pivot = ordered(0)
    val acc = new ArrayBuffer[Array[Int]]()
    var i = 1
    while (i < intervals.length) {
      if (ordered(i)(0) <= pivot(1)) {
        pivot(1) = ordered(i)(1)
      } else {
        acc.addOne(pivot)
        pivot = ordered(i)
      }
      i += 1
    }
    acc.addOne(pivot)
    acc.toArray
  }

  def searchRange(nums: Array[Int], target: Int): Array[Int] = {
    def searchEarliest(): Int = {
      var left = 0
      var right = nums.length - 1
      while (left <= right) {
        val mid = left + (right - left) / 2
        if (nums(mid) == target) {
          if (mid - 1 >= 0 && nums(mid - 1) == target) {
            right = mid - 1
          } else return mid
        } else if (nums(mid) < target) {
          left = mid + 1
        } else {
          right = mid - 1
        }
      }
      -1
    }
    def searchLatest(): Int = {
      var left = 0
      var right = nums.length - 1
      while (left <= right) {
        val mid = left + (right - left) / 2
        if (nums(mid) == target) {
          if (mid + 1 < nums.length && nums(mid + 1) == target) {
            left = mid + 1
          } else return mid
        } else if (nums(mid) < target) {
          left = mid + 1
        } else {
          right = mid - 1
        }
      }
      -1
    }
    if (nums.isEmpty) Array(-1, -1)
    else Array(searchEarliest(), searchLatest())
  }

}
