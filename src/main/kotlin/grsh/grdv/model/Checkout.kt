package grsh.grdv.model

import io.micronaut.data.annotation.*
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.DataType
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@MappedEntity("checkout")
data class Checkout(
    @field:Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
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
)

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  CheckoutRepo : PageableRepository<Checkout, Long> {

}