package ni.devotion.mvvm.model

data class Data(
    val createdAt: String,
    val descripcion: String,
    val direccion: String,
    val id: String,
    val imagenes: List<String>,
    val localizacion: List<String>,
    val nombre: String,
    val telefonos: List<String>,
    val updatedAt: String
)