package com.eslam.task.Model.UnitModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UnitModel (

	@SerializedName("response") val response : Response,
	@SerializedName("message") val message : String,
	@SerializedName("status") val status : Int
) : Serializable