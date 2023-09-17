package interview.google

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object DynamicProgramming {

  def main(args: Array[String]): Unit = {
//    println(coinChange(Array(1,2,5), 11))
    println(isPalindrome("abacaba"))
    println(longestPalindrome("bacabaca"))
  }

  /**
   * Longest Palindromic Substring
   */
  def longestPalindrome(s: String): String = {
    if (s.length == 1) s
    else {
      val dict = mutable.Map[Char, ArrayBuffer[Int]](s(0) -> ArrayBuffer(0))
      var result = (0, 1)
      for (i <- 1 until s.length) {
        if (dict.contains(s(i))) {
          for (j <- dict(s(i))) {
            if (isPalindrome(s.substring(j + 1, i))) {
              if (i + 1 - j > result._2 - result._1) {
                result = (j, i + 1)
              }
            }
          }
          dict(s(i)) += i
        } else {
          dict += s(i) -> ArrayBuffer(i)
        }
      }
      s.substring(result._1, result._2)
    }
  }

  def isPalindrome(s: String): Boolean = {
    if (s.length < 2) true
    else {
      var i = 0
      while (i < s.length / 2) {
        if (s(i) != s(s.length - 1 - i)) return false
        i += 1
      }
      true
    }
  }

  /**
   * Maximum Subarray
   */
  def maxSubArray(nums: Array[Int]): Int = {
    val dp = Array.ofDim[Int](nums.length)
    dp(0) = nums(0)
    var result = nums(0)
    for (i <- 1 until nums.length) {
      dp(i) = Math.max(dp(i - 1) + nums(i), nums(i))
      result = Math.max(dp(i), result)
    }
    result
  }

  /**
   * Best Time to Buy and Sell Stock
   */
  def maxProfit(prices: Array[Int]): Int = {
    var result = 0
    if (prices.length == 1) result
    else {
      var lowestPrice = prices(0)
      var profit = 0
      for (i <- 1 until prices.length) {
        profit = prices(i) - lowestPrice
        result = Math.max(result, profit)
        lowestPrice = Math.min(lowestPrice, prices(i))
      }
      result
    }
  }

  /**
   * Maximum Product Subarray
   */
  def maxProduct(nums: Array[Int]): Int = {
    val dp = Array.ofDim[Int](2, nums.length)
    dp(0)(0) = Math.max(nums(0), 0)
    dp(1)(0) = Math.min(nums(0), 0)
    var result = nums(0)
    for (i <- 1 until nums.length) {
      if (nums(i) > 0) {
        dp(0)(i) = Math.max(nums(i) * dp(0)(i - 1), nums(i))
        dp(1)(i) = nums(i) * dp(1)(i - 1)
      } else if (nums(i) < 0) {
        dp(0)(i) = nums(i) * dp(1)(i - 1)
        dp(1)(i) = Math.min(nums(i) * dp(0)(i - 1), nums(i))
      } else {
        dp(0)(i) = 0
        dp(1)(i) = 0
      }
      result = Math.max(dp(0)(i), result)
    }
    result
  }

  /**
   * Coin Change
   */
  def coinChange(coins: Array[Int], amount: Int): Int = {
    if (amount == 0) 0
    else {
      val dp = Array.ofDim[Int](coins.length, amount + 1)
      for (i <- 1 to amount) {
        dp(0)(i) = if (i % coins(0) == 0) i / coins(0) else -1
      }
      var result = dp(0)(amount)
      for (j <- 1 until coins.length) {
        for (i <- 1 to amount) {
          if (i - coins(j) < 0 || dp(j)(i - coins(j)) < 0) dp(j)(i) = dp(j - 1)(i)
          else if (dp(j - 1)(i) < 0) dp(j)(i) = dp(j)(i - coins(j)) + 1
          else dp(j)(i) = Math.min(dp(j - 1)(i), dp(j)(i - coins(j)) + 1)
        }
        if (result > 0 && dp(j)(amount) > 0) result = Math.min(dp(j)(amount), result)
        else if (result < 0) result = dp(j)(amount)
      }
      result
    }
  }



}
