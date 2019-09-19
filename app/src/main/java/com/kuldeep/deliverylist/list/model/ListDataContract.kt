package com.kuldeep.posts.list.model

import androidx.paging.PagedList
import com.kuldeep.core.networking.Outcome
import com.kuldeep.deliverylist.commons.data.local.DeliveriesData
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface ListDataContract {
    interface Repository {
        val postFetchOutcome: PublishSubject<Outcome<List<DeliveriesData>>>
        fun fetchPosts(startingPos:Int)
        fun refreshPosts()
        fun saveDeliveries(users: List<DeliveriesData>)
        fun handleError(error: Throwable)
    }

    interface Local {
        fun getDeliveries(): Flowable<List<DeliveriesData>>
        fun getDeliveryById(id:Int):List<DeliveriesData>
        fun saveDeliveries(users: List<DeliveriesData>)
    }

    interface Remote {
        fun getDeliveries(lint:Int,limit:Int): Single<List<DeliveriesData>>
    }
}