package interview.google

import scala.collection.mutable

object ArraysAndStrings {

  def main(args: Array[String]): Unit = {
//    println(lengthOfLongestSubstring("pwwkew"))
//    println(lengthOfLongestSubstring("aab"))
    println(maxArea(Array(1,8,6,2,5,4,8,3,7)))
  }

  /**
   * Longest Substring Without Repeating Characters
   */
  def lengthOfLongestSubstring(s: String): Int = {
    var maxLength = 0
    var count = 0
    val acc = mutable.Map[Char, Int]()
    var i = 0
    while (i < s.length) {
      var j = i
      while (j < s.length && !acc.contains(s(j))) {
        count += 1
        acc += s(j) -> j
        j += 1
      }
      maxLength = Math.max(maxLength, count)
      count = 0
      i = if (j < s.length) acc(s(j)) + 1 else i + 1
      acc.clear()
    }
    maxLength
  }

  /**
   * Container With Most Water
   */
  def maxArea(height: Array[Int]): Int = {
    var i = 0
    var j = height.length - 1
    var maxVolume = 0
    var currentVolume = 0
    while (i < j) {
      currentVolume = Math.min(height(i), height(j)) * (j - i)
      maxVolume = Math.max(maxVolume, currentVolume)
      if (height(i) > height(j)) j -= 1
      else i += 1
    }
    maxVolume
  }

  /**
   * Valid Parentheses
   */
  def isValid(s: String): Boolean = {
    val opening = Map('(' -> 0, '{' -> 1, '[' -> 2)
    val closing = Map(')' -> 0, '}' -> 1, ']' -> 2)
    val i = Int.MinValue

    def helper(s: String, acc: List[Char]): Boolean = {
      if (s.isEmpty) acc.isEmpty
      else if (opening.contains(s.head)) helper(s.tail, s.head :: acc)
      else acc.nonEmpty && closing(s.head) == opening(acc.head) && helper(s.tail, acc.tail)
    }
    helper(s, Nil)
  }

}
