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
    fun getGoodCategories(): List<GoodCategoryDto> {
        return goodCategoryRepo.findAll().map { GoodCategoryDto(it.id, it.name, it.description) }
    }

    fun getGoods(categoryId: Long, page: Int, max: Int): List<GoodDto> {
        return goodRepo.findAllByCategory(categoryId, Pageable.from(page, max)).map {
            GoodDto(it.id, it.name, it.description, it.price, categoryId, null)
        }
    }

    /*fun updateGoodCategory(id: Long = -1L, name: String? = null, description: String? = null): GoodCategoryDto {
        val category = goodCategoryRepo.findById(id).orElse(GoodCategory())
        category.name = name ?: category.name
        category.description = description ?: category.description
        return goodCategoryRepo.save(category).let { GoodCategoryDto(it.id, it.name, it.description) }
    }*/

    /*fun updateGood(
        id: Long = -1L,
        name: String? = null,
        description: String? = null,
        price: BigDecimal? = null,
        categoryId: Long? = null,
    ): GoodDto {
        val good = goodRepo.findById(id).orElse(Good())

        good.name = name ?: good.name
        good.description = description ?: good.description
        good.price = price ?: good.price
        if (categoryId != null) {
            good.category = goodCategoryRepo.findById(categoryId).orElse(good.category)
        }

        return goodRepo.save(good).let { GoodDto(it.id, it.name, it.description, it.price, categoryId, null) }
    }*/

    fun deleteGood(id: Long) {
        return goodRepo.deleteById(id)
    }

    fun deleteGoodCategory(id: Long) {
        return goodCategoryRepo.deleteById(id)
    }
}