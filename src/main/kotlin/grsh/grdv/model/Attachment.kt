package grsh.grdv.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "attachment")
data class Attachment(
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long,

    val name: String,

    val path: String,
) {
    companion object {
        @Repository
        interface Repo : CrudRepository<Attachment, Long>
    }
}