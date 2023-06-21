package domain

import java.time.LocalDate

class Product(
  val name: String,
  val category: ProductCategory,
  val weight: Double,
  val price: Double,
  val creationDate: LocalDate
 ) {

  override def toString(): String = {
    return "Product:[" + name + ", " + category + ", " + weight + ", " + price + ", " + creationDate + "]"
  }
}
