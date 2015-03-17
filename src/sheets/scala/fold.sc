val x = List("1", "2", "3", "4", "5")

object Shingles {

  private def cat(a: String, b: String) = a + " " + b

  private def cat(a: String, b: String, c: String) = a + " " + b + " " + c

  private def triple(items: List[String]): List[List[String]] = items match {
    case a :: b :: c :: Nil => List(c, cat(b, c), cat(a, b, c)) :: Nil
    case a :: b :: c :: tail => List(c, cat(b, c), cat(a, b, c)) :: Nil ::: triple(b :: c :: tail)
  }

  def combinations(items: List[String]): List[List[String]] = items match {
    case a :: Nil => List(a) :: Nil
    case a :: b :: Nil => List(a) :: List(b, cat(a, b)) :: Nil
    case a :: b :: c => List(a) :: List(b, cat(a, b)) :: triple(items)
  }

  def expand(terms: List[String]) = combinations(terms).flatMap(i => i)

}

Shingles.expand(List("1"))
// res0: List[String] = List(1)
Shingles.expand(List("1", "2"))
// res1: List[String] = List(1, 2, 1 2)
Shingles.expand(List("1", "2", "3"))
//res2: List[String] = List(1, 2, 1 2, 3, 2 3, 1 2 3)
Shingles.expand(List("1", "2", "3", "4"))
// res3: List[String] = List(1, 2, 1 2, 3, 2 3, 1 2 3, 4, 3 4, 2 3 4)


val ratsToCats = Map[String, String]("2 3 4" -> "a b c", "3 4 5" -> "x y z")

Shingles.expand(List("1", "2", "3", "4", "5"))
  .filter(shingle => ratsToCats.contains(shingle)) // List("2 3 4", "3 4 5")
  .map(rat => ratsToCats(rat))
// res5: List[String] = List(a b c, x y z)


val words = """([A-Za-z])+""".r
val description = "red cashmere sweater with black ribbon detailing"

val typeRatsToCats = Map[String, String]("cashmere sweater" -> "cashmere jumper", "ribbon detailing" -> "ribbon detail")
val terms = words.findAllIn(description).toList
Shingles.expand(terms)
  .filter(shingle => typeRatsToCats.contains(shingle))
  .map(shingle => typeRatsToCats(shingle))
//res5: List[String] = List(cashmere jumper, ribbon detail)