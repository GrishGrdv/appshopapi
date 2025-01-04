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
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@MappedEntity("user_basket")
data class UserBasket(
    @field:Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long,

    val userId: Long,

    @field:TypeDef(type = DataType.JSON)
    val goods: List<Long>
) {
    companion object {
        @Serdeable
        data class GoodCount(
            val goodId: Long,
            val count: Int,
        )
    }
}

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  UserBasketRepo : PageableRepository<UserBasket, Long> {

}