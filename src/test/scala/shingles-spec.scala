import collection.immutable.List
import org.scalatest.FlatSpec;
import org.scalatest._
import shingles.Shingles;

class ShinglesSpec extends FlatSpec with Matchers {

  "Shingles" should "return a single ngram for a single termpop values in last-in-first-out order" in {
    Shingles.expand(List("1")) shouldEqual List("1")
  }

  it should "should return two term ngram for two input terms" in {
    Shingles.expand(List("1", "2")) shouldEqual
      List("1",
           "2", "1 2")
  }

  it should "should return three term ngram for three input terms" in {
    Shingles.expand(List("1", "2", "3")) shouldEqual
      List("1",
           "2", "1 2",
           "3", "2 3", "1 2 3")
  }

  it should "should still only return three term ngram for four input terms" in {
    Shingles.expand(List("1", "2", "3", "4")) shouldEqual
      List("1",
           "2", "1 2",
           "3", "2 3", "1 2 3",
           "4", "3 4", "2 3 4")

  }
}