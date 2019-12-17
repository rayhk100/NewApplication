package com.example.myapplication

import com.example.myapplication.kmb.bound.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface KMBService {
    companion object {
        val instance: KMBService by lazy {
            Retrofit.Builder()
                .baseUrl("http://search.kmb.hk")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KMBService::class.java)
        }
    }

    @GET("/KMBWebSite/Function/FunctionRequest.ashx?action=getroutebound")
    fun getRouteBound(@Query("route") route:String, @Query("bound") bound:Int = 1): Call<com.example.myapplication.kmb.bound.Response>

    @GET("/KMBWebSite/Function/FunctionRequest.ashx?action=getstops")
    fun getStops(@Query("route") route:String, @Query("bound") bound:Int = 1): Call<com.example.myapplication.kmb.stop.Response>

    @GET("http://etav3.kmb.hk/?action=geteta")
    fun getETA(@Query("route") route:String, @Query("bound") bound:Int = 1, @Query("stop_seq") seq:Int = 0): Call<com.example.myapplication.kmb.eta.Response>

    @GET("/KMBWebSite/Function/FunctionRequest.ashx?action=getstops")
    fun getStops(@Query("route") route:String, @Query("bound") bound:Int = 1, @Query("serviceType") serviceType:Int = 1): Call<com.example.myapplication.kmb.stop.Response>
}