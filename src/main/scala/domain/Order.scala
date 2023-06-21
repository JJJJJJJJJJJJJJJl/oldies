package domain

import java.time.LocalDate

class Order(
  val customerName: String,
  val contact: String,
  val shippingAddress: String,
  val items: List[Item],
  val placementDate: LocalDate
  ) {

  lazy val grandTotal: Double = items.map(item => item.cost).sum[Double]

  override def toString(): String = {
    return "Order:[" + customerName + ", " + contact + ", " + shippingAddress + ", " + grandTotal + ", " + placementDate + "]"
  }
}
