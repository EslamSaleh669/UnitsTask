package com.eslam.task.Network

import com.eslam.task.Model.UnitModel.UnitModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Apis {
    @GET("units")
    fun getData(@Query("page") page: String, @Query("limit") limit: String): Observable<UnitModel>
}