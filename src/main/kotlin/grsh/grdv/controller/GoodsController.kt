package grsh.grdv.controller

import grsh.grdv.controller.dto.GoodCategoryDto
import grsh.grdv.controller.dto.GoodDto
import grsh.grdv.controller.dto.GoodSearchDto
import grsh.grdv.model.Good
import grsh.grdv.model.GoodRepo
import grsh.grdv.service.GoodService
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import jakarta.annotation.security.PermitAll

@PermitAll
@Controller("/goods")
class GoodsController(
    private val goodService: GoodService,
    private val goodRepo: GoodRepo
) {
    @Get("/{id}")
    fun getByGoodId(@PathVariable id: Long): HttpResponse<GoodDto> {
        return goodRepo.findById(id).map { HttpResponse.ok(
            GoodDto(it.id, it.name, it.description, it.price, it.category?.id)
        )}.orElseGet { HttpResponse.notFound() }
    }

    @Get()
    fun findAll(@Body searchDto: GoodSearchDto): HttpResponse<List<GoodDto>> {
        return goodService.findAllBySearchParameters(searchDto).map {
            GoodDto(it.id, it.name, it.description, it.price, it.category?.id)
        }.let { HttpResponse.ok(it) }
    }
}