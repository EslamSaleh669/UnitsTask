package com.eslam.task.Model.UnitModel

import com.eslam.task.Model.UnitModel.Data
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Response(

    @SerializedName("current_page") val current_page: Int,
    @SerializedName("data") val data: ArrayList<Data>,
    @SerializedName("first_page_url") val first_page_url: String,
    @SerializedName("from") val from: Int,
    @SerializedName("last_page") val last_page: Int,
    @SerializedName("last_page_url") val last_page_url: String,
    @SerializedName("next_page_url") val next_page_url: String,
    @SerializedName("path") val path: String,
    @SerializedName("per_page") val per_page: Int,
    @SerializedName("prev_page_url") val prev_page_url: String,
    @SerializedName("to") val to: Int,
    @SerializedName("total") val total: Int
) : Serializable