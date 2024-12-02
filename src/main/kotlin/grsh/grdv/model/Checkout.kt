package grsh.grdv.model

import grsh.grdv.model.UserBasket.Companion.GoodCount
import io.micronaut.data.annotation.*
import io.micronaut.data.model.DataType
import io.micronaut.data.repository.CrudRepository
import jakarta.persistence.ManyToOne

@MappedEntity("checkout")
data class Checkout(
    @Id
    @GeneratedValue
    val id: Long,

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    val userInfo: UserInfo,

    @Relation(value = Relation.Kind.ONE_TO_ONE)
    val transaction: Transaction,

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    val paymentType: PaymentType,

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    val deliveryType: DeliveryType,

    @field:TypeDef(type = DataType.JSON)
    val basket: UserBasket,

    @field:TypeDef(type = DataType.JSON)
    val recipient: Recipient
) {
    companion object {
        @Repository
        interface Repo : CrudRepository<Checkout, Long>
    }
}