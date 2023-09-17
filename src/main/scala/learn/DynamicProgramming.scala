package learn

object DynamicProgramming {

  def main(args: Array[String]): Unit = {
    println(deleteAndEarn(Array(1, 1, 1, 2, 4, 5, 5, 5, 6)))
  }

  /**
   * House Robber
   */
  def rob(nums: Array[Int]): Int = {
    if (nums.length == 1) nums(0)
    else if (nums.length == 2) nums.max
    else {
      val acc = new Array[Int](nums.length)
      acc(0) = nums(0)
      acc(1) = Math.max(nums(0), nums(1))
      for (i <- 2 until nums.length) {
        acc(i) = Math.max(acc(i - 2) + nums(i), acc(i - 1))
      }
      acc.last
    }
  }

  def robRecursive(nums: Array[Int]): Int = {
    def helper(beforePrev: Int, prev: Int, nums: Array[Int]): Int = {
      if (nums.isEmpty) Math.max(beforePrev, prev)
      else {
        helper(prev, Math.max(beforePrev + nums.head, prev), nums.tail)
      }
    }

    if (nums.length == 1) nums(0)
    else if (nums.length == 2) nums.max
    else helper(nums(0), Math.max(nums(0), nums(1)), nums.tail.tail)
  }


  def minCostClimbingStairs(cost: Array[Int]): Int = {
    if (cost.length == 1) cost(0)
    else if (cost.length == 2) Math.min(cost(0), cost(1))
    else {
      val acc = new Array[Int](cost.length + 1)
      for (i <- 2 to cost.length) {
        acc(i) = Math.min(acc(i - 2) + cost(i - 2), acc(i - 1) + cost(i - 1))
      }
      acc.last
    }
  }

  def tribonacci(n: Int): Int = {
    def helper(zero: Int, one: Int, two: Int, n: Int): Int = {
      if (n == 0) zero
      else if (n < 3) one
      else if (n == 3) zero + one + two
      else helper(one, two, zero + one + two, n - 1)
    }

    helper(0, 1, 1, n)
  }

  def deleteAndEarn(nums: Array[Int]): Int = {

    val sorted = nums.sorted
    val memo = new Array[Int](nums.length)

    def dp(i: Int): Int = {
      if (i == sorted.length) 0
      else {
        if (memo(i) == 0) {
          var equal = i + 1
          while (equal < sorted.length && sorted(equal) == sorted(i)) {
            equal += 1
          }
          var plusOne = equal
          while (plusOne < sorted.length && sorted(plusOne) == sorted(i) + 1) {
            plusOne += 1
          }
          memo(i) = Math.max(sorted(i) * (equal - i) + dp(plusOne), dp(equal))
        }
        memo(i)
      }
    }

    dp(0)
  }
}
