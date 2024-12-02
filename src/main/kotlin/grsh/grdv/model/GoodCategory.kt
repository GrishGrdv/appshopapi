package grsh.grdv.model

import io.micronaut.data.annotation.*
import io.micronaut.data.repository.CrudRepository

@MappedEntity("good_category")
data class GoodCategory(
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long,

    val name: String,

    val description: String,

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "good_category")
    val goods: List<Good>,
) {
    companion object {
        @Repository
        interface Repo : CrudRepository<GoodCategory, Long?>
    }
}