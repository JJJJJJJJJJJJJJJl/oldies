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
    val splitted: Array[String] = if (set contains "-") set.split("-") else Array(set.stripPrefix(">"))
    if (splitted.length.eq(2))
      (productMonthsOld >= splitted.head.toInt  && productMonthsOld <= splitted.tail.head.toInt)
    else splitted.head.toInt < productMonthsOld
  }

  private def mapProductAgeToAgeSet(presentDate: LocalDate, productCreationDate: LocalDate): String = {
    require(productCreationDate.isBefore(presentDate))
    val productMonthsOld: Int = ChronoUnit.MONTHS.between(productCreationDate, presentDate).toInt
    val filteredAgeSets: List[String] = productAgeSets.filter(set => setContainsInt(set, productMonthsOld))
    if (filteredAgeSets.size == 1) filteredAgeSets.head else null
  }

  def calculcateResults(): Map[String, Int] = {
    val presentDate: LocalDate = LocalDate.now()
    var results: Map[String, Int] = Map[String, Int]()
    val filteredOrders: List[Order] = orders.filter(order => {
      order.placementDate.isAfter(orderInterval.head) && order.placementDate.isBefore(orderInterval.tail.head)
    })

    filteredOrders.foreach(order => {
      var ageSetKeyChecker: Map[String, Boolean] = Map[String, Boolean]()
      order.items.foreach(item => {
        val currentKey: String = mapProductAgeToAgeSet(presentDate, item.product.creationDate)
        if (currentKey != null) {
          if(!ageSetKeyChecker.contains(currentKey)) {
            results = if (results.contains(currentKey)) results.updated(currentKey, results(currentKey) + 1) else results + (currentKey -> 1)
            ageSetKeyChecker = if (ageSetKeyChecker.contains(currentKey)) ageSetKeyChecker.updated(currentKey, true) else ageSetKeyChecker + (currentKey -> true)
          }
        }
      })
    })
    results
  }
}
