package utils

import java.time.format.DateTimeFormatter

trait DateUtilsTrait{
  val dateTimeFormatter: DateTimeFormatter
}

object DateUtils extends DateUtilsTrait {
  val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
}