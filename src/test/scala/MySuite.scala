import domain.*
import utils.DateUtils

import java.time.LocalDate

class MySuite extends munit.FunSuite {
  test("Order grand total calculation") {
    val product: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.now())
    val item1: Item = new Item(product, 10, 23)
    val item2: Item = new Item(product, 10, 23)
    val item3: Item = new Item(product, 10, 23)
    val item4: Item = new Item(product, 10, 23)
    val items: List[Item] = List(item1, item2, item3, item4)
    val order: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", items, LocalDate.now())
    assert(order.grandTotal.equals(8281.0))
  }

  test("Order placement date lies inside the given interval"){
    // Simulating comand line arguments
    val minBound: String = "2018-01-01 00:00:00"
    val maxBound: String = "2019-01-01 00:00:00"
    val minBoundDate: LocalDate = LocalDate.parse(minBound, DateUtils.dateTimeFormatter)
    val maxBoundDate: LocalDate = LocalDate.parse(maxBound, DateUtils.dateTimeFormatter)

    val product: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2018-01-10 00:00:00", DateUtils.dateTimeFormatter))
    val item: Item = new Item(product, 10, 23)
    val order: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item), LocalDate.parse("2018-01-10 00:00:00", DateUtils.dateTimeFormatter))

    assert(order.placementDate.isAfter(minBoundDate))
    assert(order.placementDate.isBefore(maxBoundDate))
  }

  test("Order placement date > interval") {
    // Simulating comand line arguments
    val minBound: String = "2018-01-01 00:00:00"
    val maxBound: String = "2019-01-01 00:00:00"
    val minBoundDate: LocalDate = LocalDate.parse(minBound, DateUtils.dateTimeFormatter)
    val maxBoundDate: LocalDate = LocalDate.parse(maxBound, DateUtils.dateTimeFormatter)

    val product: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2018-01-10 00:00:00", DateUtils.dateTimeFormatter))
    val item: Item = new Item(product, 10, 23)
    val order: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item), LocalDate.parse("2023-01-10 00:00:00", DateUtils.dateTimeFormatter))

    assert(order.placementDate.isAfter(minBoundDate))
    assert(order.placementDate.isAfter(maxBoundDate))
  }

  test("Order placement date < interval") {
    // Simulating comand line arguments
    val minBound: String = "2018-01-01 00:00:00"
    val maxBound: String = "2019-01-01 00:00:00"
    val minBoundDate: LocalDate = LocalDate.parse(minBound, DateUtils.dateTimeFormatter)
    val maxBoundDate: LocalDate = LocalDate.parse(maxBound, DateUtils.dateTimeFormatter)

    val product: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2018-01-10 00:00:00", DateUtils.dateTimeFormatter))
    val item: Item = new Item(product, 10, 23)
    val order: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item), LocalDate.parse("2012-01-10 00:00:00", DateUtils.dateTimeFormatter))

    assert(order.placementDate.isBefore(minBoundDate))
    assert(order.placementDate.isBefore(maxBoundDate))
  }
}
