package com.eslam.task.Model.UnitModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Attachments(

	@SerializedName("id") val id : Int,
	@SerializedName("unit_id") val unit_id : Int,
	@SerializedName("image") val image : String,
	@SerializedName("title") val title : String,
	@SerializedName("ordr") val ordr : Int,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String,
	@SerializedName("thumb") val thumb : String
) : Serializable