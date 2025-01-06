package grsh.grdv.controller

import grsh.grdv.controller.dto.GoodCategoryDto
import grsh.grdv.model.GoodCategory
import grsh.grdv.model.GoodCategoryRepo
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import jakarta.annotation.security.PermitAll
import io.micronaut.data.jpa.repository.criteria.Specification

@PermitAll
@Controller("/good-categories")
class GoodCategoriesController(
    private val goodCategoryRepo: GoodCategoryRepo
) {
    @Get("/{id}")
    fun getById(@PathVariable id: Long): HttpResponse<GoodCategoryDto> {
        return goodCategoryRepo.findById(id).map { HttpResponse.ok(
            GoodCategoryDto(it.id, it.name, it.description, it.parentGoodCategory)
        )}.orElseGet { HttpResponse.notFound() }
    }

    @Get
    fun getAll(): HttpResponse<List<GoodCategoryDto>> {
        return goodCategoryRepo.findAllByParentGoodCategoryIsNull().map {
            GoodCategoryDto(it.id, it.name, it.description, it.parentGoodCategory)
        }.let { HttpResponse.ok(it) }
    }

    @Get("/{parentId}")
    fun getAll(@PathVariable parentId: Long): HttpResponse<List<GoodCategoryDto>> {
        return goodCategoryRepo.findAllByParentGoodCategory(parentId).map {
            GoodCategoryDto(it.id, it.name, it.description, it.parentGoodCategory)
        }.let { HttpResponse.ok(it) }
    }

    @Post
    fun create(@Body dto: GoodCategoryDto): HttpResponse<GoodCategoryDto> {
        return goodCategoryRepo.save(GoodCategory(
            dto.id ?: 0L, dto.name, dto.description, dto.parentGoodCategory
        )).let { HttpResponse.ok(
            GoodCategoryDto(it.id, it.name, it.description, it.parentGoodCategory)
        )}
    }

    @Patch
    fun update(@Body dto: GoodCategoryDto): HttpResponse<GoodCategoryDto> {
        return goodCategoryRepo.update(GoodCategory(
            dto.id!!, dto.name, dto.description, dto.parentGoodCategory
        )).let { HttpResponse.ok(
            GoodCategoryDto(it.id, it.name, it.description, it.parentGoodCategory)
        )}
    }

    @Delete("/{id}")
    fun delete(@PathVariable id: Long): HttpResponse<Boolean> {
        val goodCategory = goodCategoryRepo.findById(id)
        if (goodCategory.isEmpty) {
            return HttpResponse.notFound()
        }

        if (goodCategoryRepo.countByParentGoodCategory(goodCategory.get().parentGoodCategory) == 0L) {
            return HttpResponse.status(HttpStatus.NO_CONTENT, "Другие категории ссылаются на удаляемую")
        }
        CriteriaBuilder
        goodCategoryRepo.delete(goodCategory.get())
        return HttpResponse.ok()
    }
}
