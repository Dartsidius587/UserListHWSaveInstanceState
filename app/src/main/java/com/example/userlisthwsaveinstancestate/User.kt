package com.example.userlisthwsaveinstancestate

data class User(val name: String, val age: String){
    override fun toString() = "Имя: $name\nВозраст: $age"
}