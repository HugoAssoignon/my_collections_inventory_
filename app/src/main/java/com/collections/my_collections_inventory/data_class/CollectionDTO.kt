data class CollectionDTO(
    val idUser: Int,
    val idManga: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CollectionDTO

        if (idUser != other.idUser) return false
        if (idManga != other.idManga) return false
        return true
    }

    override fun hashCode(): Int {
        var result = idUser
        return result
    }
}
