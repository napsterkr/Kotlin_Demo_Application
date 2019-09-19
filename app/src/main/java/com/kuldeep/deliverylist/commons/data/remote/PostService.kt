package com.kuldeep.posts.commons.data.remote

import androidx.paging.PagedList
import com.kuldeep.deliverylist.commons.data.local.DeliveriesData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("/deliveries/")
    fun getDeliveries(@Query("offset")offset:Int,@Query("limit")limit:Int): Single<List<DeliveriesData>>

}