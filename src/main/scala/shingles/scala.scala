package shingles

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