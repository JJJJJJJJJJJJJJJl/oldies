package domain.business

import domain.entities.{Item, Order}

import java.time.{LocalDate, Period}

/* Receives both the interval and data. Returns orders based on product age (“1-3”, “4-6”, “7-12”, “>12”)
*
*  TODO: Take arbitrary product age sets
* */

class System(
  val orderInterval: List[LocalDate],
  val orders: List[Order],
  val productAgeSets: List[String] = List("1-3", "4-6", "7-12", ">12")
  ) {

  def setContainsInt(set: String, productMonthsOld: Int): Boolean = {
    val splitted: Array[String] = if (set contains "-") set.split("-") else Array(set.stripPrefix(">"))
    splitted.exists(s => (s.toInt.equals(productMonthsOld)))
  }

  def mapProductAgeToAgeSet(presentDate: LocalDate, productCreationDate: LocalDate): String = {
    require(productCreationDate.isBefore(presentDate))
    val productMonthsOld: Int = Period.between(productCreationDate, presentDate).getMonths
    val filteredAgeSets: List[String] = productAgeSets.filter(set => setContainsInt(set, productMonthsOld))
    filteredAgeSets.head
  }

  def calculcateResults(): Map[String, Int] = {
    val presentDate: LocalDate = LocalDate.now()
    var results: Map[String, Int] = Map[String, Int]()
    val filteredOrders: List[Order] = orders.filter(order => order.placementDate.isAfter(orderInterval.head) && order.placementDate.isBefore(orderInterval.tail.head))
    filteredOrders.foreach(order => order.items.foreach(item => {
      val currentKey:String = mapProductAgeToAgeSet(presentDate, item.product.creationDate)
      val currentValue = results(currentKey)
      results = results.updated(currentKey, currentValue + 1)
    }))
    results
  }

}
