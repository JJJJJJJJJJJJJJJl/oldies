package domain.business

import domain.entities.{Item, Order}

import java.time.LocalDate
import java.time.temporal.ChronoUnit

/* Receives both the interval and data. Returns orders grouped by product age
**/

class System(
  val orderInterval: List[LocalDate],
  val orders: List[Order],
  val productAgeSets: List[String]
  ) {

  private def setContainsInt(set: String, productMonthsOld: Int): Boolean = {
    val split: Array[String] = set.split("-")
    split match {
      case Array(start, end) => productMonthsOld >= start.toInt && productMonthsOld <= end.toInt
      case Array(start) => start.stripPrefix(">").toInt < productMonthsOld
      case _ => false
    }
  }

  private def mapProductAgeToAgeSet(presentDate: LocalDate, productCreationDate: LocalDate): Option[String] = {
    require(productCreationDate.isBefore(presentDate))
    val productMonthsOld: Int = ChronoUnit.MONTHS.between(productCreationDate, presentDate).toInt
    val filteredAgeSets: List[String] = productAgeSets.filter(set => setContainsInt(set, productMonthsOld))
    filteredAgeSets match {
      case head :: Nil => Some(head)
      case _ => None
    }
  }

  def calculateResults(): Map[String, Int] = {
    val presentDate: LocalDate = LocalDate.now()

    val filteredOrders: List[Order] = orders.filter(order => {
      order.placementDate.isAfter(orderInterval.head) && order.placementDate.isBefore(orderInterval.tail.head)
    })

    val orderKeys = for {
      order <- filteredOrders
      key <- order.items.flatMap(item => mapProductAgeToAgeSet(presentDate, item.product.creationDate)).distinct
    } yield key

    val orderKeysCount = orderKeys.groupBy(identity).view.mapValues(_.size).toMap

    val initialMap = productAgeSets.map(_ -> 0).toMap
    initialMap ++ orderKeysCount
  }
}
