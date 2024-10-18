data class DataIp(
    val myIp: String = "10.0.2.2:8080",
) {
    fun getIp(): String {
        return this.myIp
    }
}