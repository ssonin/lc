package interview.easy.array

object RemoveDuplicates {

  def main(args: Array[String]): Unit = {
    val nums = Array(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
    val k = removeDuplicates(nums)
    println(nums.mkString(", "))
    println(k)


    val ints = Array(1, 2, 3, 1)
    println(containsDuplicate(ints))
    println("anagram".groupMapReduce(ch => ch)(_ => 1)(_ + _))
  }

  def removeDuplicates(nums: Array[Int]): Int = {
    if (nums.length < 2) nums.length
    else {
      var k = 0
      for (i <- 1 until nums.length) {
        if (nums(i) != nums(k)) {
          k += 1
          swap(nums, i, k)
        }
      }
      k + 1
    }
  }

  def swap(arr: Array[Int], i: Int, j: Int): Unit = {
    if (i != j) {
      arr(i) = arr(i) + arr(j)
      arr(j) = arr(i) - arr(j)
      arr(i) = arr(i) - arr(j)
    }
  }

  def containsDuplicate(nums: Array[Int]): Boolean = {
    nums.length != nums.toSet.size
  }
}
