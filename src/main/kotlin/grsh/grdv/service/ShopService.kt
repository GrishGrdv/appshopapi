package grsh.grdv.service

import grsh.grdv.model.Good
import grsh.grdv.model.GoodCategory
import grsh.grdv.model.GoodCategoryRepo
import grsh.grdv.model.GoodRepo
import grsh.grdv.service.dto.GoodCategoryDto
import grsh.grdv.service.dto.GoodDto
import io.micronaut.data.model.Pageable
import jakarta.inject.Singleton
import java.math.BigDecimal

@Singleton
internal class ShopService(
    private val goodRepo: GoodRepo,
    private val goodCategoryRepo: GoodCategoryRepo
) {

    fun getAllCategories(): List<GoodCategoryDto> {
        val categories = HashMap<Long, GoodCategoryDto>()
        goodCategoryRepo.findAll()
            .map { GoodCategoryDto(it.id, it.name, it.description, listOf()) }
            .forEach { categories[it.id!!] = it }

        


        return result
    }
}