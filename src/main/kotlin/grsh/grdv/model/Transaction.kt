package grsh.grdv.model

import io.micronaut.data.annotation.*
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.DataType
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import java.math.BigDecimal
import java.time.LocalDateTime
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@MappedEntity("transaction")
data class Transaction(
    @field:Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long,

    @DateCreated
    var createdAt: LocalDateTime,

    @DateUpdated
    var updatedAt: LocalDateTime,

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