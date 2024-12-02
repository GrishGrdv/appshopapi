package grsh.grdv.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import jakarta.persistence.GenerationType

@MappedEntity("payment_type")
data class PaymentType(
    @Id
    @GeneratedValue()
    val id: Long,
    val title: String,
    val description: String
) {
    companion object {
        @Repository
        interface Repo : CrudRepository<PaymentType, Long?>
    }
}