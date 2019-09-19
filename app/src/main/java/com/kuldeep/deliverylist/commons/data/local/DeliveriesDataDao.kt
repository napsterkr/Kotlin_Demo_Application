package com.kuldeep.deliverylist.commons.data.local

import androidx.paging.DataSource
import androidx.room.*
import io.reactivex.Flowable

@Dao
interface DeliveriesDataDao {
    @Query("SELECT * from deliveriesdata")
    fun getAllDeliveries(): Flowable<List<DeliveriesData>>


    @Query("SELECT * from deliveriesdata where id==:requiredId")
    fun  getDeliveryById( requiredId:Int):List<DeliveriesData>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAll(data: List<DeliveriesData>)

    @Delete
    fun delete(data: DeliveriesData)

    @Query("SELECT * FROM deliveriesdata")
    fun getDeliveriesByCount(): DataSource.Factory<Int, DeliveriesData>


}