package com.example.testmobileca.data.model

data class Category(
    val name: String,
    val type: String,
    val items: List<Account>
)
