package grsh.grdv.controller.dto

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class GoodCategoryDto(
    var id: Long?,

    var name: String = "",

    var description: String = "",

    var parentGoodCategory: Long?,
) {
}