package grsh.grdv.model

import io.micronaut.data.annotation.*
import io.micronaut.data.repository.CrudRepository
import io.micronaut.serde.annotation.Serdeable.Deserializable

@Deserializable
@MappedEntity("recipient")
data class Recipient(
    @Id
    @GeneratedValue()
    val id: Long,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val address: String,
    val zipCode: String,
    val phone: String,
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    val userInfo: UserInfo,
) {
    companion object {
        @Repository
        interface Repo : CrudRepository<Recipient, Long?>
    }
}