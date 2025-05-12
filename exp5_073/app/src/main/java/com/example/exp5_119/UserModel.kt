package com.example.exp5_119

data class UserModel(
    val regNo: String,
    val name: String,
    val cgpa: String
) {
    companion object {
        fun fromStrings(regNo: String, name: String, cgpa: String): UserModel {
            return UserModel(regNo, name, cgpa)
        }
    }
}
