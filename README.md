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