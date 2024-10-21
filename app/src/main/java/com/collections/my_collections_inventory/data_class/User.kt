package com.collections.my_collections_inventory.data_class

data class User(
    val username: String,
    val password: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false


        other as User


        if (username != other.username) return false
        if (password != other.password) return false




        return true
    }
}