package grsh.grdv.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.TypeDef
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.DataType
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import java.math.BigDecimal
import java.time.LocalDateTime

@MappedEntity("transaction")
data class Transaction(
    @Id
    @GeneratedValue
    val id: Long,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,

    @field:TypeDef(type = DataType.STRING)
    val status: TransactionStatus,

    val amount: BigDecimal,

    @field:TypeDef(type = DataType.JSON)
    val providerData: Map<String, Any>,
) {
    companion object {
        enum class TransactionStatus {
            PENDING,
            SUCCESS,
            ERROR
        }
    }
}

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  TransactionRepo : PageableRepository<Transaction, Long> {

}