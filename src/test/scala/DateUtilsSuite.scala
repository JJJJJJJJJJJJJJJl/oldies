import domain.entities.*
import domain.entities.{Item, Order}
import utils.DateUtils

import java.time.LocalDate

class DateUtilsSuite extends munit.FunSuite{
  test("Order placement date lies inside the given interval") {
    val minOrderBound: String = "2018-01-01 00:00:00"
    val maxOrderBound: String = "2019-01-01 00:00:00"
    val minOrderBoundDate: LocalDate = LocalDate.parse(minOrderBound, DateUtils.dateTimeFormatter)
    val maxOrderBoundDate: LocalDate = LocalDate.parse(maxOrderBound, DateUtils.dateTimeFormatter)

    val product: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2018-01-10 00:00:00", DateUtils.dateTimeFormatter))
    val item: Item = new Item(product, 10, 23)
    val order: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item), LocalDate.parse("2018-01-10 00:00:00", DateUtils.dateTimeFormatter))

    assert(order.placementDate.isAfter(minOrderBoundDate))
    assert(order.placementDate.isBefore(maxOrderBoundDate))
  }

  test("Order placement date > interval") {
    val minOrderBound: String = "2018-01-01 00:00:00"
    val maxOrderBound: String = "2019-01-01 00:00:00"
    val minOrderBoundDate: LocalDate = LocalDate.parse(minOrderBound, DateUtils.dateTimeFormatter)
    val maxOrderBoundDate: LocalDate = LocalDate.parse(maxOrderBound, DateUtils.dateTimeFormatter)

    val product: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2018-01-10 00:00:00", DateUtils.dateTimeFormatter))
    val item: Item = new Item(product, 10, 23)
    val order: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item), LocalDate.parse("2023-01-10 00:00:00", DateUtils.dateTimeFormatter))

    assert(order.placementDate.isAfter(minOrderBoundDate))
    assert(order.placementDate.isAfter(maxOrderBoundDate))
  }

  test("Order placement date < interval") {
    val minOrderBound: String = "2018-01-01 00:00:00"
    val maxOrderBound: String = "2019-01-01 00:00:00"
    val minOrderBoundDate: LocalDate = LocalDate.parse(minOrderBound, DateUtils.dateTimeFormatter)
    val maxOrderBoundDate: LocalDate = LocalDate.parse(maxOrderBound, DateUtils.dateTimeFormatter)

    val product: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2018-01-10 00:00:00", DateUtils.dateTimeFormatter))
    val item: Item = new Item(product, 10, 23)
    val order: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item), LocalDate.parse("2012-01-10 00:00:00", DateUtils.dateTimeFormatter))

    assert(order.placementDate.isBefore(minOrderBoundDate))
    assert(order.placementDate.isBefore(maxOrderBoundDate))
  }
}
