package grsh.grdv.service

import grsh.grdv.controller.dto.GoodSearchDto
import grsh.grdv.model.Good
import grsh.grdv.model.GoodCategory
import grsh.grdv.model.GoodRepo
import io.micronaut.data.runtime.criteria.get
import jakarta.inject.Singleton
import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.Join
import jakarta.persistence.criteria.JoinType
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.metamodel.EntityType
import jakarta.persistence.metamodel.MapAttribute
import jakarta.persistence.metamodel.Metamodel

@Singleton
class GoodService(
    private val goodRepo: GoodRepo,
    private val entityManager: EntityManager
) {

    fun findAllBySearchParameters(searchParameter: GoodSearchDto): List<Good> {
        val builder = entityManager.criteriaBuilder
        val query = builder.createQuery(Good::class.java)
        val _Good: EntityType<Good> = entityManager.metamodel.entity(Good::class.java)
        val _GoodCategory: EntityType<GoodCategory> = entityManager.metamodel.entity(GoodCategory::class.java)
        val root = query.from(Good::class.java)

        val predicates: MutableList<Predicate> = mutableListOf()
        if (searchParameter.name.isNullOrBlank()) {
            predicates.add(builder.and(builder.or(
                builder.like(builder.lower(root.get("name")), "%${searchParameter.name}%"),
                builder.like(builder.lower(root.get("description")), "%${searchParameter.name}%"),
            )))
        }

        if (searchParameter.goodCategory.isNotEmpty()) {
            val category: Join<Good, GoodCategory> = root.join("category")

            predicates.add(builder.and(
                builder.`in`(category.get(_GoodCategory.getDeclaredMap("id")).`in`(searchParameter.goodCategory)),
            ))
        }

        if (searchParameter.maxPrice != null) {
            predicates.add(builder.and(
                builder.le(root.get("price"), searchParameter.maxPrice),
            ))
        }

        if (searchParameter.minPrice != null) {
            predicates.add(builder.and(
                builder.ge(root.get("price"), searchParameter.minPrice),
            ))
        }

        query.select(root)
            .where(*predicates.map { it }.toTypedArray())
            .orderBy(if (searchParameter.sortDirection == "ASC")
                builder.asc(root.get(_Good.getDeclaredMap(searchParameter.sortProperty))) else
                builder.desc(root.get(_Good.getDeclaredMap(searchParameter.sortProperty)))
            )

        return entityManager.createQuery(query).setFirstResult(searchParameter.offset).setMaxResults(searchParameter.max).resultList
    }
}