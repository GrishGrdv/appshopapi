package grsh.grdv.controller.dto

import java.math.BigDecimal

data class GoodSearchDto(
    var goodCategory: List<Long>,
    var name: String?,
    var maxPrice: BigDecimal?,
    var minPrice: BigDecimal?,
    var sortProperty: String = "id",
    var sortDirection: String = "ASC",
    var max: Int,
    var offset: Int
) {
}