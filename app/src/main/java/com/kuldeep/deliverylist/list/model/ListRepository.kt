package com.kuldeep.posts.list.model

import com.kuldeep.core.networking.Outcome
import com.kuldeep.deliverylist.commons.data.local.DeliveriesData
import com.kuldeep.posts.core.extensions.*
import com.kuldeep.posts.core.networking.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


class ListRepository(
    private val local: ListDataContract.Local,
    private val remote: ListDataContract.Remote,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : ListDataContract.Repository {

    override val postFetchOutcome: PublishSubject<Outcome<List<DeliveriesData>>> =
        PublishSubject.create<Outcome<List<DeliveriesData>>>()

    override fun fetchPosts(startingPos:Int) {
        postFetchOutcome.loading(true)
      //  remote.getDeliveries(0,10).subscribe()
        //Observe changes to the db
        remote.getDeliveries(startingPos,10)
            .performOnBackOutOnMain(scheduler)
            .subscribe({ postsWithUsers ->
                saveDeliveries(postsWithUsers)
                postFetchOutcome.success(postsWithUsers)
            }, { error -> handleError(error) })
            .addTo(compositeDisposable)
    }

    override fun refreshPosts() {
        postFetchOutcome.loading(true)
        //  remote.getDeliveries(0,10).subscribe()
        //Observe changes to the db
        remote.getDeliveries(0,10)
            .performOnBackOutOnMain(scheduler)
            .subscribe({ postsWithUsers ->
                saveDeliveries(postsWithUsers)
                postFetchOutcome.success(postsWithUsers)
            }, { error -> handleError(error) })
            .addTo(compositeDisposable)
    }

    override fun saveDeliveries(users: List<DeliveriesData>) {
        local.saveDeliveries(users)
    }

    override fun handleError(error: Throwable) {
        postFetchOutcome.failed(error)
    }

}