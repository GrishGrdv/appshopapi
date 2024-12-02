package grsh.grdv.model

import io.micronaut.data.annotation.*
import io.micronaut.data.model.DataType
import io.micronaut.data.repository.CrudRepository
import java.math.BigDecimal

@MappedEntity("good")
data class Good(
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long,

    val name: String,

    val description: String,

    val price: BigDecimal,

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    val category: GoodCategory,
) {
    companion object {
        @Repository
        interface Repo : CrudRepository<Good, Long?>
    }
}