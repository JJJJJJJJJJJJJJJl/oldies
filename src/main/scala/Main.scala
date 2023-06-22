import domain.*
import utils.DateUtils

import java.time.{LocalDate, ZoneId}

@main def main(args: String*): Unit = {
  val minOrderBound: LocalDate = LocalDate.parse(args(0), DateUtils.dateTimeFormatter)
  val maxOrderBound: LocalDate = LocalDate.parse(args(1), DateUtils.dateTimeFormatter)

  /* TODO: Generate/Grab data
  * */

  /* TODO: Calculate results
  * */
}