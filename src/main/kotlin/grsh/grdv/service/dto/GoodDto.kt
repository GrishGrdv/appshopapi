package grsh.grdv.service.dto

import grsh.grdv.model.GoodCategory
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.Relation
import java.math.BigDecimal

data class GoodDto(
    var id: Long?,
    var name: String,
    var description: String,
    var price: BigDecimal,
    var category: Long?,
    var attachmentId: Long?
) {
}