package interview.easy.array

object Strings {

  def main(args: Array[String]): Unit = {
    println(isAnagram("aaa", "aaa"))
  }

  def isAnagram(s: String, t: String): Boolean = {

    occurrences(s) == occurrences(t)

  }

  def occurrences(s: String): Map[Char, Int] = {
    s.groupMapReduce(identity)(_ => 1)(_ + _)
  }

}
