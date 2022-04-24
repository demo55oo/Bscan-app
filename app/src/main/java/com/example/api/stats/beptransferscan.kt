package com.example.api.stats

data class beptransferscan(
    val message: String,
    val result: List<Result>,
    val status: String
)