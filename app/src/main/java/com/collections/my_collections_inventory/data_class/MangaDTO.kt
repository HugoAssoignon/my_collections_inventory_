data class MangaDTO(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val authors: String,
    val status: String,
    val description: String,
    val categories: String,
    val nbBooks: Int = 0,
    val note: Float = 0.0f
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MangaDTO

        if (id != other.id) return false
        if (title != other.title) return false
        if (imageUrl != other.imageUrl) return false
        if (authors != other.authors) return false
        if (status != other.status) return false
        if (nbBooks != other.nbBooks) return false
        if (description != other.description) return false
        if (categories != other.categories) return false
        if (note != other.note) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + authors.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + nbBooks
        result = 31 * result + description.hashCode()
        result = 31 * result + categories.hashCode()
        result = 31 * result + note.hashCode()
        return result
    }
}
