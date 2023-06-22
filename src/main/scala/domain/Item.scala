package domain

class Item(
  val product: Product,
  val shippingFee: Double,
  val taxAmount: Double
  ) {

  lazy val cost: Double = product.price + product.price * taxAmount/100 + shippingFee

  override def toString(): String = {
    return "Item:[" + cost + ", " + shippingFee + ", " + taxAmount + "]"
  }
}
