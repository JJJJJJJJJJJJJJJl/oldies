import domain.business.System
import domain.entities.{ProductCategory, Product, Item, Order}
import utils.DateUtils

import java.time.{LocalDate, ZoneId}

@main def main(args: String*): Unit = {
  /* Extract and parse CL arguments
  * */
  val minOrderBound: LocalDate = LocalDate.parse(args(0), DateUtils.dateTimeFormatter)
  val maxOrderBound: LocalDate = LocalDate.parse(args(1), DateUtils.dateTimeFormatter)
  val productAgeSets : List[String] = args.drop(2).toList

  /* Generate/Grab data
  * */
  val product1: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Accessories, 0.5, 1675, LocalDate.parse("2023-01-05 00:00:00", DateUtils.dateTimeFormatter))
  val product2: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Accessories, 0.5, 1675, LocalDate.parse("2023-02-01 00:00:00", DateUtils.dateTimeFormatter))
  val product3: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Accessories, 0.5, 1675, LocalDate.parse("2022-09-01 00:00:00", DateUtils.dateTimeFormatter))
  val product4: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Accessories, 0.5, 1675, LocalDate.parse("2024-01-01 00:00:00", DateUtils.dateTimeFormatter))

  val item1: Item = new Item(product1, 10, 23)
  val item2: Item = new Item(product2, 10, 23)
  val item3: Item = new Item(product3, 10, 23)
  val item4: Item = new Item(product4, 10, 23)

  val order1: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.now())
  val order2: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2019-01-01 00:00:00", DateUtils.dateTimeFormatter))
  val order3: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2019-05-01 00:00:00", DateUtils.dateTimeFormatter))
  val order4: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2017-10-13 00:00:00", DateUtils.dateTimeFormatter))

  /* Instantiate System and Calculate results
  * */
  val system: System = new System(List(minOrderBound, maxOrderBound), List(order1, order2, order3, order4), productAgeSets)
  val results: Map[String, Int] = system.calculateResults()
  for ((k,v) <- results) println(k + ": " + v)
}