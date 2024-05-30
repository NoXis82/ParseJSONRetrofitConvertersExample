package com.example.parsejsonretrofitconvertersexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.parsejsonretrofitconvertersexample.ui.theme.ParseJSONRetrofitConvertersExampleTheme
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val coroutineScope = rememberCoroutineScope()
            ParseJSONRetrofitConvertersExampleTheme {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Button(onClick = {
                        coroutineScope.launch {
                            parseJSON()
                        }
                    }) {
                        Text(text = "Simple Json")
                    }
                }
            }
        }
    }

    private fun parseJSON() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
        // .addConverterFactory(GsonConverterFactory.create()) for Gson converter
        // .addConverterFactory(MoshiConverterFactory.create()) for Moshi converter
        // .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())) for Kotlinx Serialization converter
        // .addConverterFactory(JacksonConverterFactory.create()) for Jackson converter

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
             .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        // Create Service
        val service = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            // Do the GET request and get response
            val response = service.getEmployee()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(response.body())
                    Log.d("Pretty Printed JSON :", prettyJson)
//                    binding.jsonResultsTextview.text = prettyJson

                    // ID
                    val id = response.body()?.employeeId ?: "N/A"
                    Log.d("ID: ", id)
//                    binding.employeeIdTextview.text = id

                    // Employee Name
                    val employeeName = response.body()?.employeeName ?: "N/A"
                    Log.d("Employee Name: ", employeeName)
//                    binding.employeeNameTextview.text = employeeName

                    // Employee Salary
                    val employeeSalary = response.body()?.employeeSalary ?: "N/A"
                    Log.d("Employee Salary: ", employeeSalary)
//                    binding.employeeSalaryTextview.text = "$ $employeeSalary"

                    // Employee Age
                    val employeeAge = response.body()?.employeeAge ?: "N/A"
                    Log.d("Employee Age: ", employeeAge)
//                    binding.employeeAgeTextview.text = employeeAge
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }


}
