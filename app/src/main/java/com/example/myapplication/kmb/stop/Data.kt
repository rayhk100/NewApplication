package com.example.myapplication.kmb.stop

data class Data(
    val additionalInfo: AdditionalInfo,
    val basicInfo: BasicInfo,
    val route: Route,
    val routeStops: List<RouteStop>
)