import domain.*

import java.time.LocalDate

class MySuite extends munit.FunSuite {
  test("Test order grand total calculation") {
    val product: Product = new Product("Chrome Hearts Tumbler", ProductCategory.Tops, 0.5, 1675, LocalDate.now())
    val item1: Item = new Item(product, 10, 23)
    val item2: Item = new Item(product, 10, 23)
    val item3: Item = new Item(product, 10, 23)
    val item4: Item = new Item(product, 10, 23)
    val items: List[Item] = List(item1, item2, item3, item4)
    val order: Order = new Order("JJJJJJ", "999666777", "(666, Panopticon Street, Hell)", items, LocalDate.now())
    assert(order.grandTotal.equals(8281.0))
  }
}
