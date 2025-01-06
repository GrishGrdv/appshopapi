package grsh.grdv.controller.dto

import java.math.BigDecimal

data class GoodDto(
    var id: Long?,
    var name: String,
    var description: String,
    var price: BigDecimal,
    var category: Long?,
) {
}