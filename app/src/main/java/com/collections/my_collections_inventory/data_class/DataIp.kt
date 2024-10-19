data class DataIp(
    val myIp: String = "192.168.208.125:8080",
) {
    fun getIp(): String {
        return this.myIp
    }
}