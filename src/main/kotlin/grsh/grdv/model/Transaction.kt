package grsh.grdv.model

import io.micronaut.data.annotation.*
import io.micronaut.data.model.DataType
import io.micronaut.data.repository.CrudRepository
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

        @Repository
        interface Repo : CrudRepository<Transaction, Long?>
    }
}