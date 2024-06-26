package com.example.parsejsonretrofitconvertersexample.models

import com.google.gson.annotations.SerializedName

// Uncomment the following line if you're using the Kotlinx Serialization converter
// @Serializable
data class NestedJSONModel(
    // @SerializedName(" ") for the Gson converter
    // @field:Json(name = " ") for the Moshi converter
    // @SerialName(" ") for the Kotlinx Serialization converter
    // @JsonProperty(" ") for the Jackson converter
    var data: List<Data>?
)

// Uncomment the following line if you're using the Kotlinx Serialization converter
// @Serializable
data class Data(
    @SerializedName("id")
    val employeeId: String?,
    val employee: Employee?,
)

// Uncomment the following line if you're using the Kotlinx Serialization converter
// @Serializable
data class Employee(
    val name: String?,
    val salary: Salary?,
    val age: String?,
)

// Uncomment the following line if you're using the Kotlinx Serialization converter
// @Serializable
data class Salary(
    val eur: Int?,
    val usd: Int?,
)
