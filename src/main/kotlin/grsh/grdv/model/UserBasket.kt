package grsh.grdv.model

import io.micronaut.data.annotation.*
import io.micronaut.data.model.DataType
import io.micronaut.data.repository.CrudRepository
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@MappedEntity("user_basket")
data class UserBasket(
    @Id
    @GeneratedValue
    val id: Long,

    val userId: Long,

    @field:TypeDef(type = DataType.JSON)
    val goods: List<GoodCount>
) {
    companion object {
        @Serdeable
        data class GoodCount(
            val goodId: Long,
            val count: Int,
        )

        @Repository
        interface Repo : CrudRepository<UserBasket, Long?>
    }
}

