data class DataIp(
    val myIp: String = "YOUR_IP_ADDRESS:8080",
) {
    fun getIp(): String {
        return this.myIp
    }
}