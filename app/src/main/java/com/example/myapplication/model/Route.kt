package com.example.myapplication.model

data class Route(
    val title:String,
    val bounds: List<Bound> = listOf(Bound.BOUND1, Bound.BOUND2),
    val boundStops: Map<Bound, List<Stop>>? = null
)