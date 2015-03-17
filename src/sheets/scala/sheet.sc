import java.io.{FileInputStream, ByteArrayInputStream, InputStreamReader, InputStream}
import java.nio.charset.StandardCharsets
import com.github.tototoshi.csv._


type RawProduct = Map[String,String]


trait RawProductSupplier {
  def getProducts() : Stream[RawProduct];
}

class CsvRawProductSupplier(is: InputStream) extends RawProductSupplier {
  val input : InputStream = is

  override def getProducts(): Stream[RawProduct] = {
    val isr = new InputStreamReader(input)
    val reader = CSVReader.open(isr)
    reader.allWithHeaders().toStream
  }
}

val csv = "a,b,c\n1,2,3\n8,9,10\nx,y,z"
//val csvFile = new FileInputStream("file.csv")

val stream = new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8))
val reader : RawProductSupplier = new CsvRawProductSupplier(stream)
val products : Stream[RawProduct] = reader.getProducts()

val c = products.map( p => p("a")).foreach(println)
