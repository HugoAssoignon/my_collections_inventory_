data class MangaDTO(
    val id: Int = 0,
    val title: String = "",
    val imageUrl: String = "",
    val author: String = "",
    val status: String = "",
    val description: String = "",
    val category: String = "",
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MangaDTO

        if (id != other.id) return false
        if (title != other.title) return false
        if (imageUrl != other.imageUrl) return false
        if (author != other.author) return false
        if (status != other.status) return false
        if (description != other.description) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }
}
