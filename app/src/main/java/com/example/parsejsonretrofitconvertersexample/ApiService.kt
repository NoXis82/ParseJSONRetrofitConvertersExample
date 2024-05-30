package com.example.parsejsonretrofitconvertersexample

import com.example.parsejsonretrofitconvertersexample.models.ArrayJSONModel
import com.example.parsejsonretrofitconvertersexample.models.NestedJSONModel
import com.example.parsejsonretrofitconvertersexample.models.SimpleJSONModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    /**
     * Simple JSON
     */
    @GET("/johncodeos-blog/ParseJSONRetrofitConvertersExample/main/simple.json")
    suspend fun getEmployee(): Response<SimpleJSONModel>


    /**
     * Array JSON
     */
    @GET("/johncodeos-blog/ParseJSONRetrofitConvertersExample/main/array.json")
    suspend fun getEmployees(): Response<List<ArrayJSONModel>>


    /**
     * Nested JSON
     */
    @GET("/johncodeos-blog/ParseJSONRetrofitConvertersExample/main/nested.json")
    suspend fun getEmployeesNested(): Response<NestedJSONModel>
}