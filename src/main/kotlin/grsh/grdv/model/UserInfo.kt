package grsh.grdv.model

import io.micronaut.data.annotation.*
import io.micronaut.data.repository.CrudRepository

@MappedEntity("user_info")
data class UserInfo(
    @Id
    @GeneratedValue
    val id: Long,
    val name: String,
    val email: String,
    val password: String,

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "recipient")
    val recipient: List<Recipient>,
) {
    companion object {
        @Repository
        interface Repo : CrudRepository<UserInfo, Long?>
    }

}