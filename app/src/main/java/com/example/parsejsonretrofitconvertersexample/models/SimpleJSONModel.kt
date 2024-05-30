package com.example.parsejsonretrofitconvertersexample.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Uncomment the following line if you're using the Kotlinx Serialization converter
//@Serializable
data class SimpleJSONModel(
    // @SerializedName(" ") for the Gson converter
    // @field:Json(name = " ") for the Moshi converter
    // @SerialName(" ") for the Kotlinx Serialization converter
    // @JsonProperty(" ") for the Jackson converter
    // @JSONField(name = " ") for the Fastjson converter

    @param:Json(name = "id")
    var employeeId: String?,

    @param:Json(name = "employee_name")
    var employeeName: String?,

    @param:Json(name = "employee_salary")
    var employeeSalary: String?,

    @param:Json(name = "employee_age")
    var employeeAge: String?
)
