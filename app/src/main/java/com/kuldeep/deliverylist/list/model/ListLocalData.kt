package com.kuldeep.posts.list.model

import com.kuldeep.deliverylist.commons.data.local.DeliveriesData
import com.kuldeep.posts.commons.data.local.DeliveriesDb
import com.kuldeep.posts.core.extensions.performOnBack
import com.kuldeep.posts.core.networking.Scheduler
import io.reactivex.Completable
import io.reactivex.Flowable

class ListLocalData(private val postDb: DeliveriesDb, private val scheduler: Scheduler) : ListDataContract.Local {


    override fun getDeliveryById(id:Int): List<DeliveriesData> {
        return postDb.deliveriesDataDao().getDeliveryById(id)
    }



    override fun getDeliveries(): Flowable<List<DeliveriesData>> {
        return postDb.deliveriesDataDao().getAllDeliveries()
    }

    override fun saveDeliveries(users: List<DeliveriesData>) {
        Completable.fromAction {
            postDb.deliveriesDataDao().upsertAll(users)
        }
                .performOnBack(scheduler)
                .subscribe()
    }
}