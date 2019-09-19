package com.kuldeep.posts.commons.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kuldeep.deliverylist.commons.data.local.DeliveriesData
import com.kuldeep.deliverylist.commons.data.local.DeliveriesDataDao

@Database(entities = [DeliveriesData::class], version = 2,exportSchema = false)
abstract class DeliveriesDb : RoomDatabase() {
    abstract fun deliveriesDataDao(): DeliveriesDataDao
}
