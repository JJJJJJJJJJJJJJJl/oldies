import domain.*
import java.time.LocalDate

@main def main(args: String*): Unit = {
  val minLimit = args(0)
  val maxLimit = args(1)
  println("[" + minLimit + ", " + maxLimit + "]")
}