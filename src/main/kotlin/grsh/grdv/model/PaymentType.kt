package grsh.grdv.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository

@MappedEntity("payment_type")
data class PaymentType(
    @Id
    @GeneratedValue()
    val id: Long,
    val title: String,
    val description: String
)

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  PaymentTypeRepo : PageableRepository<PaymentType, Long> {

}