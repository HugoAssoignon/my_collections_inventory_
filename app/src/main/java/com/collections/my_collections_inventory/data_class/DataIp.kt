data class DataIp(
    val myIp: String = "172.20.10.7:8080",
) {
    fun getIp(): String {
        return this.myIp
    }
}