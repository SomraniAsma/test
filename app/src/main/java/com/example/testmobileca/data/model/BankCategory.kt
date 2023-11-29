package com.example.testmobileca.data.model


data class BankCategory(
    val index: Int ,
    val bankName: String,
    val category: String,
    val accounts: List<Account>
    )

