package grsh.grdv.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class GoodCategory(
    @Id val id: Long,
    val name: String,
    val description: String
) {
}