package com.example.myapplication.kmb.eta

data class Response(
    val generated: Long,
    val response: List<Data>,
    val responsecode: Int,
    val updated: Long
)