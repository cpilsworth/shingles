Generates 2-3 term shingles from a list of terms

    val x = List("1", "2", "3", "4", "5")

    Shingles.expand(List("1"))
    // res0: List[String] = List(1)
    Shingles.expand(List("1", "2"))
    // res1: List[String] = List(1, 2, 1 2)
    Shingles.expand(List("1", "2", "3"))
    //res2: List[String] = List(1, 2, 1 2, 3, 2 3, 1 2 3)
    Shingles.expand(List("1", "2", "3", "4"))
    // res3: List[String] = List(1, 2, 1 2, 3, 2 3, 1 2 3, 4, 3 4, 2 3 4)

Filtering these shingles against known patterns might look like this

    val ratsToCats = Map[String, String]("2 3 4" -> "a b c", "3 4 5" -> "x y z")

    Shingles.expand(List("1", "2", "3", "4", "5"))
      .filter(shingle => ratsToCats.contains(shingle)) // List("2 3 4", "3 4 5")
      .map(rat => ratsToCats(rat))
    // res5: List[String] = List(a b c, x y z)
