import domain.entities.*
import domain.business.System
import utils.DateUtils

import java.time.LocalDate

class SystemSuite extends munit.FunSuite {
  test("System -- (1-3, 4-6, 7-12, >12)") {
    val minOrderBound: LocalDate = LocalDate.parse("2018-01-01 00:00:00", DateUtils.dateTimeFormatter)
    val maxOrderBound: LocalDate = LocalDate.now()

    val productAgeSets: List[String] = List("1-3", "4-6", "7-12", ">12")

    val product1: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2023-05-01 00:00:00", DateUtils.dateTimeFormatter))
    val product2: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2023-02-01 00:00:00", DateUtils.dateTimeFormatter))
    val product3: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2022-09-01 00:00:00", DateUtils.dateTimeFormatter))
    val product4: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2021-01-01 00:00:00", DateUtils.dateTimeFormatter))

    val item1: Item = new Item(product1, 10, 23)
    val item2: Item = new Item(product2, 10, 23)
    val item3: Item = new Item(product3, 10, 23)
    val item4: Item = new Item(product4, 10, 23)

    val order1: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.now())
    val order2: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2019-01-01 00:00:00", DateUtils.dateTimeFormatter))
    val order3: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2019-05-01 00:00:00", DateUtils.dateTimeFormatter))
    val order4: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2017-10-13 00:00:00", DateUtils.dateTimeFormatter))

    val system: System = new System(List(minOrderBound, maxOrderBound), List(order1, order2, order3, order4), productAgeSets)

    val results: Map[String, Int] = system.calculcateResults()
    assert(results.size.eq(4))
    for ((k,v) <- results) assert(v.eq(2))
  }

  test("System -- (1-6, 7-12, >12)") {
    val minOrderBound: LocalDate = LocalDate.parse("2018-01-01 00:00:00", DateUtils.dateTimeFormatter)
    val maxOrderBound: LocalDate = LocalDate.now()

    val productAgeSets: List[String] = List("1-6", "7-12", ">12")

    val product1: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2023-05-01 00:00:00", DateUtils.dateTimeFormatter))
    val product2: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2023-02-01 00:00:00", DateUtils.dateTimeFormatter))
    val product3: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2022-09-01 00:00:00", DateUtils.dateTimeFormatter))
    val product4: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2021-01-01 00:00:00", DateUtils.dateTimeFormatter))

    val item1: Item = new Item(product1, 10, 23)
    val item2: Item = new Item(product2, 10, 23)
    val item3: Item = new Item(product3, 10, 23)
    val item4: Item = new Item(product4, 10, 23)

    val order1: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.now())
    val order2: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2019-01-01 00:00:00", DateUtils.dateTimeFormatter))
    val order3: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2019-05-01 00:00:00", DateUtils.dateTimeFormatter))
    val order4: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2017-10-13 00:00:00", DateUtils.dateTimeFormatter))

    val system: System = new System(List(minOrderBound, maxOrderBound), List(order1, order2, order3, order4), productAgeSets)

    val results: Map[String, Int] = system.calculcateResults()
    assert(results.size.eq(3))
    assert(results("1-6").eq(4))
    assert(results("7-12").eq(2))
    assert(results(">12").eq(2))
  }

  test("System -- (1-12, >24)") {
    val minOrderBound: LocalDate = LocalDate.parse("2018-01-01 00:00:00", DateUtils.dateTimeFormatter)
    val maxOrderBound: LocalDate = LocalDate.now()

    val productAgeSets: List[String] = List("1-12", ">24")

    val product1: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2023-05-01 00:00:00", DateUtils.dateTimeFormatter))
    val product2: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2023-02-01 00:00:00", DateUtils.dateTimeFormatter))
    val product3: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2022-09-01 00:00:00", DateUtils.dateTimeFormatter))
    val product4: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.parse("2021-11-01 00:00:00", DateUtils.dateTimeFormatter))

    val item1: Item = new Item(product1, 10, 23)
    val item2: Item = new Item(product2, 10, 23)
    val item3: Item = new Item(product3, 10, 23)
    val item4: Item = new Item(product4, 10, 23)

    val order1: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.now())
    val order2: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2019-01-01 00:00:00", DateUtils.dateTimeFormatter))
    val order3: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2019-05-01 00:00:00", DateUtils.dateTimeFormatter))
    val order4: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", List(item1, item2, item3, item4), LocalDate.parse("2017-10-13 00:00:00", DateUtils.dateTimeFormatter))

    val system: System = new System(List(minOrderBound, maxOrderBound), List(order1, order2, order3, order4), productAgeSets)

    val results: Map[String, Int] = system.calculcateResults()
    assert(results.size.eq(1))
    assert(results("1-12").eq(6))
    assert(!results.contains(">24"))
  }
}
