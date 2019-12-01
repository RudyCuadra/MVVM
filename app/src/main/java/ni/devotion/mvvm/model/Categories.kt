package ni.devotion.mvvm.model

data class Categories(
    val createdAt: String,
    val id: String,
    val nombre: String,
    val updatedAt: String,
    val imagenes: List<String>
)