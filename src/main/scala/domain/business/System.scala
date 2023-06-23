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

  def setContainsInt(set: String, productMonthsOld: Int): Boolean = {
    val splitted: Array[String] = if (set contains "-") set.split("-") else Array(set.stripPrefix(">"))
    if (splitted.length.eq(2))
      splitted.head.toInt.eq(productMonthsOld) || splitted.tail.head.toInt.eq(productMonthsOld)
      || (productMonthsOld > splitted.head.toInt  && productMonthsOld < splitted.tail.head.toInt)
    else splitted.head.toInt.eq(productMonthsOld) || splitted.head.toInt < productMonthsOld
  }

  def mapProductAgeToAgeSet(presentDate: LocalDate, productCreationDate: LocalDate): String = {
    require(productCreationDate.isBefore(presentDate))
    val productMonthsOld: Int = ChronoUnit.MONTHS.between(productCreationDate, presentDate).toInt
    val filteredAgeSets: List[String] = productAgeSets.filter(set => setContainsInt(set, productMonthsOld))
    filteredAgeSets.head
  }

  def calculcateResults(): Map[String, Int] = {
    val presentDate: LocalDate = LocalDate.now()
    var results: Map[String, Int] = Map[String, Int]()
    val filteredOrders: List[Order] = orders.filter(order => {
      order.placementDate.isAfter(orderInterval.head) && order.placementDate.isBefore(orderInterval.tail.head)
    })
    filteredOrders.foreach(order => {
      order.items.foreach(item => {
        val currentKey: String = mapProductAgeToAgeSet(presentDate, item.product.creationDate)
        results = if (results.contains(currentKey)) results.updated(currentKey, results(currentKey) + 1) else results + (currentKey -> 1)
      })
    })
    results
  }

}
